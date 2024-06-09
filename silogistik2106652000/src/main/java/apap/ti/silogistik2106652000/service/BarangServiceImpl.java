package apap.ti.silogistik2106652000.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;
import apap.ti.silogistik2106652000.repository.BarangDb;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangService gudangBarangService;

    @Override
    public List<Barang> getAllBarang(){
        return barangDb.findByIsDeletedFalse();
    }

    @Override
    public Long countAllBarang(){
        return (long) getNonDeletedBarang().size();
    }

    @Override
    public void saveBarang(Barang barang){
        if (barang.getTipeBarang() == null || barang.getTipeBarang() < 1 || barang.getTipeBarang() > 5) {
            throw new IllegalArgumentException("Tipe barang tidak valid");
        }

        Long countByTipe = barangDb.countByTipeBarang(barang.getTipeBarang());

        String tipeString;
        switch (barang.getTipeBarang()) {
            case 1:
                tipeString = "ELEC";
                break;
            case 2:
                tipeString = "CLOT";
                break;
            case 3:
                tipeString = "FOOD";
                break;
            case 4:
                tipeString = "COSM";
                break;
            case 5:
                tipeString = "TOOL";
                break;
            default:
                throw new IllegalArgumentException("Tipe barang tidak valid");
        }

        String countString = String.format("%03d", countByTipe + 1);

        String sku = tipeString + countString;
        barang.setSku(sku);

        barangDb.save(barang);
    }

    @Override
    public Barang getBarangById(String sku){
        for (Barang barang: getAllBarang()){
            if(barang.getSku().equals(sku)){
                return barang;
            }
        }
        return null;
    }

    @Override
    public Barang updateBarang(Barang barangFromDto){
        Barang barang = getBarangById(barangFromDto.getSku());
        if (barang != null){
            barang.setMerk(barangFromDto.getMerk());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public String convertToDescription(ReadBarangResponseDTO barang){
        switch (barang.getTipeBarang()) {
            case 1:
                return "Produk Elektronik";
            case 2:
                return "Pakaian & Aksesoris";
            case 3:
                return "Makanan & Minuman";
            case 4:
                return "Kosmetik";
            case 5:
                return "Perlengkapan Rumah";
            default:
                return "Tipe Barang Tidak Valid";
        }
    }

    @Override
    public Map<Barang, Long> getAllBarangWithStok() {
        Map<Barang, Long> mapBarangStok = new HashMap<>();

        for (Barang barang : getAllBarang()) {
            long totalStok = 0;
            for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
                totalStok += gudangBarang.getStok();
            }

            mapBarangStok.put(barang, totalStok);
        }

        return mapBarangStok;
    }

    @Override
    public Map<Gudang, Integer> getGudangStokMapByBarang(String sku){
        List<GudangBarang> listGudangBarang = gudangBarangService.getAllGudangBarang();

        listGudangBarang = listGudangBarang.stream()
                .filter(gudang -> gudang.getBarang().getSku().equals(sku))
                .collect(Collectors.toList());

        Map<Gudang, Integer> gudangStokMap = new HashMap<>();
        for (GudangBarang gudangBarang : listGudangBarang) {
            gudangStokMap.put(gudangBarang.getGudang(), gudangBarang.getStok());
        }

        return gudangStokMap;
    }

    @Override
    public void deleteBarang(Barang barang){
        barang.setDeleted(true);
        barangDb.save(barang);

    }

    @Override
    public List<Barang> getNonDeletedBarang(){
        return barangDb.findByIsDeletedFalse();
    }

}
