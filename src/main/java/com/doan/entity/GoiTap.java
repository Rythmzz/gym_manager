package com.doan.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="GOITAP")
public class GoiTap {
	@Id
	@Column(name="MaGoiTap")
	private String maGoiTap;
	
	@Column(name="TrangThai")
	private Integer trangThai;
	
	@Column(name="TenGoiTap")
	private String tenGoiTap;

	@Column(name="ThoiHan")
	private Integer thoiHan;
	
	@Column(name="Gia")
	private Float gia;
	
	@ManyToOne
	@JoinColumn(name="MaLop")
	private LopDV lopDV;
	
	@OneToMany(mappedBy = "goiTap",fetch = FetchType.EAGER)
	private Collection<The> the;

	public String getMaGoiTap() {
		return maGoiTap;
	}

	public void setMaGoiTap(String maGoiTap) {
		this.maGoiTap = maGoiTap;
	}

	public String getTenGoiTap() {
		return tenGoiTap;
	}
	
	
	

	public void setTenGoiTap(String tenGoiTap) {
		this.tenGoiTap = tenGoiTap;
	}

	
	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}
	
	public Integer getThoiHan() {
		return thoiHan;
	}

	public void setThoiHan(Integer thoiHan) {
		this.thoiHan = thoiHan;
	}

	public Float getGia() {
		return gia;
	}

	public void setGia(Float gia) {
		this.gia = gia;
	}


	public LopDV getLopDV() {
		return lopDV;
	}

	public void setLopDV(LopDV lopDV) {
		this.lopDV = lopDV;
	}

	public Collection<The> getThe() {
		return the;
	}

	public void setThe(Collection<The> the) {
		this.the = the;
	}
	
}
