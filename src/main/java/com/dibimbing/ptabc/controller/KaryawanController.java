package com.dibimbing.ptabc.controller;

import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.repository.KaryawanRepository;
import com.dibimbing.ptabc.service.KaryawanService;
import com.dibimbing.ptabc.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/karyawan")
public class KaryawanController {

    @Autowired
    KaryawanService karyawanService;
    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    TemplateResponse templateResponse;

    @PostMapping("/add")
    public ResponseEntity<Map> save(@RequestBody Karyawan objModel) {
        Map obj = karyawanService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Karyawan objModel) {
        Map obj = karyawanService.update(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map obj = karyawanService.delete(id);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByNama(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String nama) {
        Map map = new HashMap();
        Page<Karyawan> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin row

        if (nama != null && !nama.isEmpty()) {
            list = karyawanRepository.findByNama("%" + nama + "%", show_data);
        } else {
            list = karyawanRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(list),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getById(@PathVariable(value = "id") Karyawan id) {
        Map obj = karyawanService.getById(id);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

}
