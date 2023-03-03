package com.doan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doan.entity.PhanQuyen;
import com.doan.entity.TaiKhoan;

public interface PhanQuyenRepository  extends CrudRepository<PhanQuyen,String> {
	@Query("SELECT c FROM PhanQuyen c where c.maQuyen = :maQuyen")
	public List<PhanQuyen> findByMaQuyen(@Param("maQuyen") int maQuyen);
}
