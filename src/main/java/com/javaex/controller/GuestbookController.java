package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVO;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list");
		List<GuestbookVO> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestbookVO guestbookVo) {
		System.out.println("add");
		guestbookService.add(guestbookVo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String add(@RequestParam("no") int no,Model model) {
		System.out.println("deleteform");
		model.addAttribute("no");
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVO guestbookVo) {
		System.out.println("delete");
			guestbookService.delete(guestbookVo);
		return "redirect:/guestbook/list";
	}
	
}