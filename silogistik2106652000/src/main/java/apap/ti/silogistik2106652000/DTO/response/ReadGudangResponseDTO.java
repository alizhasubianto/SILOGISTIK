package apap.ti.silogistik2106652000.DTO.response;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106652000.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private Long id;
    private String nama;
    private String alamatGudang;
    private List<GudangBarang> listGudangBarang;
}
