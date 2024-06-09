package apap.ti.silogistik2106652000.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;

@Service
public interface GudangBarangService {
    void saveGudangBarang(GudangBarang gudangBarang);

    GudangBarang getGudangBarangById(long id);

    List<GudangBarang> getAllGudangBarang();

}
