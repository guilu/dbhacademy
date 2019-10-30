package com.dbhacademy.firefight.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Respuesta;

@Repository
public interface RespuestaJpaRepository extends PagingAndSortingRepository<Respuesta, Long> {

	List<Respuesta> findAll();
	List<Respuesta> findByPregunta(Optional<Pregunta> pregunta);
}
