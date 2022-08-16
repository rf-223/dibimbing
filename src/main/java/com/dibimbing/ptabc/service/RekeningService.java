package com.dibimbing.ptabc.service;

import com.dibimbing.ptabc.model.Rekening;
import com.dibimbing.ptabc.model.Training;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RekeningService {
    public Map insert(Rekening rekening, Long idKaryawan);
    public Map update(Rekening rekening, Long idKaryawan);
    public Map delete(Long rekeningId);
    public Map getAll(int size, int page);

    public Map getById(Rekening rekening);
}
