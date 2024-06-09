package apap.ti.silogistik2106652000.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import apap.ti.silogistik2106652000.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106652000.DTO.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Karyawan;
import apap.ti.silogistik2106652000.model.PermintaanPengiriman;

@Service
public interface PermintaanPengirimanService {
    Long countAllPermintaanPengiriman();
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    void generateNomorPengiriman(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    PermintaanPengiriman getPermintaanPengirimanById(long idPermintaanPengiriman);
    Map<Barang, Integer> getDetailBarangPesanan(long id);
    String convertToDescription(ReadPermintaanPengirimanResponseDTO permintaanPengirimanDTO);
    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengiriman> filterPermintaanPengiriman(LocalDateTime startDate, LocalDateTime endDate, String sku);
}
