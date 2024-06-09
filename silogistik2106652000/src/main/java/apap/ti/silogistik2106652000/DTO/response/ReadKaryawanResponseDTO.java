package apap.ti.silogistik2106652000.DTO.response;

import java.math.BigInteger;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadKaryawanResponseDTO {
    private Long id;
    private String nama;
    private Integer jenisKelamin;
    private LocalDate tanggalLahir;
}
