package com.dibimbing.ptabc.service.impl;

import com.dibimbing.ptabc.dao.KaryawanTrainingRequest;
import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanTraining;
import com.dibimbing.ptabc.model.Rekening;
import com.dibimbing.ptabc.model.Training;
import com.dibimbing.ptabc.repository.KaryawanRepository;
import com.dibimbing.ptabc.repository.KaryawanTrainingRepository;
import com.dibimbing.ptabc.repository.TrainingRepository;
import com.dibimbing.ptabc.service.KaryawanTrainingService;
import com.dibimbing.ptabc.util.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class KaryawanTrainingServiceImpl implements KaryawanTrainingService {

    public static final Logger log = LoggerFactory.getLogger(KaryawanTrainingServiceImpl.class);
    @Autowired
    KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TemplateResponse templateResponse;


    @Override
    public Map insert(KaryawanTrainingRequest obj) {

        try {
            if (templateResponse.checkNull(obj.getIdKaryawan())) {
                return templateResponse.templateError("Id Karyawan Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(obj.getIdTraining())) {
                return templateResponse.templateError("Id Training Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(obj.getTraining_date())) {
                return templateResponse.templateError("Tanggal Training Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(obj.getIdKaryawan());
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Karyawan Tidak ada di database");
            }
            Training checkIdTraining = trainingRepository.getbyID(obj.getIdTraining());
            if (templateResponse.checkNull(checkIdTraining)) {
                return templateResponse.templateError("Id Training Tidak ada di database");
            }

            //insert
            KaryawanTraining karyawanTrainingData = new KaryawanTraining();
            karyawanTrainingData.setKaryawan(checkIdKaryawan);
            karyawanTrainingData.setTraining(checkIdTraining);
            karyawanTrainingData.setTraining_date(obj.getTraining_date());
            karyawanTrainingData.setCreated_date(new Date());
            karyawanTrainingData.setUpdated_date(new Date());

            KaryawanTraining karyawanTrainingObj = karyawanTrainingRepository.save(karyawanTrainingData);
            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(karyawanTrainingObj);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(KaryawanTrainingRequest obj) {

        try {
            if (templateResponse.checkNull(obj.getId())) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(obj.getIdKaryawan())) {
                return templateResponse.templateError("Id Karyawan Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(obj.getIdTraining())) {
                return templateResponse.templateError("Id Training Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(obj.getTraining_date())) {
                return templateResponse.templateError("Tanggal Training Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(obj.getIdKaryawan());
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Karyawan Tidak ada di database");
            }
            Training checkIdTraining = trainingRepository.getbyID(obj.getIdTraining());
            if (templateResponse.checkNull(checkIdTraining)) {
                return templateResponse.templateError("Id Training Tidak ada di database");
            }
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepository.getbyID(obj.getId());
            if (templateResponse.checkNull(checkIdKaryawanTraining)) {
                return templateResponse.templateError("Id Training Tidak ada di database");
            }

            //update
            checkIdKaryawanTraining.setKaryawan(checkIdKaryawan);
            checkIdKaryawanTraining.setTraining(checkIdTraining);
            checkIdKaryawanTraining.setTraining_date(obj.getTraining_date());
            checkIdKaryawanTraining.setUpdated_date(new Date());

            KaryawanTraining karyawanTrainingObj = karyawanTrainingRepository.save(checkIdKaryawanTraining);
            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(karyawanTrainingObj);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long obj) {
        try {
            if (templateResponse.checkNull(obj)) {
                return templateResponse.templateError("Id Karyawan Training Tidak Boleh Kosong");
            }
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepository.getbyID(obj);
            if (templateResponse.checkNull(checkIdKaryawanTraining)) {
                return templateResponse.templateError("Id Training Tidak ada di database");
            }

//            karyawanTrainingRepository.deleteById(obj); // hard delete

            checkIdKaryawanTraining.setDeleted_date(new Date()); // soft delete


            KaryawanTraining karyawanTrainingObj = karyawanTrainingRepository.save(checkIdKaryawanTraining);
            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(karyawanTrainingObj);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<KaryawanTraining> list = karyawanTrainingRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.info("{}", "Eror di method getAll: " + e);
            return templateResponse.templateError(e);
        }
    }

}