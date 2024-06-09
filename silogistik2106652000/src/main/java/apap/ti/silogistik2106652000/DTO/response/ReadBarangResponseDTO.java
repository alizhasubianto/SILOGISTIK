package apap.ti.silogistik2106652000.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private String merk;
    private Integer stok;
    private Integer tipeBarang;
    private Long hargaBarang;
}
