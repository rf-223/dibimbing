package com.dibimbing.ptabc.service.impl;

import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanDetail;
import com.dibimbing.ptabc.repository.KaryawanDetailRepository;
import com.dibimbing.ptabc.repository.KaryawanRepository;
import com.dibimbing.ptabc.service.KaryawanService;
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
public class KaryawanServiceImpl implements KaryawanService {

    public static final Logger log = LoggerFactory.getLogger(KaryawanServiceImpl.class);
    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    KaryawanDetailRepository karyawanDetailRepository;

    @Autowired
    TemplateResponse templateResponse;

    public SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public SimpleDateFormat dfDOB = new SimpleDateFormat("yyyy-MM-dd");

    String a = dfDate.format(new java.sql.Date(new Date().getTime()));
    java.sql.Date getDate = new java.sql.Date(new Date().getTime());

    @Override
    public Map insert(Karyawan karyawan) {
        try {
            if (templateResponse.checkNull(karyawan.getNama())) {
                return templateResponse.templateError("Nama Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getJk())) {
                return templateResponse.templateError("Jenis Kelamin Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getDob())) {
                return templateResponse.templateError("Tanggal Lahir Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getAlamat())) {
                return templateResponse.templateError("Alamat Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getStatus())) {
                return templateResponse.templateError("Status Tidak Boleh Kosong");
            }


            karyawan.setCreated_date(new Date());
            karyawan.setUpdated_date(new Date());


            //insert karyawan
            Karyawan karyawanData = new Karyawan();
            karyawanData.setId(karyawan.getId());
            karyawanData.setNama(karyawan.getNama());
            karyawanData.setJk(karyawan.getJk());
            karyawanData.setDob(karyawan.getDob());
            karyawanData.setAlamat(karyawan.getAlamat());
            karyawanData.setStatus(karyawan.getStatus());
            karyawanData.setCreated_date(new Date());
            karyawanData.setUpdated_date(new Date());
            Karyawan karyawanObj = karyawanRepository.save(karyawanData);

            //insert karyawan  detail : set FK dari karyawan
            KaryawanDetail karyawanDetailData = new KaryawanDetail();
            karyawanDetailData.setId(karyawan.getKaryawanDetail().getId());
            karyawanDetailData.setNik(karyawan.getKaryawanDetail().getNik());
            karyawanDetailData.setNpwp(karyawan.getKaryawanDetail().getNpwp());
            karyawanDetailData.setKaryawan(karyawanData); // insert karyawan
            KaryawanDetail karyawanDetailObj = karyawanDetailRepository.save(karyawanDetailData);

            log.info("{}", "Sukses Insert");
            return templateResponse.templateSukses(karyawanData);
        } catch (Exception e) {
            log.info("{}", "Eror: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map update(Karyawan karyawan) {
        try {
            if (templateResponse.checkNull(karyawan.getId())) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }
            if (templateResponse.checkNull(karyawan.getKaryawanDetail())) {
                return templateResponse.templateError("Karyawan Detail Tidak Boleh Kosong");
            }


            Karyawan karyawanUpdate = karyawanRepository.getbyID(karyawan.getId());
            if (templateResponse.checkNull(karyawanUpdate)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            //insert karyawan
            Karyawan karyawanDataUpdate = new Karyawan();
            karyawanDataUpdate.setId(karyawan.getId());
            karyawanDataUpdate.setNama(karyawan.getNama());
            karyawanDataUpdate.setJk(karyawan.getJk());
            karyawanDataUpdate.setDob(karyawan.getDob());
            karyawanDataUpdate.setAlamat(karyawan.getAlamat());
            karyawanDataUpdate.setStatus(karyawan.getStatus());
            karyawanDataUpdate.setCreated_date(new Date());
            karyawanDataUpdate.setUpdated_date(new Date());
            Karyawan karyawanObj = karyawanRepository.save(karyawanDataUpdate);

            //insert karyawan  detail : set FK dari karyawan
            KaryawanDetail karyawanDetailData = new KaryawanDetail();
            karyawanDetailData.setId(karyawan.getKaryawanDetail().getId());
            karyawanDetailData.setNik(karyawan.getKaryawanDetail().getNik());
            karyawanDetailData.setNpwp(karyawan.getKaryawanDetail().getNpwp());
            karyawanDetailData.setKaryawan(karyawanDataUpdate); // insert karyawan
            KaryawanDetail karyawanDetailObj = karyawanDetailRepository.save(karyawanDetailData);

            log.info("{}", "Sukses Update");
            return templateResponse.templateSukses(karyawanObj);
        } catch (Exception e) {
            log.info("{}", "Eror di method update: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map delete(Long karyawan) {
        try {
            if (templateResponse.checkNull(karyawan)) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawan);
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

//            karyawanRepository.deleteById(karyawan); // hard delete (delete permanent)
            checkIdKaryawan.setDeleted_date(new Date()); // soft delete
            karyawanRepository.save(checkIdKaryawan);

            log.info("{}", "Sukses Deleted");
            return templateResponse.templateSukses("sukses deleted");
        } catch (Exception e) {
            log.info("{}", "Eror di method delete: " + e);
            return templateResponse.templateError(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada eror di method getAll:" + e);
            return templateResponse.templateError(e);
        }
    }


    @Override
    public Map getById(Karyawan karyawan) {
        try {
            if (templateResponse.checkNull(karyawan)) {
                return templateResponse.templateError("Id Tidak Boleh Kosong");
            }

            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawan.getId());
            if (templateResponse.checkNull(checkIdKaryawan)) {
                return templateResponse.templateError("Id Tidak Ditemukan");
            }

            log.info("{}", "Sukses getById");
            return templateResponse.templateSukses(checkIdKaryawan);
        } catch (Exception e) {
            log.info("{}", "Eror di method getById: " + e);
            return templateResponse.templateError(e);
        }
    }
}
