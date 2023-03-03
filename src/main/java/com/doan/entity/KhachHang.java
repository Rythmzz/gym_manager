package com.doan.entity;

import java.awt.Image;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "KHACHHANG")
public class KhachHang {
	@Id
	@Column(name = "MaKH")
	private String maKH;
	
	@Column(name = "TenKH")
	private String tenKH;
	
	@Column(name = "DiaChi")
	private String diaChi;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "SDT")
	private String sdt;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@Column(name = "NgaySinh")
	private Date ngaySinh;
	
	@Column(name = "GioiTinh")
	private String gioiTinh;
	
	@Column(name = "GhiChu")
	private String ghiChu;
	
	@Column(name = "Anh")
	private String anh;
	
	@OneToMany(mappedBy = "khachHang",fetch = FetchType.EAGER)
	private Collection<The> the;

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public Collection<The> getThe() {
		return the;
	}

	public void setThe(Collection<The> the) {
		this.the = the;
	}
	
	
}
