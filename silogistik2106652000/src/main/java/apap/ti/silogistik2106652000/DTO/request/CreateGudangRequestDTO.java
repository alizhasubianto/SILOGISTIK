package apap.ti.silogistik2106652000.DTO.request;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGudangRequestDTO {
    private String nama;
    private String alamatGudang;
}
