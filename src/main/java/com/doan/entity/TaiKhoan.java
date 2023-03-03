package com.doan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
	@Id
	@Column(name="Username")
	private String userName;
	
	@Column(name="Password")
	private String passWord;
	
	
	
	@Column(name="TrangThai")
	private Integer trangThai;
	
	@OneToOne(mappedBy = "taiKhoan" ,fetch = FetchType.EAGER)
	private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn(name="maQuyen")
	private PhanQuyen phanQuyen;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	


	public Integer getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}
	
	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	
	
}
