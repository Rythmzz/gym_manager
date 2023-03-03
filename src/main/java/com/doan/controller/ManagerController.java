package com.doan.controller;



import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.doan.entity.DiemDanh;
import com.doan.entity.GoiTap;
import com.doan.entity.HoaDon;
import com.doan.entity.KhachHang;
import com.doan.entity.LopDV;
import com.doan.entity.NhanVien;
import com.doan.entity.PhanQuyen;
import com.doan.entity.TaiKhoan;
import com.doan.entity.The;
import com.doan.mail.MyConstants.MyConstants;
import com.doan.repository.LopDVRepository;
import com.doan.service.CustomerService;
import com.doan.service.DiemDanhService;
import com.doan.service.GoiTapService;
import com.doan.service.HoaDonService;
import com.doan.service.LopDVService;
import com.doan.service.NhanVienService;
import com.doan.service.PhanQuyenService;
import com.doan.service.TaiKhoanService;
import com.doan.service.TheService;

@Controller
@RequestMapping("manager101")
public class ManagerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PhanQuyenService phanQuyenService;
	@Autowired
	private NhanVienService nhanVienService;
	@Autowired
	private TheService theService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private HoaDonService hoaDonService;
	@Autowired
	private GoiTapService goiTapService;
	@Autowired
	private LopDVService lopDVService;
	@Autowired
	private DiemDanhService diemDanhService;
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	ServletContext servletContext;
	@RequestMapping("home")
	public ModelAndView home(HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mav = new ModelAndView("dashboard");
		// check : Only admin can access this route
		
		
		if(!session.getAttribute("maQuyen").equals("0")) {
			response.sendRedirect("dangky");
		}
		
		
		List <The> thes=theService.selectByTrangThai("Chưa Thanh Toán");
		LocalDate localDate = LocalDate.now();
	      String date=""+localDate;
	      String[] dates=	date.split("-");
		
		
		String namHienTai = dates[0];
		String thangHienTai=dates[1];
		float[] danhThuT= {0,0,0,0,0,0,0,0,0,0,0,0};
		float[] doanhThuTCN= {0,0,0};
		int[] soLuongDV= {0,0,0,0};
		int[] soLuongKHT= {0,0,0,0,0,0,0,0,0,0,0,0};
		
		
		String[] fieldBDNCN= {"","",""};
		
		int i;
		List <HoaDon> hoaDons;
		for(i=1;i<=12;i++) {
			//System.out.println("sodem= "+i);
				 if(String.valueOf(i).length()==1) {hoaDons=hoaDonService.selectByYear(""+namHienTai+"-0"+i+"-");}
				 else{hoaDons=hoaDonService.selectByYear(""+namHienTai+"-"+i+"-");}
				 if(hoaDons.isEmpty()) continue;
				 
			//hoaDons.forEach(hoaDon-> danhThuT[i]+= hoaDon.getThehd().getGoiTap().getGia());
				 int a= 0 ;
				 List<String> sLKHs=new ArrayList<>(); // tao list ao
			     List<String> sLKHs1 = new ArrayList<>();// list remove duplicate
			while(true) {
				
				//System.out.println("sodem trong while= "+i);
				try {
				  //System.out.println("hades["+i+"]="+"["+a+"]="+hoaDons.get(a).getThehd().getGoiTap().getGia());
					
					// công don tien trong thang
				danhThuT[i-1]+=hoaDons.get(a).getThehd().getGoiTap().getGia();
				sLKHs.add(hoaDons.get(a).getThehd().getKhachHang().getMaKH());
				
				a++;
				}catch (Exception e) {break;}
			}
			// tien hanh duplicate đếm sô luong khach hang
			Set<String> store = new HashSet<>();
			
	        for (String sLKH : sLKHs) {
	            if (!store.add(sLKH) == false) {
	            	sLKHs1.add(""+sLKH);
	            }
	        }
	        // dem so luong
	        soLuongKHT[i-1]=sLKHs1.size();
			
		}
		
		//============== danh thu cao nhat ============= 
		float maxDT = danhThuT[0];
		int maxIndexDT=0;
        for ( i = 1; i < danhThuT.length; i++) {
            if (danhThuT[i] > maxDT) {  
            	maxDT = danhThuT[i];maxIndexDT=i; 
            }
        }
        
        if(maxIndexDT==0) {
        	doanhThuTCN[0]=danhThuT[maxIndexDT];
        	doanhThuTCN[1]=danhThuT[maxIndexDT+1];
        	doanhThuTCN[2]=danhThuT[maxIndexDT+2];
        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT+1)+"'";
        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT+2)+"'";
        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT+3)+"'";
        	
        }else if(maxIndexDT==11) {
        	doanhThuTCN[0]=danhThuT[maxIndexDT-2];
        	doanhThuTCN[1]=danhThuT[maxIndexDT-1];
        	doanhThuTCN[2]=danhThuT[maxIndexDT];
        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT+1)+"'";
        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT)+"'";
        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT-1)+"'";
        }else {
        	doanhThuTCN[0]=danhThuT[maxIndexDT-1];
        	doanhThuTCN[1]=danhThuT[maxIndexDT];
        	doanhThuTCN[2]=danhThuT[maxIndexDT+1];
        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT)+"'";
        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT+1)+"'";
        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT+2)+"'";
        }
        
        
        //=============== dv thang ================
        List <HoaDon> hoaDons1;
        if(String.valueOf(thangHienTai).length()==1) {
        	
        	hoaDons1=hoaDonService.selectByYear(""+namHienTai+"-0"+thangHienTai+"-");
        	System.out.println("NGAY HIEN TAI= "+namHienTai+"-0"+thangHienTai+"-");
        } else{
        	hoaDons1=hoaDonService.selectByYear(""+namHienTai+"-"+thangHienTai+"-");
        	System.out.println("NGAY HIEN TAI= "+namHienTai+"-"+thangHienTai+"-");
        }
        if(!hoaDons1.isEmpty()) {
        	System.out.println("test-ten-lop="+hoaDons1.get(0).getThehd().getGoiTap().getLopDV().getTenLop());
        }
     
        for(i=0;;i++) {
        	try {
        	if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("aerobic")) {
        		soLuongDV[0]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("boxing")) {
        		soLuongDV[1]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("fitness")) {
        		soLuongDV[2]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("yoga")) {
        		soLuongDV[3]+=1;
        		
        	}
        	}
        	catch(Exception e) {
        		break;
        	}
        	
        	
        }
        int tongDV=0;
        for ( i = 0; i < 4 ; i++) {
            tongDV += soLuongDV[i];
        }
        
		//=========== top 10 khach hang =========
        List<HoaDon> top10KHs = hoaDonService.findBetweenNamSortGiaTien(namHienTai);
       
        
        List<String> khachHangs = new ArrayList<>();
        
        List<KhachHang> top10KHTiemNang =new ArrayList<>();
       
       
        
        for (int ig = 0; ig < top10KHs.size(); ig++) {
        	String maKHTop=top10KHs.get(ig).getThehd().getKhachHang().getMaKH();
        	float tien=top10KHs.get(ig).getThehd().getGoiTap().getGia();
            for (int j = ig + 1 ; j < top10KHs.size(); j++) {
            	 int flag=0;
            	 for (String khachHang1 : khachHangs) {
            		 if(khachHang1.split("-")[0].equals(top10KHs.get(j).getThehd().getKhachHang().getMaKH())) { 
            			 flag=1;
            			 break;
            		 }
            	 }
                 if (top10KHs.get(ig).getThehd().getKhachHang().getMaKH().equals(top10KHs.get(j).getThehd().getKhachHang().getMaKH()) && flag !=1  ) {
                	 maKHTop=top10KHs.get(ig).getThehd().getKhachHang().getMaKH();
                	 tien+=top10KHs.get(j).getThehd().getGoiTap().getGia();
                	
                	 
                 }
            }
            int flag=0;
            for (String khachHang1 : khachHangs) {
       		 if(khachHang1.split("-")[0].equals(maKHTop)) { 
       			flag=1;
       			
       			 break;
       		 }
       	 } if(flag==0) khachHangs.add(""+maKHTop+"-"+tien);
            
            
        }
        
        // sắp xếp giam dan
        
        String tg;
        for(int ig = 0; ig < khachHangs.size(); ig++){
            for(int j = ig + 1; j < khachHangs.size(); j++){
                if(Float.parseFloat(khachHangs.get(ig).split("-")[1]) < Float.parseFloat(khachHangs.get(j).split("-")[1])){
                    // Hoan vi 2 so a[i] va a[j]
                    tg = khachHangs.get(ig);
                    khachHangs.set(ig,khachHangs.get(j));
                    khachHangs.set(j, tg);        
                }
            }
        }
     // lấy 10 khách hàng đầu tiên
        try {
	        for( int id =0; id<10;id++) {
	        	List <KhachHang> KH = customerService.selectByMaKH(khachHangs.get(id).split("-")[0]);
	        	top10KHTiemNang.add(KH.get(0));
	        }
        }catch(Exception e) {}
        
        
        
        // loc ra các khach hàng đau tiên chưa tính trùng tên
//        for (The top10KH : top10KHs) {
//            if (!store.add(top10KH.getKhachHang().getTenKH()) == false) {
//            	//System.out.println(sLKH);
//            	khachHangs.add(top10KH);
//            }
//        }
////        // cộng dồn số tiền vào từng khách hàng giống nhau
//        for (The top10KH : top10KHs) {
//            if (store1.add(top10KH.getKhachHang().getTenKH()) == false) {
//            	//System.out.println(sLKH);
//            	for(The khachHang:khachHangs) {
//            		if(khachHang.getKhachHang().getTenKH().equals(top10KH.getKhachHang().getTenKH())) {
//            			khachHang.getGoiTap().setGia(khachHang.getGoiTap().getGia()+top10KH.getGoiTap().getGia());
//            			
//            		}
//            	}
//            }
//        }
        
        
		
		mav.addObject("thes_wtt", thes);
		mav.addObject("danhThuN", Arrays.toString(danhThuT));
		mav.addObject("doanhThuTCN", Arrays.toString(doanhThuTCN));
		mav.addObject("fieldBDNCN", Arrays.toString(fieldBDNCN));
		
		mav.addObject("bdDVT", Arrays.toString(soLuongDV));
		mav.addObject("bdKHN", Arrays.toString(soLuongKHT));
		mav.addObject("maxDT", maxDT);
		mav.addObject("tongDV", tongDV);
		mav.addObject("top10KHTiemNang", top10KHTiemNang);
		return mav;
		
	}
//	@RequestMapping("map")
//	public String map() {
//		return "map";
//	}
//	@RequestMapping("notifications")
//	public String information() {
//		return "notifications";
//	}
	@RequestMapping(value="user", params = {"id"}, method=RequestMethod.GET)
	public ModelAndView userprofile(@RequestParam("id") String maKH) {
		ModelAndView mav = new ModelAndView("user");
		List <KhachHang> khachHangs=customerService.selectByMaKH(maKH);
		List <The> thes=theService.selectByMaKH(maKH);
		mav.addObject("khachhangs", khachHangs);
		mav.addObject("avatar", khachHangs.get(0).getAnh());
		mav.addObject("tenKH", khachHangs.get(0).getTenKH());
		mav.addObject("thes", thes);

		return mav;
	}
	@RequestMapping(value="updateuser",  method=RequestMethod.POST)
	public ModelAndView updateuser(@RequestParam("makh") String maKH,@RequestParam("sdt") String sdt,@RequestParam("hovaten") String hoVaTen,@RequestParam("gioitinh") String gioiTinh,@RequestParam("email") String email,@RequestParam("diachi") String diaChi,@RequestParam("ngaysinh") String ngaySinh,@RequestParam("avatar") MultipartFile file) throws ParseException {

		
		 
		 ModelAndView mav =new ModelAndView("redirect:user?id="+maKH);
		mav.addObject("thongBao", "0");
		// ============== check gmail exist===============
		KhachHang khachHang=new KhachHang();
		List<KhachHang> emailKH = customerService.selectByEmail(email);
		List<KhachHang> khachHangMaKH = customerService.selectByMaKH(maKH);
		if((emailKH.isEmpty()||khachHangMaKH.get(0).getEmail().equals(email) )&& !email.isEmpty()) {
			// update info
			try {
			String sDate1=ngaySinh.replace("-", "/");  
		    Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);  
		    khachHang.setNgaySinh(date1);
			}
			catch(Exception e) {}
		    khachHang.setMaKH(maKH);
			khachHang.setTenKH(hoVaTen);
			khachHang.setEmail(email);
			khachHang.setDiaChi(diaChi);
			khachHang.setGioiTinh(gioiTinh);
			khachHang.setSdt(sdt);
			
			//upload avatar
			
			
			
			String thongBao="";
			String extensionFile=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			if (!file.isEmpty() && (extensionFile.equals("jpg") || extensionFile.equals("png")) ) {
						
						try {
							// xoa file truoc khi update file moi
							File avatar =new File(servletContext.getRealPath("resources/images/"+maKH+".jpg"));
							if(avatar.exists()) {
								avatar.delete();
							}else {
								avatar =new File(servletContext.getRealPath("resources/images/"+maKH+".png"));
								avatar.delete();
							}
			
							// Creating the directory to store file
							String fileName= maKH+"."+extensionFile;
							File dir = new File(servletContext.getRealPath("resources/images/"));
							System.out.println("FILEEEEEEEEEEEEEE=" +dir);
							File serverFile = new File(dir.getAbsolutePath()+ File.separator + fileName);
							file.transferTo(serverFile);
							thongBao= "success";
							khachHang.setAnh(maKH+"."+extensionFile);
							
							
							 } catch (Exception e) {
							  
								 thongBao="You failed to upload => " +e.getMessage();
							 }
							 
					} else {
						
						thongBao="Không phải file ảnh";
						
					}
			if(extensionFile.isEmpty()) khachHang.setAnh(khachHangMaKH.get(0).getAnh());
			else khachHang.setAnh(maKH+"."+extensionFile);
			
			customerService.save(khachHang);
			mav =new ModelAndView("redirect:user?id="+maKH);
			mav.addObject("thongBao", "1");
			return mav;
		}
		
		return mav;
		
		
	}
	
