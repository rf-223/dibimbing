package com.dibimbing.ptabc.mybatis.service.impl;



import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanDetail;
import com.dibimbing.ptabc.mybatis.model.KaryawanMybatis;
import com.dibimbing.ptabc.mybatis.repository.KaryawanRepositoryMybatis;
import com.dibimbing.ptabc.mybatis.service.KaryawanServiceMybatis;
import com.dibimbing.ptabc.util.QuerySP;
import com.dibimbing.ptabc.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KaryawanServiceMybatisImpl implements KaryawanServiceMybatis {

    @Autowired
    KaryawanRepositoryMybatis karyawanRepositoryMybatis;
    @Autowired
    TemplateResponse templateResponse;
    @Autowired
    QuerySP querySP;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map deleteKaryawan(Long rqid) {

        jdbcTemplate.execute(querySP.deleteKaryawan);
        Map<String, Object> map = new HashMap<>();
        map.put("resid", rqid);

        System.out.println("resid = " + map.get("resid") + "  deleted");

        karyawanRepositoryMybatis.deleteKaryawan(rqid);
        return null;
    }

    @Override
    public Map insertKaryawanDanDetail(String rqnama, String rqjk, Date rqdob, String rqalamat, String rqstatus, String rqnik, String rqnpwp) {
        jdbcTemplate.execute(querySP.saveKaryawanDanDetail);
        Map<String, Object> map = new HashMap<>();
        map.put("rqnama", rqnama);
        map.put("rqjk", rqjk);
        map.put("rqdob", rqdob);
        map.put("rqalamat", rqalamat);
        map.put("rqstatus", rqstatus);
        map.put("rqnik", rqnik);
        map.put("rqnpwp", rqnpwp);
        map.put("residk", null);
        map.put("resnama", null);
        map.put("resjk", null);
        map.put("resdob", null);
        map.put("resalamat", null);
        map.put("resstatus", null);
        map.put("residkd", null);
        map.put("resnik", null);
        map.put("resnpwp", null);
        map.put("resid_karyawan", null);
        map.put("reserordesc", null);
        map.put("reserorcode", null);

        karyawanRepositoryMybatis.insertKaryawanDanDetail(map);

        System.out.println("residk = " + map.get("residk"));
        System.out.println("resnama = " + map.get("resnama"));
        System.out.println("resjk = " + map.get("resjk"));
        System.out.println("resdob = " + map.get("resdob"));
        System.out.println("resalamat = " + map.get("resalamat"));
        System.out.println("resstatus = " + map.get("resstatus"));
        System.out.println("residkd = " + map.get("residkd"));
        System.out.println("resnik = " + map.get("resnik"));
        System.out.println("resnpwp = " + map.get("resnpwp"));
        System.out.println("resid_karyawan = " + map.get("resid_karyawan"));
        System.out.println("reserordesc = " + map.get("reserordesc"));
        System.out.println("reserorcode = " + map.get("reserorcode"));


        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId((Long) map.get("residk"));
        objKaryawan.setNama((String) map.get("resnama"));
        objKaryawan.setJk((String) map.get("resjk"));
        objKaryawan.setDob((Date) map.get("resdob"));
        objKaryawan.setAlamat((String) map.get("resalamat"));
        objKaryawan.setStatus((String) map.get("resstatus"));

        KaryawanDetail objKaryawanDetail = new KaryawanDetail();
        objKaryawanDetail.setId((Long) map.get("residkd"));
        objKaryawanDetail.setNik((String) map.get("resnik"));
        objKaryawanDetail.setNpwp((String) map.get("resnpwp"));
        objKaryawanDetail.setKaryawan(objKaryawan);

        Karyawan karyawanData = objKaryawan;
        karyawanData.setKaryawanDetail(objKaryawanDetail);

        return templateResponse.templateSukses(karyawanData, (String) map.get("reserordesc"), String.valueOf(map.get("reserorcode")));
    }

    @Override
    public Map updateKaryawanDanDetail(Long rqidk, String rqnama, String rqjk, Date rqdob, String rqalamat, String rqstatus, String rqnik, String rqnpwp) {
        jdbcTemplate.execute(querySP.updateKaryawandanDetail);
        Map<String, Object> map = new HashMap<>();
        map.put("rqidk", rqidk);
        map.put("rqnama", rqnama);
        map.put("rqjk", rqjk);
        map.put("rqdob", rqdob);
        map.put("rqalamat", rqalamat);
        map.put("rqstatus", rqstatus);
        map.put("rqnik", rqnik);
        map.put("rqnpwp", rqnpwp);
        map.put("residk", null);
        map.put("resnama", null);
        map.put("resjk", null);
        map.put("resdob", null);
        map.put("resalamat", null);
        map.put("resstatus", null);
        map.put("residkd", null);
        map.put("resnik", null);
        map.put("resnpwp", null);
        map.put("resid_karyawan", null);
        map.put("reserordesc", null);
        map.put("reserorcode", null);

        karyawanRepositoryMybatis.updateKaryawanDanDetail(map);

        System.out.println("residk = " + map.get("residk"));
        System.out.println("resnama = " + map.get("resnama"));
        System.out.println("resjk = " + map.get("resjk"));
        System.out.println("resdob = " + map.get("resdob"));
        System.out.println("resalamat = " + map.get("resalamat"));
        System.out.println("resstatus = " + map.get("resstatus"));
        System.out.println("residkd = " + map.get("residkd"));
        System.out.println("resnik = " + map.get("resnik"));
        System.out.println("resnpwp = " + map.get("resnpwp"));
        System.out.println("resid_karyawan = " + map.get("resid_karyawan"));
        System.out.println("reserordesc = " + map.get("reserordesc"));
        System.out.println("reserorcode = " + map.get("reserorcode"));


        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId((Long) map.get("residk"));
        objKaryawan.setNama((String) map.get("resnama"));
        objKaryawan.setJk((String) map.get("resjk"));
        objKaryawan.setDob((Date) map.get("resdob"));
        objKaryawan.setAlamat((String) map.get("resalamat"));
        objKaryawan.setStatus((String) map.get("resstatus"));

        KaryawanDetail objKaryawanDetail = new KaryawanDetail();
        objKaryawanDetail.setId((Long) map.get("residkd"));
        objKaryawanDetail.setNik((String) map.get("resnik"));
        objKaryawanDetail.setNpwp((String) map.get("resnpwp"));
        objKaryawanDetail.setKaryawan(objKaryawan);

        Karyawan karyawanData = objKaryawan;
        karyawanData.setKaryawanDetail(objKaryawanDetail);

        return templateResponse.templateSukses(karyawanData, (String) map.get("reserordesc"), String.valueOf(map.get("reserorcode")));
    }

    @Override
    public KaryawanMybatis getKaryawanById(int rqid) {
        return karyawanRepositoryMybatis.getKaryawanById(rqid);
    }

    @Override
    public List<KaryawanMybatis> getKaryawanByName(String rqnama) {
        return karyawanRepositoryMybatis.getKaryawanByName(rqnama);
    }

}
