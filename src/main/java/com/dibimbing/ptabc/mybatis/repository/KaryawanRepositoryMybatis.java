package com.dibimbing.ptabc.mybatis.repository;


import com.dibimbing.ptabc.mybatis.model.KaryawanMybatis;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface KaryawanRepositoryMybatis {

    @Delete(value = "call deletekaryawan(" +
            "#{rqid, mode = IN, jdbcType = BIGINT})")
    @Options(statementType = StatementType.CALLABLE)
    void deleteKaryawan(Long  rqid);

    @Insert(value = "call savekaryawandandetail(" +
            "#{rqnama, mode = IN, jdbcType = VARCHAR}," +
            "#{rqjk, mode = IN, jdbcType = VARCHAR}," +
            "#{rqdob, mode = IN, jdbcType = DATE}," +
            "#{rqalamat, mode = IN, jdbcType = VARCHAR}," +
            "#{rqstatus, mode = IN, jdbcType = VARCHAR}," +
            "#{rqnik, mode = IN, jdbcType = VARCHAR}," +
            "#{rqnpwp, mode = IN, jdbcType = VARCHAR}," +
            "#{residk, mode = INOUT, jdbcType = BIGINT}," +
            "#{resnama, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resjk, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resdob, mode = INOUT, jdbcType = DATE}," +
            "#{resalamat, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resstatus, mode = INOUT, jdbcType = VARCHAR}," +
            "#{residkd, mode = INOUT, jdbcType = BIGINT}," +
            "#{resnik, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resnpwp, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resid_karyawan, mode = INOUT, jdbcType = BIGINT}," +
            "#{reserordesc, mode = INOUT, jdbcType = VARCHAR}," +
            "#{reserorcode, mode = INOUT, jdbcType = INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void insertKaryawanDanDetail(Map<String, Object> map);

    @Update(value = "call updatekaryawandandetail(" +
            "#{rqidk, mode = IN, jdbcType = BIGINT}," +
            "#{rqnama, mode = IN, jdbcType = VARCHAR}," +
            "#{rqjk, mode = IN, jdbcType = VARCHAR}," +
            "#{rqdob, mode = IN, jdbcType = DATE}," +
            "#{rqalamat, mode = IN, jdbcType = VARCHAR}," +
            "#{rqstatus, mode = IN, jdbcType = VARCHAR}," +
            "#{rqnik, mode = IN, jdbcType = VARCHAR}," +
            "#{rqnpwp, mode = IN, jdbcType = VARCHAR}," +
            "#{residk, mode = INOUT, jdbcType = BIGINT}," +
            "#{resnama, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resjk, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resdob, mode = INOUT, jdbcType = DATE}," +
            "#{resalamat, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resstatus, mode = INOUT, jdbcType = VARCHAR}," +
            "#{residkd, mode = INOUT, jdbcType = BIGINT}," +
            "#{resnik, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resnpwp, mode = INOUT, jdbcType = VARCHAR}," +
            "#{resid_karyawan, mode = INOUT, jdbcType = BIGINT}," +
            "#{reserordesc, mode = INOUT, jdbcType = VARCHAR}," +
            "#{reserorcode, mode = INOUT, jdbcType = INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void updateKaryawanDanDetail(Map<String, Object> map);

    @Select(value = "SELECT * from getkaryawan(#{rqid})")
    KaryawanMybatis getKaryawanById(int rqid);

    @Select(value = "SELECT * from getlistkaryawan(#{rqnama})")
    List<KaryawanMybatis> getKaryawanByName(String rqnama);
}
