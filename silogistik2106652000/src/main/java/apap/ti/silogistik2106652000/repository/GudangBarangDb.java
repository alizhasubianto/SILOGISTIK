package apap.ti.silogistik2106652000.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106652000.model.Gudang;
import apap.ti.silogistik2106652000.model.GudangBarang;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long>{
    List<GudangBarang> findByGudang(Gudang gudang);
}
