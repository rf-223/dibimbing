package com.dibimbing.ptabc.repository;

import com.dibimbing.ptabc.model.Karyawan;
import com.dibimbing.ptabc.model.KaryawanTraining;
import com.dibimbing.ptabc.model.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends PagingAndSortingRepository<Training,Long> {
    // JPQ
    @Query("select c from Training c")// nama class
    public Page<Training> getAllData(Pageable pageable);
    // JPQ
    @Query("select c from Training c WHERE c.id = :trainingId")
    public Training getbyID(@Param("trainingId") Long idTraining);

    @Query("select c from Training c where c.tema like :nama")// nama class
    public Page<Training> findByNama(String nama, Pageable pageable);

    // jPQL
    public Training findOneById(Long id);

    // native query
    @Query(value = "select c from Training c WHERE c.id = :trainingId", nativeQuery = true)
    public Object getbyIDNativeQuery(@Param("trainingId") Long idTraining);
}

