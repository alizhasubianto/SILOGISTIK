package apap.ti.silogistik2106652000.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="permintaan_pengiriman")
public class PermintaanPengiriman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name="nomor_pengiriman", nullable = false)
    private String nomorPengiriman;

    @NotNull
    @Column(name="is_cancelled", nullable = false)
    private Boolean isCancelled = false;

    @NotNull
    @Column(name="nama_penerima", nullable = false)
    private String namaPenerima;

    @NotNull
    @Column(name="alamat_penerima", nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(name="tanggal_pengiriman", nullable = false)
    private LocalDate tanggalPengiriman;

    @NotNull
    @Column(name="biaya_pengiriman", nullable = false)
    private Integer biayaPengiriman;

    @NotNull
    @Column(name="jenis_layanan", nullable = false)
    @Min(value = 1)
    @Max(value = 4)
    private Integer jenisLayanan;
    
    @NotNull
    @Column(name="waktu_permintaan", nullable = false)
    private LocalDateTime waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();

}
