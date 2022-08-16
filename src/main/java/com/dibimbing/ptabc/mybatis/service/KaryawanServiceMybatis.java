package com.dibimbing.ptabc.mybatis.service;

import com.dibimbing.ptabc.mybatis.model.KaryawanMybatis;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface KaryawanServiceMybatis {

    Map deleteKaryawan(Long rqid);


    Map insertKaryawanDanDetail(String rqnama, String rqjk, Date rqdob, String rqalamat,
                                String rqstatus, String rqnik, String rqnpwp);

    Map updateKaryawanDanDetail(Long rqidk,String rqnama, String rqjk, Date rqdob, String rqalamat,
                                String rqstatus, String rqnik, String rqnpwp);

    KaryawanMybatis getKaryawanById(int rqid);


    List<KaryawanMybatis> getKaryawanByName(String rqnama);
}
