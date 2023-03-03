package com.doan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doan.entity.GoiTap;
import com.doan.entity.NhanVien;


public interface NhanVienRepository extends CrudRepository<NhanVien, String>{
	@Query("SELECT c FROM NhanVien c where c.maNV = :maNV")
	public List<NhanVien> findByMaNV(@Param("maNV") String maNV);
	@Query("SELECT c FROM NhanVien c where c.email = :email")
	public List<NhanVien> findByEmail(@Param("email") String email);
	@Query("SELECT c FROM NhanVien c where c.taiKhoan.userName = :userName")
	public List<NhanVien> findByUserName(@Param("userName") String userName);
}
