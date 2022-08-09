package com.dibimbing.ptabc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "karyawan_training")
@Where(clause = "deleted_date is null")
public class KaryawanTraining implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_karyawan_training",sequenceName = "karyawan_training_id_seq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_karyawan_training")
    private Long id;

    @ManyToOne(targetEntity = Karyawan.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_karyawan",referencedColumnName = "id", nullable = false)
    private Karyawan karyawan;

    @ManyToOne(targetEntity = Training.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_training",referencedColumnName = "id", nullable = false)
    private Training training;


    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date")
    private java.util.Date created_date; // java.util.Date

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_date")
    private java.util.Date updated_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "deleted_date")
    private java.util.Date deleted_date;

}
