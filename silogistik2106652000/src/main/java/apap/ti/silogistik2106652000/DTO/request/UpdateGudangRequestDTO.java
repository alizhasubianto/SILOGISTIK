package apap.ti.silogistik2106652000.DTO.request;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106652000.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO {
    private BigInteger id; 
    private List<GudangBarang> listGudangBarang;
}
