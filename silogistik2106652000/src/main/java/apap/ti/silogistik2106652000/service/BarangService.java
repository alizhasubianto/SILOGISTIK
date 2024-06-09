package apap.ti.silogistik2106652000.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;


@Service
public interface BarangService {
    List<Barang> getAllBarang();
    Long countAllBarang();
    void saveBarang(Barang barang);
    //void saveBarang(Barang barang);
    Barang getBarangById(String sku);
    Barang updateBarang(Barang barang);
    String convertToDescription(ReadBarangResponseDTO barangDTO);
    Map<Barang, Long> getAllBarangWithStok();
    Map<Gudang, Integer> getGudangStokMapByBarang(String sku);

    void deleteBarang(Barang barang);
    List<Barang> getNonDeletedBarang();
}
