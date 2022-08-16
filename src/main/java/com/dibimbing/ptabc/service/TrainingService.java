package com.dibimbing.ptabc.service;

import com.dibimbing.ptabc.model.Training;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TrainingService {

    public Map insert(Training training);
    public Map update(Training training);
    public Map delete(Long trainingId);
    public Map getAll(int size, int page);

    public Map getById(Training training);
}
