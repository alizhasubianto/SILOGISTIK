package apap.ti.silogistik2106652000.service;

import java.util.List;

import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.model.PermintaanPengirimanBarang;

@Service
public interface PermintaanPengirimanBarangService {
    List<PermintaanPengirimanBarang> getAllPermintaanPengirimanBarang();
    PermintaanPengirimanBarang getPermintaanPengirimanBarangById(long idPermintaanPengirimanBarang);
    
}
