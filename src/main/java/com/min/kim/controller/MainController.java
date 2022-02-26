package com.min.kim.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.service.RoomService;

@Controller
public class MainController {
	
	
	private RoomService roomService;
	
	public MainController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@RequestMapping("/client/chart")
	public String chart(HttpSession session, Integer roomId, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(roomId == null) {
			return "redirect:/client/notfound";
			
		}
		request.setAttribute("roomId", roomId);
		return "/client/common/chart";
	}
	
	@RequestMapping("/")
	public String home(HttpSession session) {
		return "redirect:/client/main";
	}
	@RequestMapping("/client/main")
	public String main(HttpSession session, HttpServletRequest request) {
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		if(loginedId == 0) {
			return "client/user/login";
		}
		return "client/page/main";
	}
	
	@RequestMapping("/client/detail")
	public String detail(HttpSession session, Integer id, HttpServletRequest request) {
		String encodedParam = "";
		
		
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		if(loginedId == 0) {
			return "client/user/login";
		}
		
		try {
			encodedParam = URLEncoder.encode("id를입력해주세요", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id == null) {
			return "redirect:/client/error?error=" + encodedParam;
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, id).getData()) {
			try {
				encodedParam = URLEncoder.encode("가입 유저가 아닙니다.", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		request.setAttribute("id", id);
		
		return "client/page/detail";
	}
	
	@RequestMapping("/client/error")
	@ResponseBody
	public String error(HttpSession session, String error, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(error != null && !error.equals("")) {
			return "<script>alert(\"" + error + "\");history.back()</script>";
		}
		
		return "history.back()";
	}
	
	@RequestMapping("/client/notfound")
	public String notfound(HttpSession session, String error, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		return "client/page/pageNotFound";
	}
}
