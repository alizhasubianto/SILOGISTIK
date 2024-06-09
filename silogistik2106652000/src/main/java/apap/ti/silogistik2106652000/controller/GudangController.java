package apap.ti.silogistik2106652000.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106652000.DTO.GudangMapper;
import apap.ti.silogistik2106652000.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106652000.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;
import apap.ti.silogistik2106652000.service.BarangService;
import apap.ti.silogistik2106652000.service.GudangBarangService;
import apap.ti.silogistik2106652000.service.GudangService;
import jakarta.validation.Valid;

import org.springframework.ui.Model;


@Controller
public class GudangController {

    @Autowired
    private GudangService gudangService;

    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangBarangService gudangBarangService;

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @GetMapping("gudang/")
    public String listGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        return "viewall-gudang";
    }

    @GetMapping("/gudang/{id}")
    public String detailGudang(@PathVariable("id") Long id, Model model){
        var gudang = gudangService.getGudangById(id);
        
        ReadGudangResponseDTO gudangDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);

        Map<Barang, Integer> mapBarangStok = gudangService.getBarangStokMapByGudang(id);

        model.addAttribute("gudang", gudangDTO);
        model.addAttribute("listBarang", mapBarangStok);

        return "view-gudang";
    }

    @GetMapping("gudang/{id}/restock-barang")
    public String formRestockGudang(@PathVariable("id") Long id, Model model){
        var gudang = gudangService.getGudangById(id);
        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

        List<GudangBarang> listGudangBarang = gudangDTO.getListGudangBarang();

        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("gudangDTO", gudangDTO);
        return "restock-gudang";
    }
    
    @PostMapping(value = "gudang/{id}/restock-barang", params = {"addRow"})
    public String addRowBarang(
        @ModelAttribute UpdateGudangRequestDTO updateGudangRequestDTO,
        Model model
    ){
        if(updateGudangRequestDTO.getListGudangBarang() == null || updateGudangRequestDTO.getListGudangBarang().size() == 0){
            updateGudangRequestDTO.setListGudangBarang(new ArrayList<>());
        }
    
        updateGudangRequestDTO.getListGudangBarang().add(new GudangBarang());

        List<Barang> listBarang = barangService.getAllBarang();

        List<GudangBarang> listGudangBarang = updateGudangRequestDTO.getListGudangBarang();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listGudangBarang", listGudangBarang);
        model.addAttribute("gudangDTO", updateGudangRequestDTO);
        return "restock-gudang";
    }

    @PostMapping(value="gudang/{id}/restock-barang")
    public String restockGudang(@Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model){
        var gudang = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);
        gudangService.restockGudang(gudang);

        model.addAttribute("gudang", gudang.getNama());
        model.addAttribute("idGudang", gudang.getId());

        return "success-restock-barang";

    }

    @GetMapping("/gudang/cari-barang")
    public String searchBarang(@RequestParam(value = "sku", required = false) String sku, Model model) {
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);

        if (sku != null) {
            Map<Gudang, Integer> gudangStokMap = gudangService.getSearchGudangByBarang(sku);
            model.addAttribute("gudangList", gudangStokMap);
        }

        return "search-barang";
    }
    
}
