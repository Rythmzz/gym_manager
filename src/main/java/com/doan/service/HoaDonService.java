package com.doan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.entity.HoaDon;
import com.doan.entity.TaiKhoan;
import com.doan.repository.HoaDonRepository;

@Service
public class HoaDonService {
	@Autowired
	private HoaDonRepository repo;
	public List<HoaDon> listAll() {
		return (List<HoaDon>) repo.findAll();
	}

	public void delete(String id) {
		repo.deleteById(id);
	}

	public Optional<HoaDon> get(String id) {
		return repo.findById(id);
	}

	public void save(HoaDon hoaDon) {
		repo.save(hoaDon);
	}
	public List<HoaDon> selectSortMaSoHD(){
		return repo.findAllSortMaSoHD();
	}
	public List<HoaDon> selectByYear(String nam){
		return repo.findByYear(nam);
	}
	public List<HoaDon> findBetweenNamSortGiaTien(String nam){
		return repo.findBetweenNamSortGiaTien(nam);
	}
}
