package com.dibimbing.ptabc.mybatis.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KaryawanMapper implements RowMapper<KaryawanMybatis> {
    @Override
    public KaryawanMybatis mapRow(ResultSet rs, int rowNum) throws SQLException {
        KaryawanMybatis karyawanMybatis = new KaryawanMybatis();
        karyawanMybatis.setResid(rs.getLong("residk"));
        karyawanMybatis.setResnama(rs.getString("resnama"));
        karyawanMybatis.setResjk(rs.getString("resjk"));
        karyawanMybatis.setResdob(rs.getDate("resdob"));
        karyawanMybatis.setResalamat(rs.getString("resalamat"));
        karyawanMybatis.setResstatus(rs.getString("resstatus"));
        karyawanMybatis.setReserordesc(rs.getString("reserordesc"));
        karyawanMybatis.setReserorcode(rs.getInt("reserorcode"));
        return karyawanMybatis;
    }
}
