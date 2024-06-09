package apap.ti.silogistik2106652000.service;

import java.util.List;

import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.model.Karyawan;

@Service
public interface KaryawanService {
    void createKaryawan(Karyawan karyawan);
    List<Karyawan> getAllKaryawan();
    Long countAllKaryawan();
}
