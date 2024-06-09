package apap.ti.silogistik2106652000.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.GudangBarang;
import apap.ti.silogistik2106652000.repository.GudangBarangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang){
        gudangBarangDb.save(gudangBarang);
    }

    @Override
    public List<GudangBarang> getAllGudangBarang(){
        return gudangBarangDb.findAll();
    }

    @Override
    public GudangBarang getGudangBarangById(long id){
        for (GudangBarang barang: getAllGudangBarang()){
            if(barang.getId().equals(id)){
                return barang;
            }
        }
        return null;
    }

    
}
