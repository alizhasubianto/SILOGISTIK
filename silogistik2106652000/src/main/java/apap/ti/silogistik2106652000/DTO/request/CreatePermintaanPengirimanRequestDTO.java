package apap.ti.silogistik2106652000.DTO.request;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import apap.ti.silogistik2106652000.model.Karyawan;
import apap.ti.silogistik2106652000.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermintaanPengirimanRequestDTO {
   // private Long id;
    private String namaPenerima;
    private String alamatPenerima;
    private LocalDate tanggalPengiriman;
    private Integer biayaPengiriman;
    private Integer jenisLayanan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
