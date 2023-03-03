package com.doan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doan.entity.DiemDanh;

public interface DiemDanhRepository extends CrudRepository<DiemDanh,String> {
	@Query("SELECT c FROM DiemDanh c where c.the.maThe = :maThe")
	public List<DiemDanh> findByMaThe(@Param("maThe") String maThe);
}
