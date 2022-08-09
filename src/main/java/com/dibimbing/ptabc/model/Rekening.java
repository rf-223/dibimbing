package com.dibimbing.ptabc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "rekening")
@Where(clause = "deleted_date is null")
public class Rekening implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_rekening",sequenceName = "rekening_id_seq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rekening")
    private Long id;

    @Column(name = "nama", nullable = false , length = 80)
    private String nama;

    @Column(name = "jenis", nullable = false, length = 100)
    private String jenis;

    @Column(name = "nomor",nullable = false, length = 100)
    private String nomor;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date")
    private java.util.Date created_date; // java.util.Date

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_date")
    private java.util.Date updated_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "deleted_date")
    private java.util.Date deleted_date;

    @ManyToOne(targetEntity = Rekening.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan",referencedColumnName = "id", nullable = false)
    private Karyawan karyawan;

}
