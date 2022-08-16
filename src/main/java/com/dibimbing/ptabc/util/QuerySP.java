package com.dibimbing.ptabc.util;

import org.springframework.stereotype.Component;

@Component
public class QuerySP {

    public String saveKaryawanDanDetail = "CREATE OR REPLACE PROCEDURE public.savekaryawandandetail(IN rqnama character varying,\n" +
            "IN rqjk character varying,\n" +
            "IN rqdob date,\n" +
            "IN rqalamat text,\n" +
            "IN rqstatus character varying,\n" +
            "IN rqnik character varying,\n" +
            "IN rqnpwp character varying,\n" +
            "INOUT residk bigint,\n" +
            "INOUT resnama character varying,\n" +
            "INOUT resjk character varying,\n" +
            "INOUT resdob date,\n" +
            "INOUT resalamat text,\n" +
            "INOUT resstatus character varying,\n" +
            "INOUT residkd bigint,\n" +
            "INOUT resnik character varying,\n" +
            "INOUT resnpwp character varying,\n" +
            "INOUT resid_karyawan bigint,\n" +
            "INOUT reserordesc character varying,\n" +
            "INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "\n" +
            "\tinsert into public.karyawan\n" +
            "\t(id,nama,jk,dob,alamat,status,created_date,updated_date)\n" +
            "\tselect nextval('karyawan_id_seq') ,\n" +
            "\trqnama,\n" +
            "\trqjk,\n" +
            "\trqdob,\n" +
            "\trqalamat,\n" +
            "\trqstatus,\n" +
            "\tNOW(),\n" +
            "\tNOW()\n" +
            "\treturning id into residk;\n" +
            "\t\n" +
            "\tinsert into public.karyawan_detail\n" +
            "\t(id,nik,npwp,id_karyawan)\n" +
            "\tselect nextval('karyawan_detail_id_seq') ,\n" +
            "\trqnik,\n" +
            "\trqnpwp,\n" +
            "\tresidk\n" +
            "\treturning id into residkd;\n" +
            "\n" +
            "\tresnama = rqnama;\n" +
            "\tresjk = rqjk;\n" +
            "\tresdob = rqdob;\n" +
            "\tresalamat = rqalamat;\n" +
            "\tresstatus = rqstatus;\n" +
            "\tresnik = rqnik;\n" +
            "\tresnpwp = rqnpwp;\n" +
            "\tresid_karyawan = residk;\n" +
            "\treserordesc = 'sukses';\n" +
            "\treserorcode = 200;\n" +
            "\n" +
            "\tcommit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String updateKaryawandanDetail = "CREATE OR REPLACE PROCEDURE public.updatekaryawandandetail(" +
            "IN rqidk bigint,\n" +
            "IN rqnama character varying,\n" +
            "IN rqjk character varying,\n" +
            "IN rqdob date,\n" +
            "IN rqalamat text,\n" +
            "IN rqstatus character varying,\n" +
            "IN rqnik character varying,\n" +
            "IN rqnpwp character varying,\n" +
            "INOUT residk bigint,\n" +
            "INOUT resnama character varying,\n" +
            "INOUT resjk character varying,\n" +
            "INOUT resdob date,\n" +
            "INOUT resalamat text,\n" +
            "INOUT resstatus character varying,\n" +
            "INOUT residkd bigint,\n" +
            "INOUT resnik character varying,\n" +
            "INOUT resnpwp character varying,\n" +
            "INOUT resid_karyawan bigint,\n" +
            "INOUT reserordesc character varying,\n" +
            "INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "\n" +
            "update public.karyawan \n" +
            "\tset nama = rqnama,\n" +
            "\tjk = rqjk,\n" +
            "\tdob = rqdob,\n" +
            "\talamat = rqalamat,\n" +
            "\tstatus = rqstatus,\n" +
            "\tupdated_date = NOW()\n" +
            "\twhere id = rqidk\n" +
            "\treturning id into residk;\n" +
            "\n" +
            "\tupdate public.karyawan_detail \n" +
            "\tset nik = rqnik,\n" +
            "\tnpwp = rqnpwp\n" +
            "\twhere id_karyawan = residk\n" +
            "\treturning id into residkd;\n" +
            "\n" +
            "\tresnama = rqnama;\n" +
            "\tresjk = rqjk;\n" +
            "\tresdob = rqdob;\n" +
            "\tresalamat = rqalamat;\n" +
            "\tresstatus = rqstatus;\n" +
            "\tresnik = rqnik;\n" +
            "\tresnpwp = rqnpwp;\n" +
            "\tresid_karyawan = residk;\n" +
            "\treserordesc = 'sukses';\n" +
            "\treserorcode = 200;\n" +
            "\n" +
            "\tcommit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String deleteKaryawan = "CREATE OR REPLACE PROCEDURE public.deletekaryawan(INOUT rqid bigint)\n" +
            "\tLANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "\t\tdelete from public.karyawan where id = rqid;\n" +
            "\tcommit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String getKaryawanById = "CREATE OR REPLACE FUNCTION public.getkaryawan(IN rqidk bigint)\n" +
            " RETURNS TABLE(resid bigint," +
            " resnama character varying," +
            " resjk character varying," +
            " resdob date," +
            " resalamat character varying," +
            " resstatus character varying)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $function$\n" +
            "\tBEGIN\n" +
            "\n" +
            "\t\treturn query\n" +
            "\t\t\tselect id,nama,jk,dob,alamat,status \n" +
            "\t\t\tfrom public.karyawan\n" +
            "\t\t\twhere id = rqidk;\n" +
            "\tEND;\n" +
            "$function$\n" +
            ";\n";

    public String getKaryawanByName = "CREATE OR REPLACE FUNCTION public.getlistkaryawan(rqnama character varying)\n" +
            " RETURNS TABLE(resid bigint,\n" +
            " resnama character varying,\n" +
            " resjk character varying,\n" +
            " resdob date,\n" +
            " resalamat text,\n" +
            " resstatus character varying)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $function$\n" +
            "\tBEGIN\n" +
            "\t\treturn query\n" +
            "\t\t\tselect id,nama,jk,dob,alamat,status \n" +
            "\t\t\tfrom public.karyawan\n" +
            "\t\t\twhere nama ilike rqnama;\n" +
            "\tEND;\n" +
            "$function$\n" +
            ";\n";
}
