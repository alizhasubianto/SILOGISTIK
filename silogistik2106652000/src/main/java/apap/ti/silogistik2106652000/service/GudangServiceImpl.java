package apap.ti.silogistik2106652000.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import apap.ti.silogistik2106652000.DTO.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;
import apap.ti.silogistik2106652000.repository.GudangBarangDb;
import apap.ti.silogistik2106652000.repository.GudangDb;

@Service
public class GudangServiceImpl implements GudangService{
    
    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void createGudang(Gudang gudang){
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang(){
        return gudangDb.findAll();
    }

    @Override
    public Long countAllGudang(){
        return gudangDb.count();
    }

    @Override
    public Gudang getGudangById(Long idGudang){
        for (Gudang gudang: getAllGudang()){
            if (gudang.getId().equals(idGudang)){
                return gudang;
            }
        }
        return null;
    }

    @Override
    public void restockGudang(Gudang gudangDTO){
        //get id
        Gudang gudang = getGudangById(gudangDTO.getId());

        for (GudangBarang gudangbarang : gudangDTO.getListGudangBarang()){
                if (gudangbarang.getId() != null){
                    GudangBarang gudangBarangExisting = gudangBarangService.getGudangBarangById(gudangbarang.getId());
                    gudangBarangExisting.setStok(gudangbarang.getStok());
                    gudangBarangDb.save(gudangBarangExisting);
                } else {
                    gudangbarang.setGudang(gudang);
                    gudangBarangDb.save(gudangbarang);
                }

        }
    }

    @Override
    public List<Barang> getBarangByGudang(Gudang gudang){
        List<GudangBarang> gudangBarangList = gudangBarangDb.findByGudang(gudang);
        List<Barang> barangList = new ArrayList<>();
        
        for (GudangBarang gudangBarang : gudangBarangList){
            barangList.add(gudangBarang.getBarang());
        }

        return barangList;
    }

    @Override
    public List<String> getSkuByGudang(Gudang gudang) {
        List<GudangBarang> gudangBarangList = gudangBarangDb.findByGudang(gudang);
        List<String> skuList = new ArrayList<>();
        
        for (GudangBarang gudangBarang : gudangBarangList){
            skuList.add(gudangBarang.getBarang().getSku());
        }

        return skuList;
    }

    @Override
    public Map<Barang, Integer> getBarangStokMapByGudang(Long id) {
        List<GudangBarang> listGudangBarang = gudangBarangService.getAllGudangBarang();

        listGudangBarang = listGudangBarang.stream()
                .filter(barang -> barang.getGudang().getId().equals(id))
                .collect(Collectors.toList());

        Map<Barang, Integer> barangStokMap = new HashMap<>();
        for (GudangBarang gudangBarang : listGudangBarang) {
            barangStokMap.put(gudangBarang.getBarang(), gudangBarang.getStok());
        }

        return barangStokMap;
    }

    @Override
    public Map<Gudang, Integer> getSearchGudangByBarang(String sku){
        List<GudangBarang> listGudangBarang = gudangBarangService.getAllGudangBarang();

        listGudangBarang = listGudangBarang.stream()
                .filter(barang -> barang.getBarang().getSku().equals(sku))
                .collect(Collectors.toList());

        Map<Gudang, Integer> gudangStokMap = new HashMap<>();
        for (GudangBarang gudangBarang : listGudangBarang){
            gudangStokMap.put(gudangBarang.getGudang(), gudangBarang.getStok());
        }

        return gudangStokMap;
    }


}
    

