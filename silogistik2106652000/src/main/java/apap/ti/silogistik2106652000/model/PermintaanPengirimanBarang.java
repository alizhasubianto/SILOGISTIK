package apap.ti.silogistik2106652000.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name="id_permintaan_pengiriman", referencedColumnName = "id")
    private PermintaanPengiriman permintaanPengiriman;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sku_barang", referencedColumnName = "sku")
    private Barang barang;
    
    @NotNull
    @Column(name="kuantitas_pesanan", nullable = false)
    private Integer kuantitasPesanan;
}
