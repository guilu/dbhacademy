package com.dbhacademy.firefight.repository;

import com.dbhacademy.firefight.model.entity.Examen;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {

    @Override
    List<Examen> findAll();

}
