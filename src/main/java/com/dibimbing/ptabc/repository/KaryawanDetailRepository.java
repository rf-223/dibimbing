package com.dibimbing.ptabc.repository;

import com.dibimbing.ptabc.model.KaryawanDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanDetailRepository extends PagingAndSortingRepository<KaryawanDetail, Long> {

    // JPQ
    @Query("select c from KaryawanDetail c")// nama class
    public Page<KaryawanDetail> getAllData(Pageable pageable);
    // JPQ
    @Query("select c from KaryawanDetail c WHERE c.id = :karyawanDetailId")
    public KaryawanDetail getbyID(@Param("karyawanDetailId") Long idKaryawanDetail);
    // jPQL
    public KaryawanDetail findOneById(Long id);

    // native query
    @Query(value = "select c from KaryawanDetail c WHERE c.id = :karyawanDetailId", nativeQuery = true)
    public Object getbyIDNativeQuery(@Param("karyawanDetailId") Long idKaryawanDetail);
}
