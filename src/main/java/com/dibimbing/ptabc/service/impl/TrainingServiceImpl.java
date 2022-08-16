package com.dibimbing.ptabc.service.impl;

import com.dibimbing.ptabc.model.Training;
import com.dibimbing.ptabc.repository.TrainingRepository;
import com.dibimbing.ptabc.service.TrainingService;
import com.dibimbing.ptabc.util.TemplateResponse;
import org.hibernate.sql.Template;
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
public class TrainingServiceImpl implements TrainingService {

    public static final Logger log = LoggerFactory.getLogger(TrainingServiceImpl.class);
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    TemplateResponse templateResponse;


    @Override
    public Map insert(Training training) {
        try {
            if (templateResponse.checkNull(training.getTema())) {
                return templateResponse.templateError("Tema Training Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(training.getNamaPengajar())) {
                return templateResponse.templateError("Nama Pengajar Tidak Boleh Kosong");
            }

            training.setCreated_date(new Date());
            training.setUpdated_date(new Date());

            Training trainingObj = trainingRepository.save(training);
            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(trainingObj);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Training training) {
        try {
            if (templateResponse.checkNull(training.getId())) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Training checkIdTraining = trainingRepository.getbyID(training.getId());
            if(templateResponse.checkNull(checkIdTraining)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            checkIdTraining.setTema(training.getTema());
            checkIdTraining.setNamaPengajar(training.getNamaPengajar());
            checkIdTraining.setUpdated_date(new Date());
            Training trainingObj = trainingRepository.save(checkIdTraining);
            log.info("{}", "Sukses Update");
            return templateResponse.templateSukses(trainingObj);
        } catch (Exception e) {
            log.info("{}", "Eror di method update: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long trainingId) {
        try {
            if (templateResponse.checkNull(trainingId)) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Training checkIdTraining = trainingRepository.getbyID(trainingId);
            if(templateResponse.checkNull(checkIdTraining)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

//            trainingRepository.deleteById(trainingId); // hard delete

            checkIdTraining.setDeleted_date(new Date()); // soft delete

            log.info("{}", "Sukses Deleted");
            return templateResponse.templateSukses("sukses deleted");
        }catch (Exception e) {
            log.info("{}", "Eror di method delete: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page,size);
            Page<Training> list = trainingRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        }catch (Exception e) {
            log.info("{}", "Eror di method getAll: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map getById(Training training) {
        try {
            if (templateResponse.checkNull(training.getId())) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Training checkIdTraining = trainingRepository.getbyID(training.getId());
            if(templateResponse.checkNull(checkIdTraining)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            log.info("{}", "Sukses getById");
            return templateResponse.templateSukses(checkIdTraining);
        }catch (Exception e) {
            log.info("{}", "Eror di method getById: " + e);
            return templateResponse.templateError(e);
        }
    }
}
