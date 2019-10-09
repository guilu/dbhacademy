package com.dbhacademy.firefight.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbhacademy.firefight.model.entity.Agrupacion;
import com.dbhacademy.firefight.model.entity.Tema;

@Repository
public interface TemaJpaRepository extends PagingAndSortingRepository<Tema, Long>{
	
	@Override
	List<Tema> findAll();

	List<Tema> findByAgrupacion(Agrupacion agrupacion);

}
