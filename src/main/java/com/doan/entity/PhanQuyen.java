package com.doan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHANQUYEN")
public class PhanQuyen {
	@Id
	@Column(name = "MaQuyen")
	private Integer maQuyen;
	
	@Column(name = "ChucVu")
	private String chucVu;

	public Integer getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(Integer maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}
	
	
}
