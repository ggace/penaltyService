package com.min.kim.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.kim.dto.ResultData;

@Controller
public class MainController {
	@RequestMapping("/")
	public String home(HttpSession session) {
		return "redirect:/client/main";
	}
	@RequestMapping("/client/main")
	public String main(HttpSession session) {
		Integer loginedId = (Integer) session.getAttribute("loginedId");
		if(loginedId == null) {
			return "client/user/login";
		}
		return "client/main";
	}
}
