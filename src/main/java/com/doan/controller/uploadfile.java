package com.doan.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class uploadfile {
	@RequestMapping("uploadimage")
	public String upload() {
		
		return "uploadfile";
	}
	@Autowired
	ServletContext c;
	@RequestMapping(value="uploadimage",method = RequestMethod.POST)
	public @ResponseBody ModelAndView upload(@RequestParam("image") MultipartFile file) {
		ModelAndView mav= new ModelAndView("uploadfile");
		String name="a";
		
		String extensionFile=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (!file.isEmpty() && (extensionFile.trim()=="jsp" || extensionFile.trim()=="png") ) {
			
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				File dir = new File(c.getRealPath("E:\\upload"));
//				if (!dir.exists())
//					dir.mkdirs();
				
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				file.transferTo(serverFile);
//				BufferedOutputStream stream = new BufferedOutputStream(
//						new FileOutputStream(serverFile));
//				stream.write(bytes);
//				stream.close();

				
				mav.addObject("trangThai", "success");
				return mav;
				
				 } catch (Exception e) {
				  
				  mav.addObject("trangThai", "You failed to upload " + name + " => " +
				  e.getMessage()); return mav; }
				 
		} else {
			
			mav.addObject("trangThai","You failed to upload " + name+ " because the file was empty.");
			return mav;
		}
	}
}
