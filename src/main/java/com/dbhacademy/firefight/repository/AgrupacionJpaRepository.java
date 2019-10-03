package com.dbhacademy.firefight.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.dbhacademy.firefight.model.entity.Agrupacion;

@Repository
public interface AgrupacionJpaRepository extends PagingAndSortingRepository<Agrupacion, Long>{
	

	@Override
	List<Agrupacion> findAll();
}
