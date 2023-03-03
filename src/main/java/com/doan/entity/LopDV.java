package com.doan.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LOPDV")
public class LopDV {
	@Id
	@Column(name = "MaLop")
	private String maLop;
	
	@Column(name = "TenLop")
	private String tenLop;
	
	@OneToMany(mappedBy = "lopDV")
	private Collection<GoiTap> goiTap;

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public Collection<GoiTap> getGoiTap() {
		return goiTap;
	}

	public void setGoiTap(Collection<GoiTap> goiTap) {
		this.goiTap = goiTap;
	}	
	
}
