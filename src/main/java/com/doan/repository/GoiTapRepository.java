package com.doan.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doan.entity.GoiTap;



public interface GoiTapRepository extends CrudRepository<GoiTap, String> {

	@Query("SELECT c FROM GoiTap c where c.lopDV.maLop = :malop")
	public List<GoiTap> findByMaLop(@Param("malop") String maLop);
	
	@Query("SELECT c FROM GoiTap c where c.lopDV.tenLop = :tenlop and c.trangThai=1  ")
	public List<GoiTap> findByTenLopTrangThai(@Param("tenlop") String tenLop);
	
	@Query("SELECT c FROM GoiTap c where c.maGoiTap = :maGT")
	public List<GoiTap> findByMaGT(@Param("maGT") String maGT);
	
	
	@Query("SELECT c FROM GoiTap c where c.lopDV.maLop = :malop AND c.tenGoiTap = :tenGoiTap")
	public List<GoiTap> findByMaLopGoiTap(@Param("malop") String maLop,@Param("tenGoiTap") String tenGoiTap );
	
	@Transactional
	@Modifying
	@Query("UPDATE GoiTap c set c.trangThai= :trangThai WHERE c.maGoiTap= :maGT")
	public int updateByMaGT(@Param("trangThai") int trangThai, @Param("maGT") String maGT) ;
}
