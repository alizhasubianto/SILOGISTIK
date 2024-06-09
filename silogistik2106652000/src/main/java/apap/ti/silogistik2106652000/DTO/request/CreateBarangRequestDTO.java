package apap.ti.silogistik2106652000.DTO.request;

import java.math.BigInteger;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBarangRequestDTO {
    // @NotBlank(message = "Merk tidak boleh kosong")
    private String merk;

    // @NotBlank(message = "Tipe Barang tidak boleh kosong")
    private Integer tipeBarang;

    // @NotBlank(message = "Harga Barang tidak boleh kosong")
    private Long hargaBarang;
}
