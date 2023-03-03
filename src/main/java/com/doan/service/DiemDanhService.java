package com.doan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.entity.DiemDanh;
import com.doan.entity.GoiTap;
import com.doan.repository.DiemDanhRepository;
import com.doan.repository.GoiTapRepository;

@Service
public class DiemDanhService {
	@Autowired
	private DiemDanhRepository repo;

	public List<DiemDanh> listAll() {
		return (List<DiemDanh>) repo.findAll();
	}

	public void delete(String id) {
		repo.deleteById(id);
	}

	public Optional<DiemDanh> get(String id) {
		return repo.findById(id);
	}

	public void save(DiemDanh diemDanh) {
		repo.save(diemDanh);
	}
	public List<DiemDanh> selectByMaThe(String maThe){
		return repo.findByMaThe(maThe);
	}
}
