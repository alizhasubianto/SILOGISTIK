package apap.ti.silogistik2106652000.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106652000.model.PermintaanPengiriman;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long>{
    @Query("SELECT p FROM PermintaanPengiriman p ORDER BY p.waktuPermintaan DESC")
    List<PermintaanPengiriman> findByOrderByWaktuPermintaanAsc();
    
}
