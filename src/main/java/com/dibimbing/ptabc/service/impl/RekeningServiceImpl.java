package com.dibimbing.ptabc.service.impl;

import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanDetail;
import com.dibimbing.ptabc.model.Rekening;
import com.dibimbing.ptabc.model.Training;
import com.dibimbing.ptabc.repository.KaryawanRepository;
import com.dibimbing.ptabc.repository.RekeningRepository;
import com.dibimbing.ptabc.repository.TrainingRepository;
import com.dibimbing.ptabc.service.RekeningService;
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
public class RekeningServiceImpl implements RekeningService {

    public static final Logger log = LoggerFactory.getLogger(RekeningServiceImpl.class);
    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    TemplateResponse templateResponse;

    public SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public SimpleDateFormat dfDOB = new SimpleDateFormat("yyyy-MM-dd");

    String a = dfDate.format(new java.sql.Date(new Date().getTime()));
    java.sql.Date getDate = new java.sql.Date(new Date().getTime());

    @Override
    public Map insert(Rekening rekening ,Long idKaryawan) {

        try {
            if (templateResponse.checkNull(rekening.getNama())) {
                return templateResponse.templateError("Nama Rekening Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(rekening.getJenis())) {
                return templateResponse.templateError("Jenis Rekening Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(rekening.getNomor())) {
                return templateResponse.templateError("Nomor Rekening Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(idKaryawan)) {
                return templateResponse.templateError("Id Karyawan Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(idKaryawan);
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }


//            //insert karyawan
            Karyawan karyawanData = new Karyawan();
            karyawanData.setId(checkIdKaryawan.getId());
            karyawanData.setNama(checkIdKaryawan.getNama());
            karyawanData.setJk(checkIdKaryawan.getJk());
            karyawanData.setDob(checkIdKaryawan.getDob());
            karyawanData.setAlamat(checkIdKaryawan.getAlamat());
            karyawanData.setStatus(checkIdKaryawan.getStatus());
            karyawanData.setCreated_date(checkIdKaryawan.getCreated_date());
            karyawanData.setUpdated_date(checkIdKaryawan.getUpdated_date());
            karyawanData.setDeleted_date(checkIdKaryawan.getDeleted_date());
            //insert karyawan data pada rekening dan insert tanggal
            rekening.setKaryawan(karyawanData);
            rekening.setCreated_date(new Date());
            rekening.setUpdated_date(new Date());

            Rekening rekeningSave = rekeningRepository.save(rekening);
            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(rekeningSave);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Rekening rekening ,Long idKaryawan) {
        try {
            if (templateResponse.checkNull(idKaryawan)) {
                return templateResponse.templateError("Id Karyawan Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(idKaryawan);
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Karyawan Tidak Ditemukan");
            }

            if (templateResponse.checkNull(rekening.getId())) {
                return templateResponse.templateError("Id Rekening Tidak Boleh Kosong");
            }

            Rekening checkIdRekening = rekeningRepository.getbyID(rekening.getId());
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Rekening  Tidak Ditemukan");
            }

            checkIdRekening.setNama(rekening.getNama());
            checkIdRekening.setJenis(rekening.getJenis());
            checkIdRekening.setNomor(rekening.getNomor());
            checkIdRekening.setUpdated_date(new Date());
            Rekening rekeningObj = rekeningRepository.save(checkIdRekening);
            log.info("{}", "Sukses Update");
            return templateResponse.templateSukses(rekeningObj);
        } catch (Exception e) {
            log.info("{}", "Eror di method update: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long rekeningId) {
        try {
            if (templateResponse.checkNull(rekeningId)) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Rekening checkIdRekening = rekeningRepository.getbyID(rekeningId);
            if(templateResponse.checkNull(checkIdRekening)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

//            rekeningRepository.deleteById(rekeningId); // hard delete

            checkIdRekening.setDeleted_date(new Date()); // soft delete

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
            Page<Rekening> list = rekeningRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        }catch (Exception e) {
            log.info("{}", "Eror di method getAll: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map getById(Rekening rekening) {
        try {
            if (templateResponse.checkNull(rekening.getId())) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Rekening checkIdRekening = rekeningRepository.getbyID(rekening.getId());
            if(templateResponse.checkNull(checkIdRekening)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            log.info("{}", "Sukses getById");
            return templateResponse.templateSukses(checkIdRekening);
        }catch (Exception e) {
            log.info("{}", "Eror di method getById: " + e);
            return templateResponse.templateError(e);
        }
    }
}
