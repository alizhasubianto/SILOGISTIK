package apap.ti.silogistik2106652000.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106652000.model.Gudang;

@Repository
public interface GudangDb extends JpaRepository<Gudang, Long>{

}
