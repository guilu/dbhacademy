package com.dbhacademy.firefight.repository;

import com.dbhacademy.firefight.model.entity.Respuesta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaJpaRepository extends PagingAndSortingRepository<Respuesta, Long> {

}
