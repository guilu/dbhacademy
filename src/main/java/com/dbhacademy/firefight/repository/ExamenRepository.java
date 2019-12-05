package com.dbhacademy.firefight.repository;

import com.dbhacademy.firefight.model.entity.Examen;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {

    @Override
    List<Examen> findAll();

}
