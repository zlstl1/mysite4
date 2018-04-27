package com.javaex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/joinform", method=RequestMethod.GET)
	public String joinform() {
		System.out.println("joinform");
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		System.out.println(userVo.toString());
		
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginform() {
		System.out.println("loginform");
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("password") String password,@RequestParam("email") String email,HttpSession session,Model model) {
		System.out.println("login");
		UserVo authUser = userService.login(password,email);
		
		if(authUser!=null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}else {
			model.addAttribute("result", "fail");
			return "redirect:/user/loginform";
		}	
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("logout");
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/main";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(@RequestParam("no") int no,Model model) {
		System.out.println("modifyform");
		UserVo userInfo = userService.getUserInfo(no);
		model.addAttribute("userInfo", userInfo);
		return "user/modifyform";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyform(UserVo userVo,HttpSession session) {
		System.out.println("modify");
		int result = userService.userModify(userVo);
		if(result!=0) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			authUser.setNo(userVo.getNo());
			authUser.setName(userVo.getName());
		}
		
		return "redirect:/main";
	}
	
}
