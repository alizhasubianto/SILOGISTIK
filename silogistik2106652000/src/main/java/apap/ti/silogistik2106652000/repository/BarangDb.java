package apap.ti.silogistik2106652000.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106652000.model.Barang;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {

    Long countByTipeBarang(Integer tipeBarang);
    
    List<Barang> findByIsDeletedFalse();
    
}
