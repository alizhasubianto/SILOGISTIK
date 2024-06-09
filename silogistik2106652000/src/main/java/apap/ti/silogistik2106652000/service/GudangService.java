package apap.ti.silogistik2106652000.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.DTO.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;

@Service
public interface GudangService {
    void createGudang(Gudang gudang);

    List<Gudang> getAllGudang();
    
    Gudang getGudangById(Long id);

    Long countAllGudang();

    void restockGudang(Gudang gudangDTO);

    List<Barang> getBarangByGudang(Gudang gudang);

    List<String> getSkuByGudang(Gudang gudang);

    Map<Barang, Integer> getBarangStokMapByGudang(Long id);

    Map<Gudang, Integer> getSearchGudangByBarang(String sku);
}
