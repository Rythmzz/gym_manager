package com.doan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DIEMDANH")
public class DiemDanh {
	@Id
	@Column(name = "MaDD")
	private String maDD;
	

	
	@Column(name = "SoLan")
	private Integer soLan;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@Column(name = "ThoiGian")
	private Date thoiGian;
	
	@ManyToOne
	@JoinColumn(name="maThe")
	private The the;

	public String getMaDD() {
		return maDD;
	}

	public void setMaDD(String maDD) {
		this.maDD = maDD;
	}


	public Integer getSoLan() {
		return soLan;
	}

	public void setSoLan(Integer soLan) {
		this.soLan = soLan;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public The getThe() {
		return the;
	}

	public void setThe(The the) {
		this.the = the;
	}
	
	
}
