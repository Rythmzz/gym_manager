package com.doan.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.doan.entity.KhachHang;
import com.doan.entity.The;

import java.util.List;

@Repository
public interface CustomerRepository  extends CrudRepository<KhachHang, String> {
	@Query("SELECT c FROM KhachHang c where c.maKH = :makh")
	public List<KhachHang> findByMaKH(@Param("makh") String maKH);
	@Query("SELECT c FROM KhachHang c where c.email = :email")
	public List<KhachHang> findByEmail(@Param("email") String email);
	@Query("FROM KhachHang c ORDER BY c.maKH desc")
	public List<KhachHang> findAllSortMaKH();
	
}
