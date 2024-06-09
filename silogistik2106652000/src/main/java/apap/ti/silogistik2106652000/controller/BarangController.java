package apap.ti.silogistik2106652000.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ti.silogistik2106652000.DTO.BarangMapper;
import apap.ti.silogistik2106652000.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106652000.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadBarangResponseDTO;
//import apap.ti.silogistik2106652000.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;
//import apap.ti.silogistik2106652000.model.Gudang;
//import apap.ti.silogistik2106652000.model.PermintaanPengiriman;
import apap.ti.silogistik2106652000.service.BarangService;
import apap.ti.silogistik2106652000.service.GudangService;
import apap.ti.silogistik2106652000.service.KaryawanService;
import apap.ti.silogistik2106652000.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;

    @Autowired
    GudangService gudangService;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangMapper barangMapper;
    
    @GetMapping("/")
    public String home(Model model){
        Long jumlahGudang = gudangService.countAllGudang();
        Long jumlahBarang = barangService.countAllBarang();
        Long jumlahKaryawan = karyawanService.countAllKaryawan();
        Long jumlahPermintaanPengiriman = permintaanPengirimanService.countAllPermintaanPengiriman();

        model.addAttribute("activePage", "home");
        model.addAttribute("jumlahGudang", jumlahGudang);
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("jumlahKaryawan", jumlahKaryawan);
        model.addAttribute("jumlahPermintaanPengiriman", jumlahPermintaanPengiriman);
        return "home";
    }

    @GetMapping("barang/")
    public String listBarang(Model model){
        Map<Barang, Long> mapBarangStok = barangService.getAllBarangWithStok();

        model.addAttribute("listBarang", mapBarangStok);
        return "viewall-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model){
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return "form-create-barang.html";

    }

    @PostMapping("barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model){
        
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);

        barangService.saveBarang(barang);

        model.addAttribute("barang", barang.getSku());

        return "success-create-barang";
    }

    @GetMapping("/barang/{sku}/ubah")
    public String formUpdateBarang(@PathVariable("sku") String sku, Model model){
        var barang = barangService.getBarangById(sku);
        var barangDTO = barangMapper.barangToUpdateBarangResponseDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "form-update-barang";
    }

    @PostMapping("/barang/ubah")
    public String updateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, Model model){
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.updateBarang(barangFromDTO);
        model.addAttribute("sku", barang.getSku());

        return "success-update-barang";
    }

    @GetMapping("/barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model){
        var barang = barangService.getBarangById(sku);
        
        ReadBarangResponseDTO barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);
        var tipeBarang = barangService.convertToDescription(barangDTO);

        int stok = 0;
        for (GudangBarang gudangBarang: barang.getListGudangBarang()) {
            stok += gudangBarang.getStok();
        }

        Map<Gudang, Integer> mapGudangStok = barangService.getGudangStokMapByBarang(sku);

        model.addAttribute("barang", barangDTO);
        model.addAttribute("tipeBarang", tipeBarang);
        model.addAttribute("stok", stok);
        model.addAttribute("listGudang", mapGudangStok);
        return "view-barang";
    }

    @GetMapping("/barang/{sku}/delete")
    public String deleteBarang(@PathVariable("sku") String sku, Model model){
       var barang = barangService.getBarangById(sku);
       //bukuService.deletedBuku(buku);
       barangService.deleteBarang(barang);

       model.addAttribute("sku", sku);
       
       return "success-delete-barang";
    }
    
}


