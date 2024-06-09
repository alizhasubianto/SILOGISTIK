package apap.ti.silogistik2106652000.DTO.request;

import java.math.BigInteger;
import java.util.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateKaryawanRequestDTO {
    private String nama;
    private Integer jenisKelamin;
    private Date tanggalLahir;

}
