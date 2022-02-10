package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("loginForm")
	public String loginForm() {

		return "/user/loginForm";
	}

	@RequestMapping("login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("login: " + userVo);

		UserVo authUser = userService.login(userVo);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/";
			
		} else {
			return "redirect:/user/loginForm?login=fail";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	

	@RequestMapping("joinForm")
	public String joinForm() {

		return "/user/joinForm";
	}

	@RequestMapping("join")
	public String join(@ModelAttribute UserVo userVo) {
		// System.out.println(userVo);

		userService.join(userVo);

		return "/user/joinSuccess";
	}

	@ResponseBody
	@RequestMapping("idCheck")
	public String idCheck(@RequestParam("id") String id) {
		System.out.println(id);

		String yon = userService.idCheck(id);
		System.out.println("cId: " + yon);

		return yon;
	}

}
