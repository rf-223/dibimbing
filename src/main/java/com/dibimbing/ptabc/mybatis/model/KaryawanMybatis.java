package com.dibimbing.ptabc.mybatis.model;

import lombok.Data;

import java.sql.Date;

@Data
public class KaryawanMybatis {
    private Long resid;
    String resnama;
    String resjk;
    Date resdob;
    String resalamat;
    String resstatus;
    String reserordesc;
    Integer reserorcode;
}
