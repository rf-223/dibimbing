package com.dibimbing.ptabc.mybatis.controller;

import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.mybatis.model.KaryawanMybatis;
import com.dibimbing.ptabc.mybatis.service.KaryawanServiceMybatis;
import com.dibimbing.ptabc.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/sp/karyawan/")
public class KaryawanControllerMybatis {

    @Autowired
    public KaryawanServiceMybatis karyawanServiceMybatis;
    @Autowired
    public TemplateResponse templateResponse;


    @PostMapping("")
    public ResponseEntity<Map> save(@RequestBody Karyawan objModel) {
        Map map = karyawanServiceMybatis.insertKaryawanDanDetail(
                objModel.getNama(),
                objModel.getJk(),
                objModel.getDob(),
                objModel.getAlamat(),
                objModel.getStatus(),
                objModel.getKaryawanDetail().getNik(),
                objModel.getKaryawanDetail().getNpwp());

        System.out.println(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map> update(@RequestBody Karyawan objModel) {
        Map map = karyawanServiceMybatis.updateKaryawanDanDetail(
                objModel.getId(),
                objModel.getNama(),
                objModel.getJk(),
                objModel.getDob(),
                objModel.getAlamat(),
                objModel.getStatus(),
                objModel.getKaryawanDetail().getNik(),
                objModel.getKaryawanDetail().getNpwp());
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = karyawanServiceMybatis.deleteKaryawan(id);
        return new ResponseEntity<Map>(templateResponse.templateSukses("Deleted ID : " + id),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Integer id) {
        KaryawanMybatis obj = karyawanServiceMybatis.getKaryawanById(id);
        return new ResponseEntity<Map>(templateResponse.templateSukses(templateResponse.conversiToKaryawan(obj)), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> getById(@RequestParam(required = true) String nama) {
        List<KaryawanMybatis> list = karyawanServiceMybatis.getKaryawanByName("%"+nama+"%");
        return new ResponseEntity<Map>(templateResponse.templateSukses(templateResponse.conversiToKaryawan(list)), new HttpHeaders(), HttpStatus.OK);
    }


}
