package com.doan.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "HOADON")
public class HoaDon {
	@Id
	@Column(name = "MasoHD")
	private String maSoHD;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@Column(name = "NgayHD")
	private Date ngayHD;
	
	
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="maThe")
	private The thehd;
	
	@ManyToOne
	@JoinColumn(name="MaNV")
	private NhanVien nhanVien;

	public String getMaSoHD() {
		return maSoHD;
	}

	public void setMaSoHD(String maSoHD) {
		this.maSoHD = maSoHD;
	}

	public Date getNgayHD() {
		return ngayHD;
	}

	public void setNgayHD(Date ngayHD) {
		this.ngayHD = ngayHD;
	}



	

	public The getThehd() {
		return thehd;
	}

	public void setThehd(The thehd) {
		this.thehd = thehd;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
}
