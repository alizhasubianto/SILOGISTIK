package apap.ti.silogistik2106652000.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.model.Karyawan;
import apap.ti.silogistik2106652000.repository.KaryawanDb;

@Service
public class KaryawanServiceImpl implements KaryawanService {
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void createKaryawan(Karyawan karyawan){
        karyawanDb.save(karyawan);
    }

    @Override
    public Long countAllKaryawan(){
        return karyawanDb.count();
    }

    @Override
    public List<Karyawan> getAllKaryawan(){
        return karyawanDb.findAll();
    }
}
