package com.dbhacademy.firefight.repository;

import com.dbhacademy.firefight.model.entity.Examen;
import com.dbhacademy.firefight.model.entity.Pregunta;
import com.dbhacademy.firefight.model.entity.Respuesta;
import com.dbhacademy.firefight.model.entity.Tema;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntaJpaRepository extends PagingAndSortingRepository<Pregunta, Long> {

    @Override
    List<Pregunta> findAll();
    List<Pregunta> findByTema(Optional<Tema> tema);
    List<Pregunta> findByExamen(Optional<Examen> examen);

    List<Pregunta> findByTextoContainingIgnoreCase(String textoABuscar);

    Pregunta findPreguntaByRespuestasContains(Optional<Respuesta> respuesta);


}
