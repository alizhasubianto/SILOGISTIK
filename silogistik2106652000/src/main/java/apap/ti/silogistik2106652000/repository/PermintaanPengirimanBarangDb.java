package apap.ti.silogistik2106652000.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106652000.model.PermintaanPengiriman;
import apap.ti.silogistik2106652000.model.PermintaanPengirimanBarang;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    
}
