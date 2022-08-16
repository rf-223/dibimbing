package com.dibimbing.ptabc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "karyawan_detail")
public class KaryawanDetail implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_karyawan_detail",sequenceName = "karyawan_detail_id_seq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_karyawan_detail")
    private Long id;

    @Column(name = "nik", length = 20)
    private String nik;

    @Column(name = "npwp", length = 20)
    private String npwp;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan",referencedColumnName = "id", nullable = false)
    private Karyawan karyawan;





}
