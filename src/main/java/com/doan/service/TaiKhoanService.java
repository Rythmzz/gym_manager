package com.doan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.entity.TaiKhoan;
import com.doan.repository.TaiKhoanRespository;

@Service
public class TaiKhoanService {
	@Autowired
	private TaiKhoanRespository repo;
	public List<TaiKhoan> listAll() {
		return (List<TaiKhoan>) repo.findAll();
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Optional<TaiKhoan> get(Integer id) {
		return repo.findById(id);
	}

	public void save(TaiKhoan taiKhoan) {
		repo.save(taiKhoan);
	}
	public List<TaiKhoan> selectByUserName(String userName){
		return repo.findByUserName(userName);
	}
	public List<TaiKhoan> selectByMaQuyen(int maQuyen){
		return repo.findByMaQuyen(maQuyen);
	}
	public int updateByUserName(int trangThai,String userName){
		return repo.updateByUserName(trangThai,userName);
	}
}
