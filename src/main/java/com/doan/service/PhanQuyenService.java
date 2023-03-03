package com.doan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.entity.NhanVien;
import com.doan.entity.PhanQuyen;
import com.doan.entity.TaiKhoan;
import com.doan.repository.NhanVienRepository;
import com.doan.repository.PhanQuyenRepository;

@Service
public class PhanQuyenService {
	@Autowired
	private PhanQuyenRepository repo;
	public List<PhanQuyen> listAll() {
		return (List<PhanQuyen>) repo.findAll();
	}
	
	public void delete(String id) {
		repo.deleteById(id);
	}

	public Optional<PhanQuyen> get(String id) {
		return repo.findById(id);
	}

	public void save(PhanQuyen phanQuyen) {
		repo.save(phanQuyen);
	}
	public List<PhanQuyen> selectByMaQuyen(int maQuyen){
		return repo.findByMaQuyen(maQuyen);
	}
}
