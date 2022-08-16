package com.dibimbing.ptabc.service;

import com.dibimbing.ptabc.model.Karyawan;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface KaryawanService {
    public Map insert(Karyawan karyawan);
    public Map update(Karyawan karyawan);
    public Map delete(Long karyawan);

    //list ada dicontroller

    //mvc
    public Map getAll(int size, int page);
    public Map getById(Karyawan karyawan);
}
