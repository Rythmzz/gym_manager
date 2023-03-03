package com.doan.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name= "THE")
public class The {
	@Id
	@Column(name="MaThe")
	private String maThe;
	
	
	
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@Column(name="NgayDK")
	private Date ngayDK;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@Column(name="NgayBD")
	private Date ngayBD;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@Column(name="NgayKT")
	private Date ngayKT;
	
	@Column(name="Trangthai")
	private String trangThai;
	
	@OneToMany(mappedBy = "the")
	private Collection<DiemDanh> diemDanhCollection;
	
	@ManyToOne
	@JoinColumn(name="MaKH")
	private KhachHang khachHang;
	
	@ManyToOne
	@JoinColumn(name="MaGoiTap")
	private GoiTap goiTap;

	public String getMaThe() {
		return maThe;
	}

	public void setMaThe(String maThe) {
		this.maThe = maThe;
	}

	

	
	public GoiTap getGoiTap() {
		return goiTap;
	}

	public void setGoiTap(GoiTap goiTap) {
		this.goiTap = goiTap;
	}

	public Date getNgayDK() {
		return ngayDK;
	}

	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}
	
	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Collection<DiemDanh> getDiemDanhCollection() {
		return diemDanhCollection;
	}

	public void setDiemDanhCollection(Collection<DiemDanh> diemDanhCollection) {
		this.diemDanhCollection = diemDanhCollection;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
}
