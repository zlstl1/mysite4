package com.javaex.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;
import com.javaex.vo.FileVo;

@Controller
@RequestMapping(value="/ga")
public class GalleryController {

	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list");
		List<FileVo> fileList = fileUploadService.getFiles();
		model.addAttribute("fileList",fileList);
		return "fileupload/list";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		System.out.println("upload");
		
		fileUploadService.restore(file);
		
		return "redirect:/ga/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("no") int no) {
		System.out.println("delete");
		
		fileUploadService.delete(no);
		
		return "redirect:/ga/list";
	}
}
