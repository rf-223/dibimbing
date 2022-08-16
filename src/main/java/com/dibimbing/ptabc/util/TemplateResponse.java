package com.dibimbing.ptabc.util;

import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanDetail;
import com.dibimbing.ptabc.mybatis.model.KaryawanMybatis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component //IOC - Beans
public class TemplateResponse {

    public Map templateSukses(Object object) {
        Map map = new HashMap();
        map.put("data", object);
        map.put("message", "suskes");
        map.put("status", "200");
        return map;
    }

    public Map templateSukses(Object objek,String message, String status){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", message);
        map.put("status",status);
        return map;
    }

    public Map templateError(Object object) {
        Map map = new HashMap();
        map.put("message", object);
        map.put("status", "404");
        return map;
    }

    public Map notFound(Object object) {
        Map map = new HashMap();
        map.put("message", object);
        map.put("status", "404");
        return map;
    }

    public boolean checkNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }

    public Karyawan conversiToKaryawan(KaryawanMybatis obj){
        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId(obj.getResid());
        objKaryawan.setNama(obj.getResnama());
        objKaryawan.setJk(obj.getResjk());
        objKaryawan.setDob(obj.getResdob());
        objKaryawan.setAlamat(obj.getResalamat());
        objKaryawan.setStatus(obj.getResstatus());
        return  objKaryawan;
    }

    public List<Karyawan> conversiToKaryawan(List<KaryawanMybatis> list){
        List<Karyawan> listKaryawan=  new ArrayList<>();
        for(KaryawanMybatis obj : list){
            Karyawan objKaryawan = new Karyawan();
            objKaryawan.setId(obj.getResid());
            objKaryawan.setNama(obj.getResnama());
            objKaryawan.setJk(obj.getResjk());
            objKaryawan.setDob(obj.getResdob());
            objKaryawan.setAlamat(obj.getResalamat());
            objKaryawan.setStatus(obj.getResstatus());
            listKaryawan.add(objKaryawan);
        }
        return  listKaryawan;
    }
}
