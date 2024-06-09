package apap.ti.silogistik2106652000.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106652000.model.Karyawan;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long> {
    List<Karyawan> findAll();
    Optional<Karyawan> findById(Long id);
}
