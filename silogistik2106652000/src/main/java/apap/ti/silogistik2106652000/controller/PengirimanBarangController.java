package apap.ti.silogistik2106652000.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106652000.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106652000.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106652000.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;
import apap.ti.silogistik2106652000.model.Karyawan;
import apap.ti.silogistik2106652000.model.PermintaanPengiriman;
import apap.ti.silogistik2106652000.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106652000.service.BarangService;
import apap.ti.silogistik2106652000.service.KaryawanService;
import apap.ti.silogistik2106652000.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class PengirimanBarangController {

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    BarangService barangService;

    @GetMapping("/permintaan-pengiriman/tambah")
    public String formAddPermintaanPengirimanBarang(Model model){
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listBarang", listBarang);
        
        return "form-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String addPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, Model model){
        
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
      
        permintaanPengirimanService.generateNomorPengiriman(permintaanPengiriman);

        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());

        return "success-create-permintaanpengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowBarang(
        @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO,
        Model model
    ){
        if(createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang() == null || createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().size() == 0){
            createPermintaanPengirimanRequestDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
    
        createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        List<Barang> listBarang = barangService.getAllBarang();

        List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang();

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();

        // Kirim list penerbit penulis untuk menjadi pilihan pada dropdown.
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("listPermintaanPengirimanBarang", listPermintaanPengirimanBarang);
        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);


        return "form-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/")
    public String listPermintaanPengiriman(Model model){
        //Mendapatkan semua permintaan pengiriman
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        return "viewall-permintaanpengiriman";
    }

    @GetMapping("/permintaan-pengiriman/{id}")
    public String detailPermintaanPengiriman(@PathVariable("id") Long id, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);

        ReadPermintaanPengirimanResponseDTO permintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
        var jenisPengiriman = permintaanPengirimanService.convertToDescription(permintaanPengirimanDTO);

        Map<Barang, Integer> detailPesananBarangMap = permintaanPengirimanService.getDetailBarangPesanan(id);

        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("jenisPengiriman", jenisPengiriman);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("detailPesananBarangMap", detailPesananBarangMap);
        model.addAttribute("permintaanPengiriman", listPermintaanPengiriman);

        return "detail-permintaan-pengiriman";

    }

    @GetMapping("/permintaan-pengiriman/{id}/cancel")
    public String cancelPermintaanPengiriman(@PathVariable("id") Long id, Model model){
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);

        permintaanPengirimanService.cancelPermintaanPengiriman(permintaanPengiriman);

        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());

        return "success-cancel-permintaan-pengiriman";
    }

    @GetMapping("/filter-permintaan-pengiriman")
    public String filterPermintaanPengiriman( 
        @RequestParam(value = "start-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam(value = "end-date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @RequestParam(value = "sku", required = false) String sku,  Model model) {
        
        List<Barang> listBarang = barangService.getAllBarang();

         // Convert LocalDate to LocalDateTime
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;

        List<PermintaanPengiriman> filteredPermintaan = permintaanPengirimanService.filterPermintaanPengiriman(startDateTime, endDateTime, sku);

        model.addAttribute("filteredPermintaan", filteredPermintaan);
        model.addAttribute("listBarang", listBarang);

        return "filter-permintaan-pengiriman";
    }


}

