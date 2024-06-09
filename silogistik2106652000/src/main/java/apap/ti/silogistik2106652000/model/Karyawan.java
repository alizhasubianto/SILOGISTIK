package apap.ti.silogistik2106652000.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="karyawan")
public class Karyawan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="nama_karyawan", nullable=false)
    private String nama;

    @NotNull
    @Column(name="jenis_kelamin", nullable = false)
    private Integer jenisKelamin;
    
    @NotNull
    @Column(name="tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @OneToMany(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengiriman> listPermintaanPengiriman = new ArrayList<>();
}
