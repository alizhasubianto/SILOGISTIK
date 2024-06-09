package apap.ti.silogistik2106652000.DTO.response;

import java.time.LocalDateTime;
import java.util.Date;

import apap.ti.silogistik2106652000.model.Karyawan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO {
    private LocalDateTime waktuPermintaan;
    private String nomorPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private Date tanggalPengiriman;
    private Integer jenisLayanan;
    private Integer biayaPengiriman;
    private Karyawan karyawan;

    
}
