package com.dibimbing.ptabc.dao;

import lombok.Data;

import java.util.Date;

@Data
public class KaryawanTrainingRequest {
    private Long id; // id karyawan transaksi
    private Long idKaryawan;
    private Long idTraining;
    private Date training_date;
    private Date created_date;
    private Date updated_date;
}
