package com.dibimbing.ptabc.repository;

import com.dibimbing.ptabc.model.Rekening;
import com.dibimbing.ptabc.model.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends PagingAndSortingRepository<Rekening,Long> {

    // JPQ
    @Query("select c from Rekening c")// nama class
    public Page<Rekening> getAllData(Pageable pageable);
    // JPQ
    @Query("select c from Rekening c WHERE c.id = :rekeningId")
    public Rekening getbyID(@Param("rekeningId") Long idRekening);
    // jPQL
    public Rekening findOneById(Long id);

    // native query
    @Query(value = "select c from Rekening c WHERE c.id = :rekeningId", nativeQuery = true)
    public Object getbyIDNativeQuery(@Param("rekeningId") Long idRekening);
}
