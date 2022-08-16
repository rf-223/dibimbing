package com.dibimbing.ptabc.controller;

import com.dibimbing.ptabc.dao.KaryawanTrainingRequest;
import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanTraining;
import com.dibimbing.ptabc.model.Rekening;
import com.dibimbing.ptabc.model.Training;
import com.dibimbing.ptabc.repository.KaryawanTrainingRepository;
import com.dibimbing.ptabc.repository.TrainingRepository;
import com.dibimbing.ptabc.service.KaryawanTrainingService;
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
@RequestMapping("/v1/karyawantraining/")
public class KaryawanTrainingController {
    @Autowired
    KaryawanTrainingService karyawanTrainingService;

    @Autowired
    KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    TemplateResponse templateResponse;

    @PostMapping("")
    public ResponseEntity<Map> save(  @RequestBody KaryawanTrainingRequest objModel) {
        Map obj = karyawanTrainingService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map> update(  @RequestBody KaryawanTrainingRequest objModel ) {
        Map map = karyawanTrainingService.update(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map obj = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(obj,HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map>list(@RequestParam() Integer page, @RequestParam Integer size) {
        Map list = karyawanTrainingService.getAll(size,page);
        return new ResponseEntity<Map>(list,new HttpHeaders(),HttpStatus.OK);
    }


}
