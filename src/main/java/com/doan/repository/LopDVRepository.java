package com.doan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doan.entity.LopDV;

public interface LopDVRepository extends CrudRepository<LopDV, String> {

	@Query("SELECT c FROM LopDV c where c.tenLop = :tenLop")
	public List<LopDV> findByTenLop(@Param("tenLop") String tenLop);
	@Query("SELECT c FROM LopDV c where c.maLop = :maLop")
	public List<LopDV> findByMaLop(@Param("maLop") String maLop);
}