//	
	@RequestMapping("bangnhanvien")
	public ModelAndView nhanViens( HttpServletResponse response , HttpSession session) throws IOException {
		ModelAndView mav =new ModelAndView("nhanvien");
		
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		List<NhanVien> nhanViens = nhanVienService.listAll();
		mav.addObject("nhanVien", nhanViens);
		return mav;
	}
	@RequestMapping(value ="nhanvien", params = {"id"}, method=RequestMethod.GET)
	public ModelAndView nhanVien( HttpSession session,HttpServletResponse response,@RequestParam("id") String maNV) throws IOException {
		ModelAndView mav =new ModelAndView("chitietnhanvien");
		// check : Only admin can access this route
		
		
		if(!session.getAttribute("maQuyen").equals("0")) {
			response.sendRedirect("dangky");
		}
		List<NhanVien> nhanViens = nhanVienService.selectByMaNV(maNV);
		mav.addObject("nhanVien", nhanViens);
		return mav;
	}
	
	@RequestMapping(value = "updatenhanvien", method = RequestMethod.POST)
	public ModelAndView updateNhanVien(HttpServletResponse response,HttpSession session,@RequestParam("manv") String maNV,@RequestParam("hovaten") String hoVaTen, 
			@RequestParam("email") String email, @RequestParam("sdt")  String sdt, @RequestParam("diachi") String diaChi, 
			@RequestParam("gioitinh") String gioiTinh,@RequestParam("chucvu") String chucVu) throws IOException {
		
		ModelAndView mav = new ModelAndView("chitietnhanvien");
		
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		
		List<NhanVien> nhanViens = nhanVienService.selectByMaNV(maNV);
		mav.addObject("thongbao", "0");
		List<NhanVien> ktEmail = nhanVienService.selectByEmail(email);

		
		if(nhanViens.get(0).getHoaDons().size()==0 && nhanViens.size()>0 &&( nhanViens.get(0).getEmail().equals(email.trim()) || ktEmail.size()==0 )) {
			NhanVien nhanVien = new NhanVien();
			nhanVien.setMaNV(maNV);
			nhanVien.setDiaChi(diaChi);
			nhanVien.setEmail(email);
			nhanVien.setGioiTinh(gioiTinh);
			nhanVien.setSdt(sdt);
			nhanVien.setTaiKhoan(nhanViens.get(0).getTaiKhoan());
			nhanVien.setTenNV(hoVaTen);
			nhanVienService.save(nhanVien);
			mav.addObject("thongbao", "1");
			
		}
		//truy suất dữ liệu sau khi update
	    nhanViens = nhanVienService.selectByMaNV(maNV);
		mav.addObject("nhanVien", nhanViens);
		return mav;
		
		
	}
	
	// con filter chi cho 0 va 1, không update user đã có roi
	@RequestMapping(value = "updatetaikhoan", method = RequestMethod.POST)
	public ModelAndView updateTaiKhoan(HttpServletResponse response,HttpSession session,@RequestParam("manv") String maNV,@RequestParam("username") String userName,@RequestParam("password") String passWord,@RequestParam("maquyen") String maQuyen,@RequestParam("trangthai") String trangThai ) throws IOException {
		ModelAndView mav = new ModelAndView("chitietnhanvien");
		
		// check : Only admin can access this route
		
				//String maQuenUserHT=session.getAttribute("maQuyen").toString();
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		int tb=4;// goc
		List<TaiKhoan> taiKhoans = taiKhoanService.selectByUserName(userName);
		List<NhanVien> nhanViens = nhanVienService.selectByMaNV(maNV);
		mav.addObject("thongbao", "3");
		// so sanh bằng tài khoản sau khi truy xuất để tránh buglogic
		if(taiKhoans.size()>0 && !taiKhoans.get(0).getUserName().trim().equals("root") && taiKhoans.get(0).getUserName().equals(nhanViens.get(0).getTaiKhoan().getUserName().trim())) {
			TaiKhoan taiKhoan = new TaiKhoan();	
			List<PhanQuyen> phanQuyen; 
			//List<TaiKhoan> slChuTich = taiKhoanService.selectByMaQuyen(0);
			// số lượng chủ tịch phải lớn hơn 1 thì mới được cập nhận mã quyền là 1
			if(maQuyen.trim().equals("0")) phanQuyen = phanQuyenService.selectByMaQuyen(0);
			else if (nhanViens.get(0).getTaiKhoan().getUserName().trim().equals(session.getAttribute("username"))) {
				phanQuyen = phanQuyenService.selectByMaQuyen(0);
				tb+=1;
			}else phanQuyen = phanQuyenService.selectByMaQuyen(1);
			
			taiKhoan.setUserName(userName);
			if(passWord.trim().length() > 5 ) { 
				taiKhoan.setPassWord(passWord);
				mav.addObject("thongbaoPass", "1");
			}
			else {
				taiKhoan.setPassWord(nhanViens.get(0).getTaiKhoan().getPassWord());
				tb+=2;
			}
			
			
			
			if( trangThai.trim().equals("1") || (trangThai.trim().equals("0") && !nhanViens.get(0).getTaiKhoan().getUserName().trim().equals(session.getAttribute("username"))  ) ) taiKhoan.setTrangThai(Integer.parseInt(trangThai));
		    else {
		    	taiKhoan.setTrangThai(nhanViens.get(0).getTaiKhoan().getTrangThai());
		    	tb+=4;
		    }

			taiKhoan.setPhanQuyen(phanQuyen.get(0));
			taiKhoanService.save(taiKhoan);
			mav.addObject("thongbao", ""+tb);
		}
		
		  nhanViens = nhanVienService.selectByMaNV(maNV);
			mav.addObject("nhanVien", nhanViens);
		return mav;
		
	}
	@RequestMapping(value = "dangkynhanvien", method = RequestMethod.POST)
	public ModelAndView dangKyNhanVien(HttpServletResponse response, HttpSession session,@RequestParam("hovaten") String hoVaTen, 
			@RequestParam("email") String email, @RequestParam("sdt")  String sdt, @RequestParam("diachi") String diaChi, 
			@RequestParam("gioitinh") String gioiTinh,@RequestParam("chucvu") String chucVu,@RequestParam("username") String userName,@RequestParam("password") String password,@RequestParam("xnpassword") String xnpassword) throws IOException {
		ModelAndView mav =new ModelAndView("nhanvien");
		
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		// check
		
				
				
		if(password.equals(xnpassword) && password.trim().length()>5 && !chucVu.trim().isEmpty() && !hoVaTen.trim().isEmpty()&& !userName.trim().isEmpty() ){
			List<NhanVien> ktEmail = nhanVienService.selectByEmail(email);
			List<NhanVien> ktUserName = nhanVienService.selectByUserName(userName);
			if(ktEmail.size()==0 && ktUserName.size()==0 ) { // email và username không trung
				TaiKhoan taiKhoan = new TaiKhoan();
				List<PhanQuyen> phanQuyen;
				if(chucVu.equals("Chủ tịch")) phanQuyen = phanQuyenService.selectByMaQuyen(0);
				else phanQuyen = phanQuyenService.selectByMaQuyen(1);
				
			
				taiKhoan.setUserName(userName);
				taiKhoan.setPassWord(password);
				taiKhoan.setTrangThai(1);
				taiKhoan.setPhanQuyen(phanQuyen.get(0));
				taiKhoanService.save(taiKhoan);
				
				
				
				NhanVien nhanVien= new NhanVien();
				List<NhanVien> nhanViens = nhanVienService.listAll();
				//============== auto lay mã mới ============= 
				String maNV ="";
				int maxID =0;
						try {
							 maxID = Integer.parseInt(nhanViens.get(0).getMaNV().split("NV")[1]);
							 for ( int i = 0; i < nhanViens.size(); i++) {
						            if (Integer.parseInt(nhanViens.get(i).getMaNV().split("NV")[1]) > maxID) {  
						            	maxID = Integer.parseInt(nhanViens.get(i).getMaNV().split("NV")[1]); 
						            }
						        }
							 maNV = "NV"+(maxID+1);
						}catch(Exception e) {
							
							maNV = "NV1";
						}
				        
				nhanVien.setMaNV(maNV);
				nhanVien.setDiaChi(diaChi);
				nhanVien.setEmail(email);
				nhanVien.setGioiTinh(gioiTinh);
				nhanVien.setSdt(sdt);
				nhanVien.setTaiKhoan(taiKhoan);
				nhanVien.setTenNV(hoVaTen);
				nhanVienService.save(nhanVien);
				
				mav.addObject("thongbao", "0");
				
			}else mav.addObject("thongbao", "2");
			
			
			
			
		}else mav.addObject("thongbao", "1");
		
		
		
		List<NhanVien> nhanViens = nhanVienService.listAll();
		mav.addObject("nhanVien", nhanViens);
		return mav;
	}
	
	@RequestMapping("lopdv")
	public ModelAndView lopdv(HttpSession session, HttpServletResponse response) throws IOException {
		ModelAndView ldv = new ModelAndView("lopdv");
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		
		List<LopDV> lopDVServices = lopDVService.listAll();
		ldv.addObject("lopDVServices", lopDVServices);
		
		return ldv;
	}
	@RequestMapping(value="lopdv",method=RequestMethod.POST)
	public ModelAndView themlopdv(HttpServletRequest request,HttpSession session,HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView("lopdv");
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		
		Enumeration<String> parameterNames = request.getParameterNames();
		List<String> paramNames = new ArrayList<>();
		int[] flagParams = {0,0,0,0,0}; // format ten,goi1,goi2,goi3,goi4
		// xác định các giá trị gửi lên từ browser
        while (parameterNames.hasMoreElements()) {
        	try {
        		String parameterName =parameterNames.nextElement();
             if(parameterName.equals("tenlop") && !request.getParameter("tenlop").trim().isEmpty()) { 
            	 flagParams[0]=1;
            	 
              }
             if(parameterName.trim().equals("ngay")  && !request.getParameter("giangay").trim().isEmpty()) {
            	 flagParams[1]=2;
            	 
             }
             
             if(parameterName.trim().equals("tuan") && !request.getParameter("giatuan").trim().isEmpty() ) {
            	 flagParams[2]=2;
            	 
             }
             
             if(parameterName.trim().equals("thang") && !request.getParameter("giathang").trim().isEmpty() ) {
            	 flagParams[3]=2;
            	 
             }
             
             if(parameterName.trim().equals("nam") && !request.getParameter("gianam").trim().isEmpty()) {
            	 flagParams[4]=2;
            	 
             }
             
        	}catch(Exception e) {
        		flagParams[0]=0;
        		
        	}
 
        }
        // kiemtra giá tiền là số
       try {
    	   
    	   String[] arrs= {"giangay","giatuan","giathang","gianam"};
    	   for(String arr:arrs) {
    		   if(!request.getParameter(arr).trim().isEmpty()) {
    			   Float.parseFloat(request.getParameter(arr).trim());
    		   }
    		   
    	   }
    	   
       }catch(Exception e) { flagParams[0]=0;}
        
        List<LopDV> checkTenLop= lopDVService.selectByTenLop(request.getParameter("tenlop").trim());
        if(flagParams[0]==1 && checkTenLop.size()==0) {
        	
        	// lấy 2 ký tự của mã lớp
            String prefixMaLop=request.getParameter("tenlop").trim().substring(0,1)+request.getParameter("tenlop").trim().substring(request.getParameter("tenlop").trim().length()-1);
            
        	
			List<LopDV> lopDVServices = lopDVService.listAll();		
			//============== auto lay mã mới ============= 
			String maLop="";
			
			int maxID =0;
			
			
					try {
						 maxID = Integer.parseInt(lopDVServices.get(0).getMaLop().substring(2));
						 for ( LopDV maLops:lopDVServices) {
					            if (Integer.parseInt(maLops.getMaLop().substring(2)) > maxID) {  
					            	maxID = Integer.parseInt(maLops.getMaLop().substring(2)); 
					            }
					        }
						 maLop = prefixMaLop+(maxID+1);
					}catch(Exception e) {
						
						maLop = prefixMaLop+1;
					}
			maLop=maLop.toUpperCase();
					
				// check malop was exist	
			//List<LopDV> checkMaLop = lopDVService.selectByMaLop(maLop);
					
			// them lop dv		
		    LopDV lopDV = new LopDV();
		    lopDV.setMaLop(maLop);
		    lopDV.setTenLop(request.getParameter("tenlop").trim());
		    lopDVService.save(lopDV);
		    
		    // them goi tập nếu có
		    if(flagParams[1]==2) {
		    	GoiTap goiTapNgay = new GoiTap();
		    	// format GT+Thoihan + Tengoitap + số của mã lop
		    	goiTapNgay.setMaGoiTap("GTNG"+maLop.substring(2));
		    	goiTapNgay.setLopDV(lopDV);
		    	goiTapNgay.setTenGoiTap("ngày");
		    	goiTapNgay.setThoiHan(1);
		    	goiTapNgay.setTrangThai(1);
		    	goiTapNgay.setGia(Float.parseFloat(request.getParameter("giangay")));
		    	goiTapService.save(goiTapNgay);
		    }
		    if(flagParams[2]==2) {
		    	GoiTap goiTapTuan = new GoiTap();
		    	// format GT+Thoihan + Tengoitap + số của mã lop
		    	goiTapTuan.setMaGoiTap("GTT"+maLop.substring(2));
		    	goiTapTuan.setLopDV(lopDV);
		    	goiTapTuan.setTenGoiTap("tuần");
		    	goiTapTuan.setThoiHan(7);
		    	goiTapTuan.setTrangThai(1);
		    	goiTapTuan.setGia(Float.parseFloat(request.getParameter("giatuan")));
		    	goiTapService.save(goiTapTuan);
		    }
		    if(flagParams[3]==2) {
		    	GoiTap goiTapThang = new GoiTap();
		    	// format GT+Thoihan + Tengoitap + số của mã lop
		    	goiTapThang.setMaGoiTap("GTTH"+maLop.substring(2));
		    	goiTapThang.setLopDV(lopDV);
		    	goiTapThang.setTenGoiTap("tháng");
		    	goiTapThang.setThoiHan(30);
		    	goiTapThang.setTrangThai(1);
		    	goiTapThang.setGia(Float.parseFloat(request.getParameter("giathang")));
		    	goiTapService.save(goiTapThang);
		    }
		    if(flagParams[4]==2) {
		    	GoiTap goiTapNam = new GoiTap();
		    	// format GT+Thoihan + Tengoitap + số của mã lop
		    	goiTapNam.setMaGoiTap("GTN"+maLop.substring(2));
		    	goiTapNam.setLopDV(lopDV);
		    	goiTapNam.setTenGoiTap("năm");
		    	goiTapNam.setThoiHan(365);
		    	goiTapNam.setTrangThai(1);
		    	goiTapNam.setGia(Float.parseFloat(request.getParameter("gianam")));
		    	goiTapService.save(goiTapNam);
		    }
		    mav.addObject("thongbao", "1");
		}else mav.addObject("thongbao", "0");
        
        List<LopDV> lopDVServices = lopDVService.listAll();
		mav.addObject("lopDVServices", lopDVServices);
		return mav;
		
		
	}
	
	@RequestMapping(value="lopdv1", params = {"id"}, method=RequestMethod.GET)
	public ModelAndView lopdv1(@RequestParam("id") String maLop,HttpSession session, HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView("lopdv1");
		
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		List<LopDV> checkMaLop = lopDVService.selectByMaLop(maLop);
		if(checkMaLop.size()>0) {
			List<GoiTap> goiTapServices = goiTapService.selectByMaLop(maLop);
			List <LopDV> lopDVs=lopDVService.selectByMaLop(maLop);
			mav.addObject("lopDVs", lopDVs);
			mav.addObject("goiTapServices", goiTapServices);

			return mav;
			
		}
		mav = new ModelAndView("redirect:lopdv");
		return mav;
		

	}
	@RequestMapping(value="updatelopdv", method=RequestMethod.POST)
	public ModelAndView updateLopDV(HttpSession session,HttpServletResponse response,@RequestParam("malop") String maLop1,@RequestParam("tengoitap") String tenGoiTap, @RequestParam("gia") String gia) throws IOException {
		ModelAndView mav = new ModelAndView("lopdv1");
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		
		String maLop= maLop1.trim();
		mav.addObject("thongbao", 0);
		List<LopDV> checkLopDV = lopDVService.selectByMaLop(maLop.trim());
		
		kiemTraLoi:try {
			float giaGoiTap = Float.parseFloat(gia);
			// kiem tra lop dịch vụ có tồn tại không
			if(checkLopDV.size()>0) {
				GoiTap goiTap = new GoiTap();
				int thoiHan=0;
				// Auto lấy mã gói tập
				//  format GT+Thoihan + Tengoitap + số của mã lop
				String maGT = "GT";
				if(Integer.parseInt(tenGoiTap.trim().split(" ")[0]) >1) {
					if(tenGoiTap.trim().split(" ")[1].equals("ngày")  && Integer.parseInt(tenGoiTap.trim().split(" ")[0]) <7 ) {
						maGT+=tenGoiTap.trim().split(" ")[0]+"NG"+maLop.substring(2);
						thoiHan=Integer.parseInt(tenGoiTap.trim().split(" ")[0]);
					}
					else if(tenGoiTap.trim().split(" ")[1].equals("tuần")   && Integer.parseInt(tenGoiTap.trim().split(" ")[0]) <4) {
						maGT+=tenGoiTap.trim().split(" ")[0]+"T"+maLop.substring(2);
						thoiHan=Integer.parseInt(tenGoiTap.trim().split(" ")[0])*7 ;
					}
					else if(tenGoiTap.trim().split(" ")[1].equals("tháng") && Integer.parseInt(tenGoiTap.trim().split(" ")[0]) <12) {
						maGT+=tenGoiTap.trim().split(" ")[0]+"TH"+maLop.substring(2);
						thoiHan=Integer.parseInt(tenGoiTap.trim().split(" ")[0])*30 ;
					}
					else if(tenGoiTap.trim().split(" ")[1].equals("năm") && Integer.parseInt(tenGoiTap.trim().split(" ")[0]) <11) {
						maGT+=tenGoiTap.trim().split(" ")[0]+"N"+maLop.substring(2);
						thoiHan=Integer.parseInt(tenGoiTap.trim().split(" ")[0])*365 ;
					}
					else break kiemTraLoi;
				}else {
					if(tenGoiTap.trim().split(" ")[1].equals("ngày")) {
						maGT+="NG"+maLop.substring(2);
						thoiHan=1 ;
					}
					else if(tenGoiTap.trim().split(" ")[1].equals("tuần")) {
						maGT+="T"+maLop.substring(2);
						thoiHan=7 ;
					}
					else if(tenGoiTap.trim().split(" ")[1].equals("tháng")) {
						maGT+="TH"+maLop.substring(2);
						thoiHan=30 ;
					}
					else if(tenGoiTap.trim().split(" ")[1].equals("năm")) {
						maGT+="N"+maLop.substring(2);
						thoiHan=365 ;
					}
					else break kiemTraLoi;
					
				}
				
				// kiem tra có bị trùng goi tập không
				List<GoiTap> checkGoiTap = goiTapService.selectByMaGT(maGT);
				if(checkGoiTap.size()>0) break kiemTraLoi;
				
				
				// set các côt
				goiTap.setMaGoiTap(maGT);
				goiTap.setTenGoiTap(tenGoiTap.trim());
				goiTap.setThoiHan(thoiHan);
				goiTap.setTrangThai(1);// mặc định trạng thái sẽ băng 1
				goiTap.setGia(giaGoiTap);
				goiTap.setLopDV(checkLopDV.get(0));
				goiTapService.save(goiTap);
				mav.addObject("thongbao", 1);
			}
		}catch(Exception e) {
			
		}
		
		List<GoiTap> goiTapServices = goiTapService.selectByMaLop(maLop.trim());
		List <LopDV> lopDVs=lopDVService.selectByMaLop(maLop.trim());
		mav.addObject("lopDVs", lopDVs);
		mav.addObject("goiTapServices", goiTapServices);

		
		
		
		return mav;
	}
	
	@RequestMapping("logout") 
	public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		//remote session
		session.removeAttribute("username");
		// remote cookie
		//System.out.println(request.getCookies().toString());
		
		  for (Cookie ck: request.getCookies()){
			  if(ck.getName().equalsIgnoreCase("SESSIONID")){ 
				ck.setMaxAge(0);
			  	response.addCookie(ck); 
			  	} 
			} 
		  
		  		
		return "redirect:../home";
	}
	@RequestMapping("diemdanh")
	public String diemdanh() {
		return "diemdanh";
	}
	@RequestMapping(value="diemdanh",method = RequestMethod.POST)
	public ModelAndView diemdanh(@RequestParam("mathe") String maThe) throws ParseException {
		List<The> thes= theService.selectByMaThe(maThe);
		List<DiemDanh> diemDanhs = diemDanhService.selectByMaThe(maThe);
		List<DiemDanh> diemDanhAll = diemDanhService.listAll();
		// time hien tai
		Date date = new Date();
		String thongBao="0";
		if(thes.get(0).getTrangThai().equals("Hoạt Động")) {
			DiemDanh diemDanh = new DiemDanh();
			//============== auto lay mã mới ============= 
			String maDD="";
			
			int maxID =0;
			
			
					try {
						 maxID = Integer.parseInt(diemDanhAll.get(0).getMaDD().split("DD")[1]);
						 for ( DiemDanh diemDanhMaThe:diemDanhAll) {
					            if (Integer.parseInt(diemDanhMaThe.getMaDD().split("DD")[1]) > maxID) {  
					            	maxID = Integer.parseInt(diemDanhMaThe.getMaDD().split("DD")[1]); 
					            }
					        }
						 maDD = "DD"+(maxID+1);
					}catch(Exception e) {
						
						maDD = "DD1";
					}
					 
			
					
			       // System.out.println("MA KHACH HANG NE= "+maKHDV);
			
			if(diemDanhs.isEmpty()) {
				LocalDate ngayBD1 = LocalDate.now();
				LocalDate ngayKT1 = ngayBD1.plusDays(thes.get(0).getGoiTap().getThoiHan());
				System.out.print("ngayBD1==="+ngayBD1);
				System.out.print("ngayKT1==="+ngayKT1);
				Date ngayBD=new SimpleDateFormat("yyyy/MM/dd").parse((""+ngayBD1).replace("-", "/"));
				Date ngayKT= new SimpleDateFormat("yyyy/MM/dd").parse((""+ngayKT1).replace("-", "/"));
				System.out.print("ngayBD==="+ngayBD);
				System.out.print("ngayKT==="+ngayKT);
				theService.updateNgayByMaThe(ngayBD,ngayKT, maThe);
				
				
				diemDanh.setSoLan(1);
			}else diemDanh.setSoLan(diemDanhs.size()+1);
			diemDanh.setThe(thes.get(0));
			diemDanh.setThoiGian(date);
			diemDanh.setMaDD(maDD);
			diemDanhService.save(diemDanh);
			thongBao ="1";
		}
		ModelAndView mav =new ModelAndView("diemdanh");
		mav.addObject("thongbao", thongBao);
		return mav;
	}
	
	
	@RequestMapping("bangusers")
	public ModelAndView bangusers() {
		ModelAndView mav = new ModelAndView("bangusers");
		List<KhachHang> customerServices = customerService.listAll();
		//customerServices.forEach(p->p.getThe().forEach(p1->System.out.println(p1.getTrangThai())));
		mav.addObject("customerServices", customerServices);
		return mav;
	}
	@RequestMapping("thongke")
	public ModelAndView thongke(HttpSession session,HttpServletResponse response) throws ParseException, IOException {
		ModelAndView tk = new ModelAndView("thongke");
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					 response.sendRedirect("dangky");
				}
				
		LocalDate date = LocalDate.now();
		String []dates = (""+date).split("-");
		float tongTien=0;
		String namBD=dates[0]+"/"+dates[1]+"/01";
		String namKT=dates[0]+"/"+dates[1]+"/"+dates[2];
		Date dateBD=new SimpleDateFormat("yyyy/MM/dd").parse(namBD);
		Date dateKT=new SimpleDateFormat("yyyy/MM/dd").parse(namKT);
		List<The> theServices = theService.findBetweenNgayDK(dateBD,dateKT );
		List<The> theServicess = new ArrayList<The>();
		for(The theService1:theServices ) {
			if(theService1.getTrangThai().trim().equals("Hoạt Động")||theService1.getTrangThai().trim().equals("Hết Hạn") ) {
				tongTien +=theService1.getGoiTap().getGia();
				theServicess.add(theService1);
			}
			
		}
		System.out.println(""+dates[0]+"-"+dates[1]);
		
		
		List<LopDV> lopDVs = lopDVService.listAll();
		tk.addObject("lopDVs", lopDVs);
		tk.addObject("theServices", theServicess);
		tk.addObject("tongTien", tongTien);
		tk.addObject("namBD", namBD.replace('/', '-'));
		tk.addObject("namKT", namKT.replace('/', '-'));
		
		return tk;
	}
	@RequestMapping(value="thongkeDT",method=RequestMethod.POST)
	public ModelAndView thongkeDT(HttpServletResponse response, HttpSession session,@RequestParam("ngayBD") String ngayBD,@RequestParam("ngayKT") String ngayKT) throws ParseException, IOException {
		ModelAndView tk = new ModelAndView("thongke");
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					 response.sendRedirect("dangky");
				}
		
