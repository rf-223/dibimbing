package com.dibimbing.ptabc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_karyawan",sequenceName = "karyawan_id_seq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_karyawan")
    private Long id;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "jk",nullable = false, length = 15)
    private String jk;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob",nullable = false)
    private Date dob; // java.sql.Date

    @Column(name = "alamat", nullable = false,columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date")
    private java.util.Date created_date; // java.util.Date

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_date")
    private java.util.Date updated_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "deleted_date")
    private java.util.Date deleted_date;



    @OneToOne(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private KaryawanDetail karyawanDetail;


    @JsonIgnore
    @OneToMany(mappedBy = "karyawan",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<KaryawanTraining> karyawanTrainingList;



}
