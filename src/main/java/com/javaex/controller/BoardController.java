package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list");
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform() {
		System.out.println("writeform");
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("write");
		boardService.write(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(@RequestParam("no") int no,@RequestParam("hit") int hit, Model model) {
		System.out.println("view");
		boardService.hitup(no,hit);
		BoardVo boardVo = boardService.view(no);
		model.addAttribute("BoardVo",boardVo);
		return "board/view";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no, Model model) {
		System.out.println("modifyform");
		BoardVo boardVo = boardService.view(no);
		model.addAttribute("vo",boardVo);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyform(@ModelAttribute BoardVo boardVo) {
		System.out.println("modify");
		boardService.modify(boardVo);
		return "board/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("no") int no) {
		System.out.println("delete");
		boardService.delete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam("kwd") String kwd,Model model) {
		System.out.println("search");
		List<BoardVo> list = boardService.search(kwd);
		model.addAttribute("list", list);
		return "board/list";
	}
	
}