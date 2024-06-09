package apap.ti.silogistik2106652000.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBarangRequestDTO extends CreateBarangRequestDTO {
    private String sku;

}
