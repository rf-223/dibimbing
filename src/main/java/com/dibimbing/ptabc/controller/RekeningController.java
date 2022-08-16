package com.dibimbing.ptabc.controller;

import com.dibimbing.ptabc.model.Rekening;
import com.dibimbing.ptabc.model.Training;
import com.dibimbing.ptabc.service.RekeningService;
import com.dibimbing.ptabc.service.TrainingService;
import com.dibimbing.ptabc.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/rekening")
public class RekeningController {

    @Autowired
    RekeningService rekeningService;

    @Autowired
    TemplateResponse templateResponse;



    @PostMapping("/{idKaryawan}")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@PathVariable(value = "idKaryawan") Long idKaryawan,
                                    @Valid @RequestBody Rekening objModel) {
        Map obj = rekeningService.insert(objModel, idKaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/{idKaryawan}")
    public ResponseEntity<Map> update(@PathVariable(value = "idKaryawan") Long idKaryawan,
                                    @Valid @RequestBody Rekening objModel) {
        Map obj = rekeningService.update(objModel, idKaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = rekeningService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map>list(@RequestParam() Integer page, @RequestParam Integer size) {
        Map list = rekeningService.getAll(size,page);
        return new ResponseEntity<Map>(list,new HttpHeaders(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<Map>getById(@PathVariable(value = "id")Rekening id) {
        Map obj = rekeningService.getById(id);
        return new ResponseEntity<Map>(obj,HttpStatus.OK);
    }
}
