package com.doan.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.entity.KhachHang;
import com.doan.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;

	public List<KhachHang> listAll() {
		return (List<KhachHang>) repo.findAll();
	}

	public void delete(String id) {
		repo.deleteById(id);
	}

	public Optional<KhachHang> get(String id) {
		return repo.findById(id);
	}

	public void save(KhachHang customer) {
		repo.save(customer);
	}
	
	public List<KhachHang> selectByMaKH(String maKH) {
		return repo.findByMaKH(maKH);
	}
	public List<KhachHang> selectByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public List<KhachHang> selectSortMaKh() {
		return repo.findAllSortMaKH();
	}
}
