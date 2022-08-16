package com.dibimbing.ptabc.service;

import com.dibimbing.ptabc.dao.KaryawanTrainingRequest;
import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanTraining;
import com.dibimbing.ptabc.model.Rekening;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KaryawanTrainingService {
    public Map insert(KaryawanTrainingRequest obj);
    public Map update(KaryawanTrainingRequest obj);
    public Map delete(Long obj);

    public Map getAll(int size, int page);

}
