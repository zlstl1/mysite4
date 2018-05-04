package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVO;

@Controller
@RequestMapping(value="/api/gb")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public List<GuestbookVO> list() {
		System.out.println("ajax-list : guestbook");
		List<GuestbookVO> guestbookList = guestbookService.getList();
		
		return guestbookList;
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public GuestbookVO add(@RequestBody GuestbookVO guestbookVo) {
		System.out.println("add");
		GuestbookVO vo = guestbookService.write(guestbookVo);
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax_delete", method=RequestMethod.POST)
	public int ajax_delete(@ModelAttribute GuestbookVO guestbookVo) {
		System.out.println("delete");
		int sucess = guestbookService.ajax_delete(guestbookVo);
		if(sucess!=0) {
			return guestbookVo.getNo();
		}else {
			return 0;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/scrollList", method=RequestMethod.POST)
	public List<GuestbookVO> scrollList(@RequestParam("start") int start,@RequestParam("end") int end) {
		System.out.println("ajax-list : guestbook");
		List<GuestbookVO> guestbookList = guestbookService.getScrollList(start,end);
		return guestbookList;
	}
	
	
}
