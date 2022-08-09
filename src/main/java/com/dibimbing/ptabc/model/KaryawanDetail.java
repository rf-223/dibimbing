package com.dibimbing.ptabc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "karyawan_detail")
@Where(clause = "deleted_date is null")
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date")
    private java.util.Date created_date; // java.util.Date

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_date")
    private java.util.Date updated_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "deleted_date")
    private java.util.Date deleted_date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;
}