//		LocalDate date = LocalDate.now();
//		String []dates = (""+date).split("-");
		float tongTien=0;
//		String namBD=dates[0]+"/"+dates[1]+"/01";
//		String namKT=dates[0]+"/"+dates[1]+"/"+dates[2];
		Date dateBD=new SimpleDateFormat("yyyy/MM/dd").parse(ngayBD.replace("-", "/"));
		Date dateKT=new SimpleDateFormat("yyyy/MM/dd").parse(ngayKT.replace("-", "/"));
		List<The> theServices = theService.findBetweenNgayDK(dateBD,dateKT );
		List<The> theServicess = new ArrayList<The>();
		for(The theService1:theServices ) {
			if(theService1.getTrangThai().trim().equals("Hoạt Động")||theService1.getTrangThai().trim().equals("Hết Hạn") ) {
				tongTien +=theService1.getGoiTap().getGia();
				theServicess.add(theService1);
			}
			
		}
		//System.out.println(""+dates[0]+"-"+dates[1]);
		List<LopDV> lopDVs = lopDVService.listAll();
		tk.addObject("lopDVs", lopDVs);
		tk.addObject("theServices", theServicess);
		tk.addObject("tongTien", tongTien);
		tk.addObject("namBD", ngayBD);
		tk.addObject("namKT", ngayKT);
		
		return tk;
	}
	@RequestMapping(value="thongkeDV",method=RequestMethod.POST)
	public ModelAndView thongkeDV(HttpServletResponse response,HttpSession session,@RequestParam("ngayBD") String ngayBD,@RequestParam("ngayKT") String ngayKT,@RequestParam("tenLopDV") String tenLopDV) throws ParseException, IOException {
		ModelAndView tk = new ModelAndView("thongke");
		
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					 response.sendRedirect("dangky");
				}
		int slThe=0;
		Date dateBD=new SimpleDateFormat("yyyy/MM/dd").parse(ngayBD.replace("-", "/"));
		Date dateKT=new SimpleDateFormat("yyyy/MM/dd").parse(ngayKT.replace("-", "/"));
		List<The> theServices = theService.findBetweenNgayDKTenLop(dateBD,dateKT,tenLopDV);
		for(The theService1:theServices ) {						
				slThe +=1;
		}
		//System.out.println(""+dates[0]+"-"+dates[1]);
		List<LopDV> lopDVs = lopDVService.listAll();
		tk.addObject("lopDVs", lopDVs);
		tk.addObject("flag", "dv");
		tk.addObject("theServiceDV", theServices);
		tk.addObject("slThe", slThe);
		tk.addObject("tenLopDV", tenLopDV);
		tk.addObject("namBDDV", ngayBD);
		tk.addObject("namKTDV", ngayKT);
		
		return tk;
	}
	@RequestMapping(value="thongkeKH",method=RequestMethod.POST)
	public ModelAndView thongkeKH(HttpServletResponse response,HttpSession session,@RequestParam("ngayBD") String ngayBD,@RequestParam("ngayKT") String ngayKT,@RequestParam("tenKH") String tenKH) throws ParseException, IOException {
		ModelAndView mav=new ModelAndView("thongke");
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		Date dateBD=new SimpleDateFormat("yyyy/MM/dd").parse(ngayBD.replace("-", "/"));
		Date dateKT=new SimpleDateFormat("yyyy/MM/dd").parse(ngayKT.replace("-", "/"));
		List <The> theServices = theService.findBetweenNgayDKTenKH(dateBD, dateKT,tenKH);
		List<LopDV> lopDVs = lopDVService.listAll();
		mav.addObject("lopDVs", lopDVs);
		mav.addObject("theServiceKH", theServices);
		mav.addObject("flag", "kh");
		mav.addObject("slTheKH", ""+theServices.size());
		mav.addObject("tenKH", tenKH);
		mav.addObject("namBDDV", ngayBD);
		mav.addObject("namKTDV", ngayKT);
		return mav;
		
	}
	
	@RequestMapping(value="hoadon", params = {"id"}, method=RequestMethod.GET)
	public ModelAndView hoadon(@RequestParam("id") String maThe, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("hoadon");
		List<The> thes = theService.selectByMaThe(maThe);
		mav.addObject("mathe", thes);
		

		
		return mav;
	}
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/DD/YYYY")
	@RequestMapping(value="hoadon",method=RequestMethod.POST)
		public ModelAndView hoadon1(HttpSession session,ModelMap modelMap,@RequestParam("id") String maThe, HttpServletResponse response) throws MessagingException {
		List<TaiKhoan> taiKhoans = taiKhoanService.selectByUserName(session.getAttribute("username").toString());
		List<The> thes= theService.selectByMaThe(maThe);
		List<HoaDon> hoaDonSorts = hoaDonService.selectSortMaSoHD();
		String maHDMail="";
			HoaDon hoaDon= new HoaDon();
			The the=new The();
			//The the1=new The();
			NhanVien nhanVien = new NhanVien();
			GoiTap goiTap=new GoiTap();
						
			Date date = new Date();
					
			nhanVien = taiKhoans.get(0).getNhanVien();
						
//			goiTap= thes.get(0).getGoiTap();
//			the1=thes.get(0);
//			the1.setGoiTap(goiTap);
//			the1.setMaThe(maThe);
//			theService.save(the1);
			the=thes.get(0);
			hoaDon.setNhanVien(nhanVien);
			
			hoaDon.setThehd(the);
			
			hoaDon.setNgayHD(date);
			
			
			
			
			//============== auto lay mã mới ============= 
			
			int maxID =0;
					try {
						 maxID = Integer.parseInt(hoaDonSorts.get(0).getMaSoHD().split("HD")[1]);
						 for ( int i = 0; i < hoaDonSorts.size(); i++) {
					            if (Integer.parseInt(hoaDonSorts.get(i).getMaSoHD().split("HD")[1]) > maxID) {  
					            	maxID = Integer.parseInt(hoaDonSorts.get(i).getMaSoHD().split("HD")[1]); 
					            }
					        }
						 maHDMail = "HD"+(maxID+1);
					}catch(Exception e) {
						
						maHDMail = "HD1";
					}
			        
			
					
			       // System.out.println("MA KHACH HANG NE= "+maKHDV);
			
			
			
			
			
			hoaDon.setMaSoHD(maHDMail);
			hoaDonService.save(hoaDon);
			int updateTrTh= theService.updateByMaThe("Hoạt Động", maThe);
			
			// gưi email thong bao
			List<The> thesMail= theService.selectByMaThe(maThe);
			MimeMessage messages = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(messages, true, "UTF-8");
	       
	        helper.setTo(thesMail.get(0).getKhachHang().getEmail());
	        helper.setSubject("Thanh toán dịch vụ");
	        String html_HoaDon="\r\n"
	        		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><title>NDTGYM Confirm</title><style type=\"text/css\">\r\n"
	        		+ "    /* Take care of image borders and formatting, client hacks */\r\n"
	        		+ "    img { max-width: 600px; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic;}\r\n"
	        		+ "    a img { border: none; }\r\n"
	        		+ "    table { border-collapse: collapse !important;}\r\n"
	        		+ "    #outlook a { padding:0; }\r\n"
	        		+ "    .ReadMsgBody { width: 100%; }\r\n"
	        		+ "    .ExternalClass { width: 100%; }\r\n"
	        		+ "    .backgroundTable { margin: 0 auto; padding: 0; width: 100% !important; }\r\n"
	        		+ "    table td { border-collapse: collapse; }\r\n"
	        		+ "    .ExternalClass * { line-height: 115%; }\r\n"
	        		+ "    .container-for-gmail-android { min-width: 600px; }\r\n"
	        		+ "\r\n"
	        		+ "\r\n"
	        		+ "    /* General styling */\r\n"
	        		+ "    * {\r\n"
	        		+ "      font-family: Helvetica, Arial, sans-serif;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    body {\r\n"
	        		+ "      -webkit-font-smoothing: antialiased;\r\n"
	        		+ "      -webkit-text-size-adjust: none;\r\n"
	        		+ "      width: 100% !important;\r\n"
	        		+ "      margin: 0 !important;\r\n"
	        		+ "      height: 100%;\r\n"
	        		+ "      color: #676767;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    td {\r\n"
	        		+ "      font-family: Helvetica, Arial, sans-serif;\r\n"
	        		+ "      font-size: 14px;\r\n"
	        		+ "      color: #777777;\r\n"
	        		+ "      text-align: center;\r\n"
	        		+ "      line-height: 21px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    a {\r\n"
	        		+ "      color: #676767;\r\n"
	        		+ "      text-decoration: none !important;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .pull-left {\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .pull-right {\r\n"
	        		+ "      text-align: right;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .header-lg,\r\n"
	        		+ "    .header-md,\r\n"
	        		+ "    .header-sm {\r\n"
	        		+ "      font-size: 32px;\r\n"
	        		+ "      font-weight: 700;\r\n"
	        		+ "      line-height: normal;\r\n"
	        		+ "      padding: 35px 0 0;\r\n"
	        		+ "      color: #4d4d4d;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .header-md {\r\n"
	        		+ "      font-size: 24px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .header-sm {\r\n"
	        		+ "      padding: 5px 0;\r\n"
	        		+ "      font-size: 18px;\r\n"
	        		+ "      line-height: 1.3;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .content-padding {\r\n"
	        		+ "      padding: 20px 0 5px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .mobile-header-padding-right {\r\n"
	        		+ "      width: 290px;\r\n"
	        		+ "      text-align: right;\r\n"
	        		+ "      padding-left: 10px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .mobile-header-padding-left {\r\n"
	        		+ "      width: 290px;\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "      padding-left: 10px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .free-text {\r\n"
	        		+ "      width: 100% !important;\r\n"
	        		+ "      padding: 10px 60px 0px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .button {\r\n"
	        		+ "      padding: 30px 0;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "\r\n"
	        		+ "    .mini-block {\r\n"
	        		+ "      border: 1px solid #e5e5e5;\r\n"
	        		+ "      border-radius: 5px;\r\n"
	        		+ "      background-color: #ffffff;\r\n"
	        		+ "      padding: 12px 15px 15px;\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "      width: 253px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .mini-container-left {\r\n"
	        		+ "      width: 278px;\r\n"
	        		+ "      padding: 10px 0 10px 15px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .mini-container-right {\r\n"
	        		+ "      width: 278px;\r\n"
	        		+ "      padding: 10px 14px 10px 15px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .product {\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "      vertical-align: top;\r\n"
	        		+ "      width: 175px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .total-space {\r\n"
	        		+ "      padding-bottom: 8px;\r\n"
	        		+ "      display: inline-block;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .item-table {\r\n"
	        		+ "      padding: 50px 20px;\r\n"
	        		+ "      width: 560px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .item {\r\n"
	        		+ "      width: 300px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .mobile-hide-img {\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "      width: 125px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .mobile-hide-img img {\r\n"
	        		+ "      border: 1px solid #e6e6e6;\r\n"
	        		+ "      border-radius: 4px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .title-dark {\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "      border-bottom: 1px solid #cccccc;\r\n"
	        		+ "      color: #4d4d4d;\r\n"
	        		+ "      font-weight: 700;\r\n"
	        		+ "      padding-bottom: 5px;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .item-col {\r\n"
	        		+ "      padding-top: 20px;\r\n"
	        		+ "      text-align: left;\r\n"
	        		+ "      vertical-align: top;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "    .force-width-gmail {\r\n"
	        		+ "      min-width:600px;\r\n"
	        		+ "      height: 0px !important;\r\n"
	        		+ "      line-height: 1px !important;\r\n"
	        		+ "      font-size: 1px !important;\r\n"
	        		+ "    }\r\n"
	        		+ "\r\n"
	        		+ "  </style><style type=\"text/css\" media=\"screen\">\r\n"
	        		+ "    @import url(http://fonts.googleapis.com/css?family=Oxygen:400,700);\r\n"
	        		+ "  </style><style type=\"text/css\" media=\"screen\">\r\n"
	        		+ "    @media screen {\r\n"
	        		+ "      /* Thanks Outlook 2013! */\r\n"
	        		+ "      * {\r\n"
	        		+ "        font-family: 'Oxygen', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\r\n"
	        		+ "      }\r\n"
	        		+ "    }\r\n"
	        		+ "  </style><style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\r\n"
	        		+ "    /* Mobile styles */\r\n"
	        		+ "    @media only screen and (max-width: 480px) {\r\n"
	        		+ "\r\n"
	        		+ "      table[class*=\"container-for-gmail-android\"] {\r\n"
	        		+ "        min-width: 290px !important;\r\n"
	        		+ "        width: 100% !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      img[class=\"force-width-gmail\"] {\r\n"
	        		+ "        display: none !important;\r\n"
	        		+ "        width: 0 !important;\r\n"
	        		+ "        height: 0 !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      table[class=\"w320\"] {\r\n"
	        		+ "        width: 320px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "\r\n"
	        		+ "      td[class*=\"mobile-header-padding-left\"] {\r\n"
	        		+ "        width: 160px !important;\r\n"
	        		+ "        padding-left: 0 !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class*=\"mobile-header-padding-right\"] {\r\n"
	        		+ "        width: 160px !important;\r\n"
	        		+ "        padding-right: 0 !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class=\"header-lg\"] {\r\n"
	        		+ "        font-size: 24px !important;\r\n"
	        		+ "        padding-bottom: 5px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class=\"content-padding\"] {\r\n"
	        		+ "        padding: 5px 0 5px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "       td[class=\"button\"] {\r\n"
	        		+ "        padding: 5px 5px 30px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class*=\"free-text\"] {\r\n"
	        		+ "        padding: 10px 18px 30px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class~=\"mobile-hide-img\"] {\r\n"
	        		+ "        display: none !important;\r\n"
	        		+ "        height: 0 !important;\r\n"
	        		+ "        width: 0 !important;\r\n"
	        		+ "        line-height: 0 !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class~=\"item\"] {\r\n"
	        		+ "        width: 140px !important;\r\n"
	        		+ "        vertical-align: top !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class~=\"quantity\"] {\r\n"
	        		+ "        width: 50px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class~=\"price\"] {\r\n"
	        		+ "        width: 90px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class=\"item-table\"] {\r\n"
	        		+ "        padding: 30px 20px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "\r\n"
	        		+ "      td[class=\"mini-container-left\"],\r\n"
	        		+ "      td[class=\"mini-container-right\"] {\r\n"
	        		+ "        padding: 0 15px 15px !important;\r\n"
	        		+ "        display: block !important;\r\n"
	        		+ "        width: 290px !important;\r\n"
	        		+ "      }\r\n"
	        		+ "    }\r\n"
	        		+ "  </style></head><body bgcolor=\"#f7f7f7\"><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"container-for-gmail-android\" width=\"100%\"><tr><center><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#ffffff\" background=\"http://s3.amazonaws.com/swu-filepicker/4E687TRe69Ld95IDWyEg_bg_top_02.jpg\" style=\"background-color:transparent\"><tr><td width=\"100%\" height=\"80\" valign=\"top\" style=\"text-align: center; vertical-align:middle;\"><center><table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\"><tr><td class=\"pull-left mobile-header-padding-left\" style=\"vertical-align: middle;\"><a class=\"header-md\" href=\"\">Xin chào, "+thesMail.get(0).getKhachHang().getTenKH()+"</a></td></tr></table></center></td></tr></table></center></td></tr><tr><td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #f7f7f7;\" class=\"content-padding\"><center><table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"w320\"><tr><td class=\"header-lg\">\r\n"
	        		+ "              Đã thanh toán!\r\n"
	        		+ "            </td></tr><tr><td class=\"free-text\">\r\n"
	        		+ "              Cảm ơn quý khách hàng đã đăng ký dịch vụ của NDTGYM.<br> Chúc quý khách có 1 ngày tập luyện thật tốt và vui vẻ!\r\n"
	        		+ "            </td></tr><tr><td class=\"button\"><div><a href=\"http://\"\r\n"
	        		+ "              style=\"background-color:#28a745;border-radius:5px;color:#ffffff;display:inline-block;font-family:'Cabin', Helvetica, Arial, sans-serif;font-size:14px;font-weight:regular;line-height:45px;text-align:center;text-decoration:none;width:155px;-webkit-text-size-adjust:none;mso-hide:all;\">Đăng ký dịch vụ mới</a></div></td></tr><tr><td class=\"w320\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td class=\"mini-container-left\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td class=\"mini-block-padding\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:separate !important;\"><tr><td class=\"mini-block\"><span class=\"header-sm\">Thông tin khách hàng</span><br />\r\n"
	        		+ "                                "+thesMail.get(0).getKhachHang().getTenKH()+" <br />\r\n"
	        		+ "                                "+thesMail.get(0).getKhachHang().getSdt()+" <br />\r\n"
	        		+ "                                "+thesMail.get(0).getKhachHang().getEmail()+" \r\n"
	        		+ "                              </td></tr></table></td></tr></table></td><td class=\"mini-container-right\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td class=\"mini-block-padding\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:separate !important;\"><tr><td class=\"mini-block\"><span class=\"header-sm\">Thông tin dịch vụ</span><br />\r\n"
	        		+ "                                Ngày đăng ký: "+thesMail.get(0).getNgayDK()+" <br /><span class=\"header-sm\">Mã Hóa đơn</span><br />\r\n"
	        		+ "                                #"+maHDMail+"\r\n"
	        		+ "                              </td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></td></tr><tr><td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #ffffff;  border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5;\"><center><table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\"><tr><td class=\"item-table\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr><td class=\"title-dark\" width=\"300\">\r\n"
	        		+ "                       Tên lớp\r\n"
	        		+ "                    </td><td class=\"title-dark\" width=\"163\">\r\n"
	        		+ "                      Gói tập\r\n"
	        		+ "                    </td><td class=\"title-dark\" width=\"97\">\r\n"
	        		+ "                      Giá\r\n"
	        		+ "                    </td></tr><tr><td class=\"item-col item\"><span style=\"color: #4d4d4d; font-weight:bold;\">"+thesMail.get(0).getGoiTap().getLopDV().getTenLop()+"</span></td><td class=\"item-col quantity\">\r\n"
	        		+ "                     "+thesMail.get(0).getGoiTap().getTenGoiTap()+"\r\n"
	        		+ "                    </td><td class=\"item-col\">\r\n"
	        		+ "                      ₫"+thesMail.get(0).getGoiTap().getGia()+"\r\n"
	        		+ "                    </td></tr><tr><td class=\"item-col item mobile-row-padding\"></td><td class=\"item-col quantity\"></td><td class=\"item-col price\"></td></tr><tr><td class=\"item-col item\"></td><td class=\"item-col quantity\" style=\"text-align:right; padding-right: 10px; border-top: 1px solid #cccccc;\"><span class=\"total-space\">Tổng phụ</span><br /><span class=\"total-space\">Thuế</span><br /><span class=\"total-space\" style=\"font-weight: bold; color: #4d4d4d\">Thành tiền</span></td><td class=\"item-col price\" style=\"text-align: left; border-top: 1px solid #cccccc;\"><span class=\"total-space\">₫"+thesMail.get(0).getGoiTap().getGia()+"</span><br /><span class=\"total-space\">₫0.00</span><br /><span class=\"total-space\" style=\"font-weight:bold; color: #4d4d4d\">₫"+thesMail.get(0).getGoiTap().getGia()+"</span></td></tr></table></td></tr></table></center></td></tr><tr><td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #f7f7f7; height: 100px;\"><center><table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"w320\"><tr><td style=\"padding: 5px 0 10px\"><strong>97, Man Thiện</strong><br />\r\n"
	        		+ "              Thành phố Thủ Đức <br />\r\n"
	        		+ "              Hồ Chí Minh <br /><br /></td></tr></table></center></td></tr></table></div></body></html>";
	        helper.setText(html_HoaDon, true);

	        this.emailSender.send(messages);
			
			
			ModelAndView mav= new ModelAndView("hoadon");
			mav.addObject("updatetrth", updateTrTh);
			System.out.println(updateTrTh);
			List<The> thes1= theService.selectByMaThe(maThe);
			mav.addObject("mathe", thes1);
			
			return mav;
		}
		
	
	
	
	@RequestMapping("banghoadon")
	public ModelAndView danhsachhoadon() {
		List<HoaDon> hoaDons = hoaDonService.listAll();
		ModelAndView mav = new ModelAndView("banghoadon");
		mav.addObject("hoadons", hoaDons);
		return mav;

	}
	
	
	@RequestMapping("dangky")
	public String dangky() {
		return "dangky";
	}
	@RequestMapping(value = "dangkyprofile", method = RequestMethod.POST)
	public ModelAndView dangky(@RequestParam("hovaten") String hoVaTen, @RequestParam("ngaysinh") String ngaySinh, 
			@RequestParam("email") String email, @RequestParam("sdt")  String sdt, @RequestParam("diachi") String diaChi, 
			@RequestParam("gioitinh") String gioiTinh,@RequestParam("avatar") MultipartFile file) {
		// ============== check gmail exist===============
		
		List<KhachHang> emailKH = customerService.selectByEmail(email);
		if(emailKH.isEmpty() && !email.isEmpty() && !hoVaTen.trim().isEmpty()) {
		
			
		List<KhachHang> khachHangSort = customerService.selectSortMaKh(); 
		
		
		
		
		
		
		KhachHang khachHang = new KhachHang();
		//============== auto lay mã mới ============= 
				String maKHDV ="";
				int maxID =0;
						try {
							 maxID = Integer.parseInt(khachHangSort.get(0).getMaKH().split("KH")[1]);
							 for ( int i = 0; i < khachHangSort.size(); i++) {
						            if (Integer.parseInt(khachHangSort.get(i).getMaKH().split("KH")[1]) > maxID) {  
						            	maxID = Integer.parseInt(khachHangSort.get(i).getMaKH().split("KH")[1]); 
						            }
						        }
							 maKHDV = "KH"+(maxID+1);
						}catch(Exception e) {
							
							maKHDV = "KH1";
						}
				        
				
				        
				       // System.out.println("MA KHACH HANG NE= "+maKHDV);
		//=========== upload avartar ========
				        
		String thongBao="";
		String extensionFile=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			if (!file.isEmpty() && (extensionFile.equals("jpg") || extensionFile.equals("png")) ) {
						
						try {
							
			
							// Creating the directory to store file
							String fileName= maKHDV+"."+extensionFile;
							File dir = new File(servletContext.getRealPath("resources/images/"));
							System.out.println("FILEEEEEEEEEEEEEE=" +dir);
							File serverFile = new File(dir.getAbsolutePath()+ File.separator + fileName);
							file.transferTo(serverFile);
							thongBao= "success";
	
							 } catch (Exception e) {
							  
								 thongBao="You failed to upload => " +e.getMessage();
							 }
							 
					} else {
						
						thongBao="Không phải file ảnh";
						
					}
		
			System.out.println("FILEEEEEEEEEEEEEE=" +thongBao);
		
		khachHang.setMaKH(maKHDV);
		khachHang.setTenKH(hoVaTen);
		khachHang.setNgaySinh(null);
		khachHang.setDiaChi(diaChi);
		khachHang.setEmail(email);
		khachHang.setGioiTinh(gioiTinh);
		khachHang.setSdt(sdt);
		khachHang.setGhiChu(null);
		if(extensionFile.isEmpty()) {
			khachHang.setAnh(null);
		}else khachHang.setAnh(maKHDV+"."+extensionFile);
		
		
		customerService.save(khachHang);
		ModelAndView mav =new ModelAndView("redirect:dichvu?id="+maKHDV);
		mav.addObject("thongBao", "Đăng ký khách hàng thành công");
		return mav;
		}
		
		ModelAndView mav =new ModelAndView("dangky");
		mav.addObject("thongBaoLoi", "Email hoặc thông tin không chính xác!");
		mav.addObject("hoVaTen",hoVaTen);
		mav.addObject("ngaySinh",ngaySinh);
		mav.addObject("sdt",sdt);
		mav.addObject("gioiTinh",gioiTinh);
		mav.addObject("diaChi",diaChi);
		
		
		
		
		return mav;
	}
	@RequestMapping(value="dichvu" , params = {"id"}, method=RequestMethod.GET)
	public ModelAndView dichvu(@RequestParam("id") String maKH) {
		List<The> TheSort = theService.selectSortMaThe();
		List<GoiTap> goiTaps=goiTapService.listAll();
		List<LopDV> lopDVs=lopDVService.listAll();

		String maTDV = "";
		LocalDate localDate = LocalDate.now();
		
		
		
				//============== auto lay mã mới ============= 
		
		int maxID =0;
				try {
					 maxID = Integer.parseInt(TheSort.get(0).getMaThe().split("T")[1]);
					 for ( int i = 0; i < TheSort.size(); i++) {
				            if (Integer.parseInt(TheSort.get(i).getMaThe().split("T")[1]) > maxID) {  
				            	maxID = Integer.parseInt(TheSort.get(i).getMaThe().split("T")[1]); 
				            }
				        }
					 maTDV = "T"+(maxID+1);
				}catch(Exception e) {
					
					maTDV = "T1";
				}
		        
		
		       
		       // System.out.println("MA KHACH HANG NE= "+maKHDV);
		
		
		
//		Set<String> store = new HashSet<>();
//		for (GoiTap goiTap : goiTaps) {
//            if (!store.add(goiTap.getTenGoiTap()) == false) {
//            	goiTapsDV.add(""+goiTap.getTenGoiTap());
//            }
//        }
		
		
		ModelAndView mav = new ModelAndView("dichvu");
		mav.addObject("maTDV", maTDV);
		mav.addObject("maKH", maKH);
	//	mav.addObject("goiTaps", goiTapsDV);
		mav.addObject("lopDVs", lopDVs);
		mav.addObject("localDate", localDate);
		return mav;
		
	}
	@RequestMapping(value = "dangkydichvu", method = RequestMethod.POST)
	public ModelAndView dichvu(@RequestParam("maKH") String maKH, @RequestParam("lopDV") String tenLopDV, 
			@RequestParam("goiTap") String goiTap) {
		ModelAndView mav;
		
		// check goiTap đầu vào có trạng thái là 1
			int flag=0;
			List<GoiTap> checkGoiTap= goiTapService.selectByTenLopTrangThai(tenLopDV);
			for(GoiTap tenGoiTap:checkGoiTap) {
				if(tenGoiTap.getTenGoiTap().equals(goiTap.trim())) flag=1;
			}
			List<KhachHang> khachHangs =customerService.selectByMaKH(maKH.trim());
			
			if(flag==1 && khachHangs.size()==1 ) {
				List<The> TheSort = theService.selectSortMaThe();
				//====auto lay ma the===
				//============== auto lay mã mới ============= 
						String maTDV ="";
						int maxID =0;
								try {
									 maxID = Integer.parseInt(TheSort.get(0).getMaThe().split("T")[1]);
									 for ( int i = 0; i < TheSort.size(); i++) {
								            if (Integer.parseInt(TheSort.get(i).getMaThe().split("T")[1]) > maxID) {  
								            	maxID = Integer.parseInt(TheSort.get(i).getMaThe().split("T")[1]); 
								            }
								        }
									 maTDV = "T"+(maxID+1);
								}catch(Exception e) {
									
									maTDV = "T1";
								}
						        
						
						        
						       // System.out.println("MA KHACH HANG NE= "+maKHDV);
				//===lay khach hang theo ma
				
				
				//===== lay goi tap theo ma lop va ten goi
				goiTap=goiTap.trim();
				List<LopDV> lopDVs=lopDVService.selectByTenLop(tenLopDV);
				
				List<GoiTap> goiTaps=goiTapService.selectByMaLopTenGoiTap(lopDVs.get(0).getMaLop(), goiTap);
				//=== tao thuc the
				Date date=new Date();
				
				The the= new The();
				the.setMaThe(maTDV);
				the.setKhachHang(khachHangs.get(0));
				the.setGoiTap(goiTaps.get(0));
				the.setNgayDK(date);
				the.setTrangThai("Chưa Thanh Toán");
				theService.save(the);
				 mav=new ModelAndView("redirect:hoadon?id="+maTDV);
			}else  mav = new ModelAndView("redirect:logout");
		return mav;
	}
	@RequestMapping("bieudonam")
	public ModelAndView bieudonam(HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mav=new ModelAndView("bieudonam");
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					 response.sendRedirect("dangky");
				}
				
				
		// lay date hien tai
		LocalDate localDate = LocalDate.now();
	    String date=""+localDate;
	    String[] dates=	date.split("-");
	    String namHienTai = dates[0];
		String thangHienTai=dates[1];
		
		float[] danhThuT= {0,0,0,0,0,0,0,0,0,0,0,0};
		int[] soLuongKHT= {0,0,0,0,0,0,0,0,0,0,0,0};
		
		
		int i;
		List <HoaDon> hoaDons;
		for(i=1;i<=11;i++) {
			//System.out.println("sodem= "+i);
				 if(String.valueOf(i).length()==1) {hoaDons=hoaDonService.selectByYear(""+namHienTai+"-0"+i+"-");}
				 else{hoaDons=hoaDonService.selectByYear(""+namHienTai+"-"+i+"-");}
				 if(hoaDons.isEmpty()) continue;
				 
			//hoaDons.forEach(hoaDon-> danhThuT[i]+= hoaDon.getThehd().getGoiTap().getGia());
				 int a= 0 ;
				 List<String> sLKHs=new ArrayList<>(); // tao list ao
			     List<String> sLKHs1 = new ArrayList<>();// list remove duplicate
			while(true) {
				
				//System.out.println("sodem trong while= "+i);
				try {
				  //System.out.println("hades["+i+"]="+"["+a+"]="+hoaDons.get(a).getThehd().getGoiTap().getGia());
				danhThuT[i-1]+=hoaDons.get(a).getThehd().getGoiTap().getGia();
				sLKHs.add(hoaDons.get(a).getThehd().getKhachHang().getMaKH());
				
				a++;
				}catch (Exception e) {break;}
			}
			// tien hanh duplicate
			Set<String> store = new HashSet<>();
			
	        for (String sLKH : sLKHs) {
	            if (!store.add(sLKH) == false) {
	            	sLKHs1.add(""+sLKH);
	            }
	        }
	        // dem so luong
	        soLuongKHT[i-1]=sLKHs1.size();
			
		}
		
		mav.addObject("danhThuN", Arrays.toString(danhThuT));
		mav.addObject("bdKHN", Arrays.toString(soLuongKHT));
		return mav;
	}
	
	@RequestMapping(value="bieudonam",method=RequestMethod.POST)
	public ModelAndView bieudonam1(HttpServletResponse response,HttpSession session,@RequestParam("nam") String namHienTai) throws IOException {
		ModelAndView mav=new ModelAndView("bieudonam");
		// check : Only admin can access this route
		if(!session.getAttribute("maQuyen").equals("0")) {
			 response.sendRedirect("dangky");
		}
		
		
		
		float[] danhThuT= {0,0,0,0,0,0,0,0,0,0,0,0};
		int[] soLuongKHT= {0,0,0,0,0,0,0,0,0,0,0,0};
		
		
		int i;
		List <HoaDon> hoaDons;
		for(i=1;i<=11;i++) {
			//System.out.println("sodem= "+i);
				 if(String.valueOf(i).length()==1) {hoaDons=hoaDonService.selectByYear(""+namHienTai+"-0"+i+"-");}
				 else{hoaDons=hoaDonService.selectByYear(""+namHienTai+"-"+i+"-");}
				 if(hoaDons.isEmpty()) continue;
				 
			//hoaDons.forEach(hoaDon-> danhThuT[i]+= hoaDon.getThehd().getGoiTap().getGia());
				 int a= 0 ;
				 List<String> sLKHs=new ArrayList<>(); // tao list ao
			     List<String> sLKHs1 = new ArrayList<>();// list remove duplicate
			while(true) {
				
				//System.out.println("sodem trong while= "+i);
				try {
				  //System.out.println("hades["+i+"]="+"["+a+"]="+hoaDons.get(a).getThehd().getGoiTap().getGia());
				danhThuT[i-1]+=hoaDons.get(a).getThehd().getGoiTap().getGia();
				sLKHs.add(hoaDons.get(a).getThehd().getKhachHang().getMaKH());
				
				a++;
				}catch (Exception e) {break;}
			}
			// tien hanh duplicate
			Set<String> store = new HashSet<>();
			
	        for (String sLKH : sLKHs) {
	            if (!store.add(sLKH) == false) {
	            	sLKHs1.add(""+sLKH);
	            }
	        }
	        // dem so luong
	        soLuongKHT[i-1]=sLKHs1.size();
			
		}
		
		mav.addObject("danhThuN", Arrays.toString(danhThuT));
		mav.addObject("bdKHN", Arrays.toString(soLuongKHT));
		mav.addObject("namHienTai", namHienTai);
		return mav;
		
		
	}
	
	
	
	
	@RequestMapping("bieudodtcn")
	public ModelAndView bieudodtcn(HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mav=new ModelAndView("bieudodtcn");
		
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
				
		LocalDate localDate = LocalDate.now();
	      String date=""+localDate;
	      String[] dates=	date.split("-");
	      
		
		String namHienTai = dates[0];
		
		String[] fieldBDNCN= {"","",""};
		float[] danhThuT= {0,0,0,0,0,0,0,0,0,0,0,0};
		float[] doanhThuTCN= {0,0,0};
		
		
		int i;
		List <HoaDon> hoaDons;
		for(i=1;i<=11;i++) {
			//System.out.println("sodem= "+i);
				 if(String.valueOf(i).length()==1) {hoaDons=hoaDonService.selectByYear(""+namHienTai+"-0"+i+"-");}
				 else{hoaDons=hoaDonService.selectByYear(""+namHienTai+"-"+i+"-");}
				 if(hoaDons.isEmpty()) continue;
				 
			//hoaDons.forEach(hoaDon-> danhThuT[i]+= hoaDon.getThehd().getGoiTap().getGia());
				 int a= 0 ;
				 
			while(true) {
				
				//System.out.println("sodem trong while= "+i);
				try {
				  //System.out.println("hades["+i+"]="+"["+a+"]="+hoaDons.get(a).getThehd().getGoiTap().getGia());
				danhThuT[i-1]+=hoaDons.get(a).getThehd().getGoiTap().getGia();
				
				
				a++;
				}catch (Exception e) {break;}
			}
			
			
		}
		//============== danh thu cao nhat ============= 
				float maxDT = danhThuT[0];
				int maxIndexDT=0;
		        for ( i = 1; i < danhThuT.length; i++) {
		            if (danhThuT[i] > maxDT) {  
		            	maxDT = danhThuT[i];maxIndexDT=i; 
		            }
		        }
		        if(maxIndexDT==0) {
		        	doanhThuTCN[0]=danhThuT[maxIndexDT];
		        	doanhThuTCN[1]=danhThuT[maxIndexDT+1];
		        	doanhThuTCN[2]=danhThuT[maxIndexDT+2];
		        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT+1)+"'";
		        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT+2)+"'";
		        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT+3)+"'";
		        	
		        }else if(maxIndexDT==11) {
		        	doanhThuTCN[0]=danhThuT[maxIndexDT-2];
		        	doanhThuTCN[1]=danhThuT[maxIndexDT-1];
		        	doanhThuTCN[2]=danhThuT[maxIndexDT];
		        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT+1)+"'";
		        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT)+"'";
		        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT-1)+"'";
		        }else {
		        	doanhThuTCN[0]=danhThuT[maxIndexDT-1];
		        	doanhThuTCN[1]=danhThuT[maxIndexDT];
		        	doanhThuTCN[2]=danhThuT[maxIndexDT+1];
		        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT)+"'";
		        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT+1)+"'";
		        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT+2)+"'";
		        }
		
		mav.addObject("doanhThuTCN", Arrays.toString(doanhThuTCN));
		mav.addObject("fieldBDNCN", Arrays.toString(fieldBDNCN));
		
		mav.addObject("maxDT", maxDT);
		
		return mav;
	}
	@RequestMapping(value="bieudodtcn",method=RequestMethod.POST)
	public ModelAndView bieudodtcn1(HttpServletResponse response,HttpSession session,@RequestParam("nam")String namHienTai ) throws IOException {
		ModelAndView mav=new ModelAndView("bieudodtcn");
		// check : Only admin can access this route
		if(!session.getAttribute("maQuyen").equals("0")) {
			 response.sendRedirect("dangky");
		}
		
		String[] fieldBDNCN= {"","",""};
		float[] danhThuT= {0,0,0,0,0,0,0,0,0,0,0,0};
		float[] doanhThuTCN= {0,0,0};
		
		
		int i;
		List <HoaDon> hoaDons;
		for(i=1;i<=11;i++) {
			//System.out.println("sodem= "+i);
				 if(String.valueOf(i).length()==1) {hoaDons=hoaDonService.selectByYear(""+namHienTai+"-0"+i+"-");}
				 else{hoaDons=hoaDonService.selectByYear(""+namHienTai+"-"+i+"-");}
				 if(hoaDons.isEmpty()) continue;
				 
			//hoaDons.forEach(hoaDon-> danhThuT[i]+= hoaDon.getThehd().getGoiTap().getGia());
				 int a= 0 ;
				 
			while(true) {
				
				//System.out.println("sodem trong while= "+i);
				try {
				  //System.out.println("hades["+i+"]="+"["+a+"]="+hoaDons.get(a).getThehd().getGoiTap().getGia());
				danhThuT[i-1]+=hoaDons.get(a).getThehd().getGoiTap().getGia();
				
				
				a++;
				}catch (Exception e) {break;}
			}
			
			
		}
		//============== danh thu cao nhat ============= 
				float maxDT = danhThuT[0];
				int maxIndexDT=0;
		        for ( i = 1; i < danhThuT.length; i++) {
		            if (danhThuT[i] > maxDT) {  
		            	maxDT = danhThuT[i];maxIndexDT=i; 
		            }
		        }
		        if(maxIndexDT==0) {
		        	doanhThuTCN[0]=danhThuT[maxIndexDT];
		        	doanhThuTCN[1]=danhThuT[maxIndexDT+1];
		        	doanhThuTCN[2]=danhThuT[maxIndexDT+2];
		        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT+1)+"'";
		        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT+2)+"'";
		        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT+3)+"'";
		        	
		        }else if(maxIndexDT==11) {
		        	doanhThuTCN[0]=danhThuT[maxIndexDT-2];
		        	doanhThuTCN[1]=danhThuT[maxIndexDT-1];
		        	doanhThuTCN[2]=danhThuT[maxIndexDT];
		        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT+1)+"'";
		        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT)+"'";
		        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT-1)+"'";
		        }else {
		        	doanhThuTCN[0]=danhThuT[maxIndexDT-1];
		        	doanhThuTCN[1]=danhThuT[maxIndexDT];
		        	doanhThuTCN[2]=danhThuT[maxIndexDT+1];
		        	fieldBDNCN[0]="'Tháng  "+(maxIndexDT)+"'";
		        	fieldBDNCN[1]="'Tháng  "+(maxIndexDT+1)+"'";
		        	fieldBDNCN[2]="'Tháng  "+(maxIndexDT+2)+"'";
		        }
		
		mav.addObject("doanhThuTCN", Arrays.toString(doanhThuTCN));
		mav.addObject("fieldBDNCN", Arrays.toString(fieldBDNCN));
		mav.addObject("namHienTai", namHienTai);
		mav.addObject("maxDT", maxDT);
		return mav;
	}
	
	
	
	
	@RequestMapping("bieudodv")
	public ModelAndView bieudodv(HttpServletResponse response,HttpSession session) throws IOException {
		ModelAndView mav =new ModelAndView("bieudodv");
		// check : Only admin can access this route
				if(!session.getAttribute("maQuyen").equals("0")) {
					 response.sendRedirect("dangky");
				}
				
		LocalDate localDate = LocalDate.now();
	    String date=""+localDate;
	    String[] dates=	date.split("-");
		String namHienTai = dates[0];
		String thangHienTai=dates[1];
		int[] soLuongDV= {0,0,0,0};
		int i;
		//=============== dv thang ================
        List <HoaDon> hoaDons1;
        if(String.valueOf(namHienTai).length()==1) {
        	
        	hoaDons1=hoaDonService.selectByYear(""+namHienTai+"-0"+thangHienTai+"-");
        	//System.out.println("NGAY HIEN TAI= "+namHienTai+"-0"+thangHienTai+"-");
        } else{
        	hoaDons1=hoaDonService.selectByYear(""+namHienTai+"-"+thangHienTai+"-");
        	//System.out.println("NGAY HIEN TAI= "+namHienTai+"-"+thangHienTai+"-");
        }
        //if(!hoaDons1.isEmpty()) {
        	//System.out.println("test-ten-lop="+hoaDons1.get(0).getThehd().getGoiTap().getLopDV().getTenLop());
        //}
     
        for(i=0;;i++) {
        	try {
        	if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("aerobic")) {
        		soLuongDV[0]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("boxing")) {
        		soLuongDV[1]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("fitness")) {
        		soLuongDV[2]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("yoga")) {
        		soLuongDV[3]+=1;
        		
        	}
        	}
        	catch(Exception e) {
        		break;
        	}
        	
        	
        }
        int tongDV=0;
        for ( i = 0; i < 4 ; i++) {
            tongDV += soLuongDV[i];
        }
		
		mav.addObject("bdDVT", Arrays.toString(soLuongDV));
		mav.addObject("tongDV", tongDV);
		mav.addObject("date", date);
		return mav;
	}
	@RequestMapping(value="bieudodv",method=RequestMethod.POST)
	public ModelAndView bieuDoDV1(HttpServletResponse response,HttpSession session,@RequestParam("thang")String thang,@RequestParam("nam")String nam) throws IOException {
		ModelAndView mav =new ModelAndView("bieudodv");
		// check : Only admin can access this route
		if(!session.getAttribute("maQuyen").equals("0")) {
			response.sendRedirect("dangky");
		}
		
		String namHienTai = nam;
		String thangHienTai=thang;
		int[] soLuongDV= {0,0,0,0};
		int i;
		//=============== dv thang ================
        List <HoaDon> hoaDons1;
        if(String.valueOf(namHienTai).length()==1) {
        	
        	hoaDons1=hoaDonService.selectByYear(""+namHienTai+"-0"+thangHienTai+"-");
        	System.out.println("NGAY HIEN TAI= "+namHienTai+"-0"+thangHienTai+"-");
        } else{
        	hoaDons1=hoaDonService.selectByYear(""+namHienTai+"-"+thangHienTai+"-");
        	System.out.println("NGAY HIEN TAI= "+namHienTai+"-"+thangHienTai+"-");
        }
        if(!hoaDons1.isEmpty()) {
        	System.out.println("test-ten-lop="+hoaDons1.get(0).getThehd().getGoiTap().getLopDV().getTenLop());
        }
     
        for(i=0;;i++) {
        	try {
        	if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("aerobic")) {
        		soLuongDV[0]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("boxing")) {
        		soLuongDV[1]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("fitness")) {
        		soLuongDV[2]+=1;
        		
        	}else if(hoaDons1.get(i).getThehd().getGoiTap().getLopDV().getTenLop().equals("yoga")) {
        		soLuongDV[3]+=1;
        		
        	}
        	}
        	catch(Exception e) {
        		break;
        	}
        	
        	
        }
        int tongDV=0;
        for ( i = 0; i < 4 ; i++) {
            tongDV += soLuongDV[i];
        }
		
		
		mav.addObject("bdDVT", Arrays.toString(soLuongDV));
		
		mav.addObject("tongDV", tongDV);
		mav.addObject("date",nam+"-"+thang);
		return mav;
		
	}
	
	@RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() throws MessagingException{
		

        
        
        MimeMessage messages = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(messages, true, "UTF-8");
       
        helper.setTo("thanhnamvo2000@gmail.com");
        helper.setSubject("A file for you");
        String html_HoaDon="\r\n"
        		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><title>NDTGYM Confirm</title><style type=\"text/css\">\r\n"
        		+ "    /* Take care of image borders and formatting, client hacks */\r\n"
        		+ "    img { max-width: 600px; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic;}\r\n"
        		+ "    a img { border: none; }\r\n"
        		+ "    table { border-collapse: collapse !important;}\r\n"
        		+ "    #outlook a { padding:0; }\r\n"
        		+ "    .ReadMsgBody { width: 100%; }\r\n"
        		+ "    .ExternalClass { width: 100%; }\r\n"
        		+ "    .backgroundTable { margin: 0 auto; padding: 0; width: 100% !important; }\r\n"
        		+ "    table td { border-collapse: collapse; }\r\n"
        		+ "    .ExternalClass * { line-height: 115%; }\r\n"
        		+ "    .container-for-gmail-android { min-width: 600px; }\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "    /* General styling */\r\n"
        		+ "    * {\r\n"
        		+ "      font-family: Helvetica, Arial, sans-serif;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    body {\r\n"
        		+ "      -webkit-font-smoothing: antialiased;\r\n"
        		+ "      -webkit-text-size-adjust: none;\r\n"
        		+ "      width: 100% !important;\r\n"
        		+ "      margin: 0 !important;\r\n"
        		+ "      height: 100%;\r\n"
        		+ "      color: #676767;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    td {\r\n"
        		+ "      font-family: Helvetica, Arial, sans-serif;\r\n"
        		+ "      font-size: 14px;\r\n"
        		+ "      color: #777777;\r\n"
        		+ "      text-align: center;\r\n"
        		+ "      line-height: 21px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    a {\r\n"
        		+ "      color: #676767;\r\n"
        		+ "      text-decoration: none !important;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .pull-left {\r\n"
        		+ "      text-align: left;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .pull-right {\r\n"
        		+ "      text-align: right;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .header-lg,\r\n"
        		+ "    .header-md,\r\n"
        		+ "    .header-sm {\r\n"
        		+ "      font-size: 32px;\r\n"
        		+ "      font-weight: 700;\r\n"
        		+ "      line-height: normal;\r\n"
        		+ "      padding: 35px 0 0;\r\n"
        		+ "      color: #4d4d4d;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .header-md {\r\n"
        		+ "      font-size: 24px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .header-sm {\r\n"
        		+ "      padding: 5px 0;\r\n"
        		+ "      font-size: 18px;\r\n"
        		+ "      line-height: 1.3;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .content-padding {\r\n"
        		+ "      padding: 20px 0 5px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .mobile-header-padding-right {\r\n"
        		+ "      width: 290px;\r\n"
        		+ "      text-align: right;\r\n"
        		+ "      padding-left: 10px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .mobile-header-padding-left {\r\n"
        		+ "      width: 290px;\r\n"
        		+ "      text-align: left;\r\n"
        		+ "      padding-left: 10px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .free-text {\r\n"
        		+ "      width: 100% !important;\r\n"
        		+ "      padding: 10px 60px 0px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .button {\r\n"
        		+ "      padding: 30px 0;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "    .mini-block {\r\n"
        		+ "      border: 1px solid #e5e5e5;\r\n"
        		+ "      border-radius: 5px;\r\n"
        		+ "      background-color: #ffffff;\r\n"
        		+ "      padding: 12px 15px 15px;\r\n"
        		+ "      text-align: left;\r\n"
        		+ "      width: 253px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .mini-container-left {\r\n"
        		+ "      width: 278px;\r\n"
        		+ "      padding: 10px 0 10px 15px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .mini-container-right {\r\n"
        		+ "      width: 278px;\r\n"
        		+ "      padding: 10px 14px 10px 15px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .product {\r\n"
        		+ "      text-align: left;\r\n"
        		+ "      vertical-align: top;\r\n"
        		+ "      width: 175px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .total-space {\r\n"
        		+ "      padding-bottom: 8px;\r\n"
        		+ "      display: inline-block;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .item-table {\r\n"
        		+ "      padding: 50px 20px;\r\n"
        		+ "      width: 560px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .item {\r\n"
        		+ "      width: 300px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .mobile-hide-img {\r\n"
        		+ "      text-align: left;\r\n"
        		+ "      width: 125px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .mobile-hide-img img {\r\n"
        		+ "      border: 1px solid #e6e6e6;\r\n"
        		+ "      border-radius: 4px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .title-dark {\r\n"
        		+ "      text-align: left;\r\n"
        		+ "      border-bottom: 1px solid #cccccc;\r\n"
        		+ "      color: #4d4d4d;\r\n"
        		+ "      font-weight: 700;\r\n"
        		+ "      padding-bottom: 5px;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .item-col {\r\n"
        		+ "      padding-top: 20px;\r\n"
        		+ "      text-align: left;\r\n"
        		+ "      vertical-align: top;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "    .force-width-gmail {\r\n"
        		+ "      min-width:600px;\r\n"
        		+ "      height: 0px !important;\r\n"
        		+ "      line-height: 1px !important;\r\n"
        		+ "      font-size: 1px !important;\r\n"
        		+ "    }\r\n"
        		+ "\r\n"
        		+ "  </style><style type=\"text/css\" media=\"screen\">\r\n"
        		+ "    @import url(http://fonts.googleapis.com/css?family=Oxygen:400,700);\r\n"
        		+ "  </style><style type=\"text/css\" media=\"screen\">\r\n"
        		+ "    @media screen {\r\n"
        		+ "      /* Thanks Outlook 2013! */\r\n"
        		+ "      * {\r\n"
        		+ "        font-family: 'Oxygen', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\r\n"
        		+ "      }\r\n"
        		+ "    }\r\n"
        		+ "  </style><style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\r\n"
        		+ "    /* Mobile styles */\r\n"
        		+ "    @media only screen and (max-width: 480px) {\r\n"
        		+ "\r\n"
        		+ "      table[class*=\"container-for-gmail-android\"] {\r\n"
        		+ "        min-width: 290px !important;\r\n"
        		+ "        width: 100% !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      img[class=\"force-width-gmail\"] {\r\n"
        		+ "        display: none !important;\r\n"
        		+ "        width: 0 !important;\r\n"
        		+ "        height: 0 !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      table[class=\"w320\"] {\r\n"
        		+ "        width: 320px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "\r\n"
        		+ "      td[class*=\"mobile-header-padding-left\"] {\r\n"
        		+ "        width: 160px !important;\r\n"
        		+ "        padding-left: 0 !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class*=\"mobile-header-padding-right\"] {\r\n"
        		+ "        width: 160px !important;\r\n"
        		+ "        padding-right: 0 !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class=\"header-lg\"] {\r\n"
        		+ "        font-size: 24px !important;\r\n"
        		+ "        padding-bottom: 5px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class=\"content-padding\"] {\r\n"
        		+ "        padding: 5px 0 5px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "       td[class=\"button\"] {\r\n"
        		+ "        padding: 5px 5px 30px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class*=\"free-text\"] {\r\n"
        		+ "        padding: 10px 18px 30px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class~=\"mobile-hide-img\"] {\r\n"
        		+ "        display: none !important;\r\n"
        		+ "        height: 0 !important;\r\n"
        		+ "        width: 0 !important;\r\n"
        		+ "        line-height: 0 !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class~=\"item\"] {\r\n"
        		+ "        width: 140px !important;\r\n"
        		+ "        vertical-align: top !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class~=\"quantity\"] {\r\n"
        		+ "        width: 50px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class~=\"price\"] {\r\n"
        		+ "        width: 90px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class=\"item-table\"] {\r\n"
        		+ "        padding: 30px 20px !important;\r\n"
        		+ "      }\r\n"
        		+ "\r\n"
        		+ "      td[class=\"mini-container-left\"],\r\n"
        		+ "      td[class=\"mini-container-right\"] {\r\n"
        		+ "        padding: 0 15px 15px !important;\r\n"
        		+ "        display: block !important;\r\n"
        		+ "        width: 290px !important;\r\n"
        		+ "      }\r\n"
        		+ "    }\r\n"
        		+ "  </style></head><body bgcolor=\"#f7f7f7\"><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"container-for-gmail-android\" width=\"100%\"><tr><center><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#ffffff\" background=\"http://s3.amazonaws.com/swu-filepicker/4E687TRe69Ld95IDWyEg_bg_top_02.jpg\" style=\"background-color:transparent\"><tr><td width=\"100%\" height=\"80\" valign=\"top\" style=\"text-align: center; vertical-align:middle;\"><center><table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\"><tr><td class=\"pull-left mobile-header-padding-left\" style=\"vertical-align: middle;\"><a class=\"header-md\" href=\"\">Xin chào, "+"" +"</a></td></tr></table></center></td></tr></table></center></td></tr><tr><td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #f7f7f7;\" class=\"content-padding\"><center><table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"w320\"><tr><td class=\"header-lg\">\r\n"
        		+ "              Đã thanh toán!\r\n"
        		+ "            </td></tr><tr><td class=\"free-text\">\r\n"
        		+ "              Cảm ơn quý khách hàng đã đăng ký dịch vụ của NDTGYM.<br> Chúc quý khách có 1 ngày tập luyện thật tốt và vui vẻ!\r\n"
        		+ "            </td></tr><tr><td class=\"button\"><div><a href=\"http://\"\r\n"
        		+ "              style=\"background-color:#28a745;border-radius:5px;color:#ffffff;display:inline-block;font-family:'Cabin', Helvetica, Arial, sans-serif;font-size:14px;font-weight:regular;line-height:45px;text-align:center;text-decoration:none;width:155px;-webkit-text-size-adjust:none;mso-hide:all;\">Đăng ký dịch vụ mới</a></div></td></tr><tr><td class=\"w320\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td class=\"mini-container-left\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td class=\"mini-block-padding\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:separate !important;\"><tr><td class=\"mini-block\"><span class=\"header-sm\">Thông tin khách hàng</span><br />\r\n"
        		+ "                                Võ Thành Nam <br />\r\n"
        		+ "                                0981640851 <br />\r\n"
        		+ "                                thanhnamvo2000@gmail.com \r\n"
        		+ "                              </td></tr></table></td></tr></table></td><td class=\"mini-container-right\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td class=\"mini-block-padding\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"border-collapse:separate !important;\"><tr><td class=\"mini-block\"><span class=\"header-sm\">Thông tin dịch vụ</span><br />\r\n"
        		+ "                                Ngày đăng ký: 10-05-2021 <br /><span class=\"header-sm\">Mã Hóa đơn</span><br />\r\n"
        		+ "                                HD1\r\n"
        		+ "                              </td></tr></table></td></tr></table></td></tr></table></td></tr></table></center></td></tr><tr><td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #ffffff;  border-top: 1px solid #e5e5e5; border-bottom: 1px solid #e5e5e5;\"><center><table cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\"><tr><td class=\"item-table\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr><td class=\"title-dark\" width=\"300\">\r\n"
        		+ "                       Tên lớp\r\n"
        		+ "                    </td><td class=\"title-dark\" width=\"163\">\r\n"
        		+ "                      Gói tập\r\n"
        		+ "                    </td><td class=\"title-dark\" width=\"97\">\r\n"
        		+ "                      Tổng cộng\r\n"
        		+ "                    </td></tr><tr><td class=\"item-col item\"><span style=\"color: #4d4d4d; font-weight:bold;\">Fitness</span></td><td class=\"item-col quantity\">\r\n"
        		+ "                     tháng\r\n"
        		+ "                    </td><td class=\"item-col\">\r\n"
        		+ "                      ₫150,000.00\r\n"
        		+ "                    </td></tr><tr><td class=\"item-col item mobile-row-padding\"></td><td class=\"item-col quantity\"></td><td class=\"item-col price\"></td></tr><tr><td class=\"item-col item\"></td><td class=\"item-col quantity\" style=\"text-align:right; padding-right: 10px; border-top: 1px solid #cccccc;\"><span class=\"total-space\">Tổng phụ</span><br /><span class=\"total-space\">Thuế</span><br /><span class=\"total-space\" style=\"font-weight: bold; color: #4d4d4d\">Thành tiền</span></td><td class=\"item-col price\" style=\"text-align: left; border-top: 1px solid #cccccc;\"><span class=\"total-space\">₫150,000.00</span><br /><span class=\"total-space\">₫0.00</span><br /><span class=\"total-space\" style=\"font-weight:bold; color: #4d4d4d\">₫150,000.00</span></td></tr></table></td></tr></table></center></td></tr><tr><td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #f7f7f7; height: 100px;\"><center><table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"w320\"><tr><td style=\"padding: 5px 0 10px\"><strong>97, Man Thiện</strong><br />\r\n"
        		+ "              Thành phố Thủ Đức <br />\r\n"
        		+ "              Hồ Chí Minh <br /><br /></td></tr></table></center></td></tr></table></div></body></html>";
        helper.setText(html_HoaDon, true);
     // let's attach the infamous windows Sample file (this time copied to c:/)
//        FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
//        helper.addAttachment("CoolImage.jpg", file);
        this.emailSender.send(messages);
		return "sendemail";
	}
	
	
	@RequestMapping("testupdatengay")
	public String testupdatengay() throws ParseException {
		
		LocalDate ngayBD1 = LocalDate.now();
		LocalDate ngayKT1 = ngayBD1.plusDays(1);
		System.out.print("ngayBD1==="+ngayBD1);
		System.out.print("ngayKT1==="+ngayKT1);
		Date ngayBD=new SimpleDateFormat("yyyy/MM/dd").parse((""+ngayBD1).replace("-", "/"));
		Date ngayKT= new SimpleDateFormat("yyyy/MM/dd").parse((""+ngayKT1).replace("-", "/"));
		
		
		theService.updateNgayByMaThe(ngayBD,ngayKT, "T1");
		
		return "testupdatengay";
	}
	
	@RequestMapping("testsession")
	public ModelAndView testsession(HttpSession session, HttpServletResponse response) {
		ModelAndView mav =new ModelAndView("testsession");
		if(!session.getAttribute("maQuyen").equals("0")) mav.addObject("session", "1");
		if( !((""+session.getAttribute("maQuyen")).equals("0"))) {
			mav.addObject("session",session.getAttribute("maQuyen") );
		}else {
			try {
				response.sendRedirect("dangky");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mav;
	}
	
	
	
	//============API=================
	@RequestMapping(value="laygiagoitap",method=RequestMethod.POST)
	public ModelAndView layGiaGoiTap(@RequestParam("lopDV")String lopDV,@RequestParam("goiTap")String goiTap ) {
		//System.out.println("lopDV= "+lopDV);
		//System.out.println("goiTap= "+goiTap );
		
		goiTap=goiTap.trim();
		List<LopDV> lopDVs=lopDVService.selectByTenLop(lopDV);
		
		List<GoiTap> goiTaps=goiTapService.selectByMaLopTenGoiTap(lopDVs.get(0).getMaLop(), goiTap);
		ModelAndView mav=new ModelAndView("apigetgiatien");
		mav.addObject("getGiaTien", goiTaps.get(0).getGia());
		return mav;
	}
	@RequestMapping(value="xoakhachhang",method=RequestMethod.POST)
	public ModelAndView xoakhachhang(@RequestParam("maKH")String maKH ) {
		ModelAndView mav= new ModelAndView("apixoakhachhang");
		List<The> theKH=theService.selectByMaKHNotSort(maKH);
		// kiem tra ma kh có trong bang the ? , khong -> xoa
		if(theKH.isEmpty()) {
			
			File avatar =new File(servletContext.getRealPath("resources/images/"+maKH+".jpg"));
			if(avatar.exists()) {
				avatar.delete();
			}else {
				avatar =new File(servletContext.getRealPath("resources/images/"+maKH+".png"));
				avatar.delete();
			}
			customerService.delete(maKH);
			
			mav.addObject("xoaKH", "1");
			
		}
		else mav.addObject("xoaKH", "0");
		
		
		return mav;
	} 
	@RequestMapping(value="xoagoitap",method=RequestMethod.POST)
	public ModelAndView xoaGoiTap(HttpSession session,HttpServletResponse response,@RequestParam("maGT")String maGT ) throws IOException {
		ModelAndView mav= new ModelAndView("apixoagoitap");
		
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		mav.addObject("thongbaoxoa", 0);
		List<The> theGT=theService.selectByMaGT(maGT);
		// kiem tra ma GT có trong bang the ? , khong -> xoa
		if(theGT.size()==0) {
			
			goiTapService.delete(maGT);
			mav.addObject("thongbaoxoa", 1);
		}
		
		
		
		return mav;
	} 
	
	
	@RequestMapping(value="khoataikhoan",method=RequestMethod.POST)
	public ModelAndView khoaTaiKhoan(HttpSession session,HttpServletResponse response,@RequestParam("maNV")String maNV,@RequestParam("checked")String checked ) throws IOException {
		ModelAndView mav = new ModelAndView("apikhoataikhoan");
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		List<NhanVien> nhanVien = nhanVienService.selectByMaNV(maNV);
		if(!nhanVien.get(0).getTaiKhoan().getPhanQuyen().getChucVu().equals("Chủ tịch")) {
			try {
				
				
				
				if(checked.trim().equals("true")) taiKhoanService.updateByUserName(0, nhanVien.get(0).getTaiKhoan().getUserName()); 
				else if (checked.trim().equals("false")) taiKhoanService.updateByUserName(1, nhanVien.get(0).getTaiKhoan().getUserName());
				mav.addObject("thongBaoUpdate", "1");
			}catch(Exception e){
				mav.addObject("thongBaoUpdate", "0");
				
			}
		}
		return mav;
	}
	
	@RequestMapping(value="khoagoitap",method=RequestMethod.POST)
	public ModelAndView khoaGoiTap(HttpSession session,HttpServletResponse response,@RequestParam("maGT")String maGT,@RequestParam("checked")String checked ) throws IOException {
		ModelAndView mav = new ModelAndView("apikhoagoitap");
		
		// check : Only admin can access this route
		
		
				if(!session.getAttribute("maQuyen").equals("0")) {
					response.sendRedirect("dangky");
				}
		
		mav.addObject("thongbaoupdate", "0");
		List<GoiTap> checkGoiTap= goiTapService.selectByMaGT(maGT);
		if(checkGoiTap.size()>0) {
			if(checked.trim().equals("true")) goiTapService.updateByMaGT(0, maGT);
			else if (checked.trim().equals("false"))goiTapService.updateByMaGT(1, maGT);
			mav.addObject("thongbaoupdate", "1");
		}
		
		
		return mav;
		
	}
	
	@RequestMapping(value="laytengoitap",method=RequestMethod.POST)
	public ModelAndView layTenGoiTap(@RequestParam("lopDV")String lopDV ) throws IOException {
		ModelAndView mav = new ModelAndView("apilaytengoitap");
		
		List<GoiTap> checkGoiTap= goiTapService.selectByTenLopTrangThai(lopDV);
		String tenCacGoiTap = "";
		for (GoiTap goiTap:checkGoiTap) {
			tenCacGoiTap += goiTap.getTenGoiTap()+",";
		}
		mav.addObject("tengoitaps", tenCacGoiTap.substring(0,tenCacGoiTap.length()-1));
		
		return mav;
		
	}
	//kiemtrahethan
	@RequestMapping(value="kiemtrahethan",method=RequestMethod.POST)
	public ModelAndView kiemTraHeThan() {
		ModelAndView mav = new ModelAndView("apikiemtrahethan");
		Date date = new Date();
		List<The> checkNgayHH = theService.selectByNgayHH(date);
		
		for(The checkHH:checkNgayHH) {
			
			if(checkHH.getTrangThai().trim().equals("Hoạt Động")) theService.updateByMaThe("Hết Hạn",checkHH.getMaThe());
		}
		 
		
		return mav;
		
		
	}
	@RequestMapping(value="getinfodiemdanh",method=RequestMethod.POST)
	public ModelAndView getinfodiemdanh(@RequestParam("mathe")String maThe ) {
		List<The> theDD = theService.selectByMaThe(maThe);
		List<String> infoDD=new ArrayList<>(); // tao list ao
		
		List<DiemDanh> diemDanhs= diemDanhService.selectByMaThe(maThe);
		int soLan=0;
		
		infoDD.add(theDD.get(0).getKhachHang().getTenKH());
		infoDD.add(theDD.get(0).getKhachHang().getGioiTinh());
		infoDD.add(theDD.get(0).getKhachHang().getSdt());
		infoDD.add(theDD.get(0).getGoiTap().getTenGoiTap());
		infoDD.add(""+theDD.get(0).getNgayKT());
		infoDD.add(theDD.get(0).getTrangThai());
		
		for(DiemDanh diemDanh:diemDanhs) {
			if(!diemDanh.getSoLan().equals(null)) soLan+=1;
			
		}
		infoDD.add(""+soLan);
		infoDD.add(theDD.get(0).getKhachHang().getMaKH());
		try {
			infoDD.add(theDD.get(0).getKhachHang().getAnh());
		}catch(Exception e) {}
		ModelAndView mav= new ModelAndView("apigetinfodiemdanh");
		mav.addObject("arrayinfo", infoDD);
		return mav;
	}
}
