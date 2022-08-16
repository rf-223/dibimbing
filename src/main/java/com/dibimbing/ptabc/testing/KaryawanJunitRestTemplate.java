package com.dibimbing.ptabc.testing;



import com.dibimbing.ptabc.mybatis.service.KaryawanServiceMybatis;
import com.dibimbing.ptabc.util.QuerySP;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KaryawanJunitRestTemplate {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    KaryawanServiceMybatis karyawanServiceMybatis;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    QuerySP querySP;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void insert() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n"+
                "    \"nama\":\"Dadang 2\",\n" +
                "    \"jk\":\"Laki-laki\",\n" +
                "    \"dob\":\"2001-01-01\",\n" +
                "    \"alamat\":\"Jalan Kemayoran Jakarta Pusat\",\n" +
                "    \"status\":\"Kontrak\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);
        System.out.println("bodyTesting  =" + bodyTesting);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/karyawan/add", HttpMethod.POST, entity, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n"+
                "    \"id\":\"2\",\n" +
                "    \"nama\":\"Diding\",\n" +
                "    \"jk\":\"Laki-laki\",\n" +
                "    \"dob\":\"2001-01-01\",\n" +
                "    \"alamat\":\"Jalan Senen Jakarta Pusat\",\n" +
                "    \"status\":\"Kontrak\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);
        System.out.println("bodyTesting  =" + bodyTesting);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/sp/karyawan/update", HttpMethod.PUT, entity, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        int id = 3;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/karyawan/delete/" + id, HttpMethod.DELETE, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void getKaryawanById() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        Integer id = 2;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9090/api/v1/karyawan/"+id, HttpMethod.GET, null, String.class);
        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }
//
//    @Test
//    public void getKaryawanByName() throws IOException {
//        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            jdbcTemplate.execute(querySP.getKaryawanByName);
//            System.out.println("response  1=" + karyawanServiceMybatis.getKaryawanByName("%di%"));
//            List<KaryawanMybatis> list = karyawanServiceMybatis.getKaryawanByName("%di%");
//            for (KaryawanMybatis data : list) {
//                System.out.println("data ke ============== ");
//                System.out.println("id=" + data.getResid());
//                System.out.println("nama=" + data.getResnama());
//                System.out.println("jk=" + data.getResjk());
//                System.out.println("dob=" + data.getResdob());
//                System.out.println("alamat=" + data.getResalamat());
//                System.out.println("status=" + data.getResstatus());
//            }
//        } finally {
//            session.close();
//        }
//    }

}
