package com.dibimbing.ptabc.mybatis.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KaryawanDetailMapper implements RowMapper<KaryawanDetailMybatis> {
    @Override
    public KaryawanDetailMybatis mapRow(ResultSet rs, int rowNum) throws SQLException {
        KaryawanDetailMybatis karyawanDetailMybatis = new KaryawanDetailMybatis();
        karyawanDetailMybatis.setResid(rs.getLong("residkd"));
        karyawanDetailMybatis.setResnik(rs.getString("resnik"));
        karyawanDetailMybatis.setResnpwp(rs.getString("resnpwp"));
        karyawanDetailMybatis.setResid_karyawan(rs.getLong("resid_karyawan"));
        return karyawanDetailMybatis;
    }
}
