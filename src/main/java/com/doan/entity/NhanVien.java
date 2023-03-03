package com.doan.entity;

import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name= "NHANVIEN")
public class NhanVien {
	@Id
	@Column(name = "MaNV")
	private String maNV;
	
	@Column(name = "TenNV")
	private String tenNV;
	
	@Column(name = "DiaChi")
	private String diaChi;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "SĐT")
	private String sdt;
	
	@Column(name = "GioiTinh")
	private String gioiTinh;
	

	@OneToMany(mappedBy="nhanVien",fetch = FetchType.EAGER)
	private Collection<HoaDon> hoaDons;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userName")
	private TaiKhoan taiKhoan;

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
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

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}



	public Collection<HoaDon> getHoaDons() {
		return hoaDons;
	}

	public void setHoaDons(Collection<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	
}
