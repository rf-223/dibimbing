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
public interface KaryawanTrainingRepository extends PagingAndSortingRepository<KaryawanTraining, Long> {
    // JPQ
    @Query("select c from KaryawanTraining c")// nama class
    public Page<KaryawanTraining> getAllData(Pageable pageable);
    // JPQ
    @Query("select c from KaryawanTraining c WHERE c.id = :karyawanTrainingId")
    public KaryawanTraining getbyID(@Param("karyawanTrainingId") Long idKaryawanTraining);

    // jPQL
    public KaryawanTraining findOneById(Long id);

    // native query
    @Query(value = "select c from KaryawanTraining c WHERE c.id = :karyawanTrainingId", nativeQuery = true)
    public Object getbyIDNativeQuery(@Param("karyawanTrainingId") Long idKaryawanTraining);


}
