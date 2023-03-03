package com.doan.repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.doan.entity.The;

public interface TheRepository extends CrudRepository<The, String> {
//	@Query("SELECT c FROM The c where c.maKH = :makh")
//	public Optional<The> findByMaKH1(@Param("makh") String maKH);
	@Query("SELECT c FROM The c where c.khachHang.maKH = :makh ")
	public List<The> findByMaKHNotSort(@Param("makh") String maKH);
	@Query("SELECT c FROM The c where c.khachHang.maKH = :makh order by c.ngayDK desc")
	public List<The> findByMaKH(@Param("makh") String maKH);
	@Query("FROM The c ORDER BY c.maThe desc")
	public List<The> findAllSortMaThe();
	@Query("SELECT c FROM The c WHERE ( c.ngayDK BETWEEN :namBD AND :namKT ) ")
	public List<The> findBetweenNgayDK(@Param("namBD") Date namBD,@Param("namKT") Date namKT);
	
	@Query("SELECT c FROM The c WHERE ( c.ngayDK BETWEEN :namBD AND :namKT ) AND c.goiTap.lopDV.tenLop =:tenLopDV  ")
	public List<The> findBetweenNgayDKTenLop(@Param("namBD") Date namBD,@Param("namKT") Date namKT,@Param("tenLopDV") String tenMaLop);
	
	@Query("SELECT c FROM The c WHERE ( c.ngayDK BETWEEN :namBD AND :namKT)  AND c.khachHang.tenKH = :tenKH")
	public List<The> findBetweenNgayDKTenKH(@Param("namBD") Date namBD,@Param("namKT") Date namKT, @Param("tenKH") String tenKH);
	
	@Query("SELECT c FROM The c where c.ngayKT < :ngayHT  ")
	public List<The> checkByNgayKT(@Param("ngayHT") Date ngayHT);
	
	@Query("SELECT c FROM The c where c.maThe = :mathe")
	public List<The> findByMaThe(@Param("mathe") String maThe);
	@Query("SELECT c FROM The c where c.goiTap.maGoiTap = :maGT")
	public List<The> findByMaGT(@Param("maGT") String maGT);
	
	@Query("SELECT c FROM The c where c.trangThai = :trangThai")
	public List<The> findByTrangThai(@Param("trangThai") String trangThai);
	@Transactional
	@Modifying
	@Query("UPDATE The c set c.trangThai= :trangthai WHERE c.maThe= :mathe")
	public int updateByMaThe(@Param("trangthai") String trangThai, @Param("mathe") String maThe) ;
	@Temporal(TemporalType.DATE)
	@Query("UPDATE The c set c.ngayBD= :ngayBD  WHERE c.maThe= :mathe")
	public int updateNgayBDByMaThe(@Param("ngayBD") Date ngayBD ,@Param("mathe") String maThe ) ;
	@Transactional
	@Modifying
	@Query("UPDATE The c set c.ngayBD= :ngayBD ,   c.ngayKT= :ngayKT   WHERE c.maThe= :mathe")
	public int updateNgayByMaThe( @Param("ngayBD") Date ngayBD , @Param("ngayKT") Date ngayKT ,@Param("mathe") String maThe ) ;
	@Query("SELECT c FROM  The c WHERE  c.ngayDK LIKE CONCAT(:nam,'%') ORDER BY c.goiTap.gia desc ")//
	public List<The> findBetweenNamSortGiaTien(@Param("nam") String nam);
	
	
//	@Query("SELECT c FROM The c order by c. desc")
//	public List<The> sortByData();
}
