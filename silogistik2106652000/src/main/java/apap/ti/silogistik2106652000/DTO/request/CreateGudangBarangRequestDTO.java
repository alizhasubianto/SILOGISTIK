package apap.ti.silogistik2106652000.DTO.request;

import apap.ti.silogistik2106652000.model.Barang;
import apap.ti.silogistik2106652000.model.Gudang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGudangBarangRequestDTO {
    private Integer stok;
    private Long id;
    private Gudang gudang;
    private Barang barang;
}
