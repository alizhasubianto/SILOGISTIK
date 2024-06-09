package apap.ti.silogistik2106652000.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="barang")
public class Barang {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max=7)
    @Column(name="sku", nullable = false)
    private String sku;

    @NotNull
    @Column(name="tipe_barang", nullable = false)
    private Integer tipeBarang;

    @NotNull
    @Column(name="merk", nullable = false)
    private String merk;

    @Column(name="is_deleted")
    private boolean isDeleted = false;

    @NotNull
    @Column(name="harga_barang", nullable = false)
    private Long hargaBarang;
    
    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang = new ArrayList<>();

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();
}

