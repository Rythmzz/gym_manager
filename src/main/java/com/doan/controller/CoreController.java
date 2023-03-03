package com.doan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.doan.entity.KhachHang;
import com.doan.entity.NhanVien;
import com.doan.entity.TaiKhoan;
import com.doan.service.CustomerService;
import com.doan.service.NhanVienService;
import com.doan.service.TaiKhoanService;

@Controller
public class CoreController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private NhanVienService nhanVienService;
	@RequestMapping("index")
	public String trangchu() {
		return "index";
	}
	@RequestMapping("home")
	public String index() {
		return "index";
	}
	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}
	@RequestMapping("about")
	public String about() {
		return "about";
	}
	@RequestMapping("blog")
	public String blog() {
		return "blog";
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String loginInterface(HttpSession session) {
		List<NhanVien> nhanViens= nhanVienService.listAll();
		for(NhanVien nhanVien:nhanViens) {
			try {
				if(session.getAttribute("username").equals(nhanVien.getTaiKhoan().getUserName()))
				return "redirect:manager101/home";
			}catch(Exception e) {
				return "login";
			}
		}
		return "login";
		
	}
	@Autowired
	private TaiKhoanService taiKhoanService;
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login( ModelMap modelMap,@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session) {
		List<TaiKhoan> taiKhoans = taiKhoanService.listAll();
		for(TaiKhoan item:taiKhoans){
	        //System.out.println(item.getUserName()+"---"+item.getPassWord()+"\n");
			if(username.equalsIgnoreCase(item.getUserName()) && password.equalsIgnoreCase(item.getPassWord()) && item.getTrangThai()==1 ) {
				session.setAttribute("username", username);
				session.setAttribute("maQuyen", ""+item.getPhanQuyen().getMaQuyen());
				return "redirect:manager101/home";
			}
	    }
		
		modelMap.addAttribute("error", "Wrong username or password!");
		return "login";
		
	}
}
