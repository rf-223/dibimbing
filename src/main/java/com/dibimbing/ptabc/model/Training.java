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
@Table(name = "training")
@Where(clause = "deleted_date is null")
public class Training implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "seq_training",sequenceName = "training_id_seq",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_training")
    private Long id;

    @Column(name = "tema",nullable = false,length = 100)
    private String tema;

    @Column(name = "nama_pengajar",nullable = false,length = 100)
    private String namaPengajar;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_date")
    private java.util.Date created_date; // java.util.Date

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_date")
    private java.util.Date updated_date;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "deleted_date")
    private java.util.Date deleted_date;

    @JsonIgnore
    @OneToMany(mappedBy = "training",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<KaryawanTraining> karyawanTrainingList;
}
