package com.min.kim.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.dto.ResultData;
import com.min.kim.dto.User;
import com.min.kim.service.UserService;
import com.min.kim.util.Util;

@Controller
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/client/user/getUser")
	@ResponseBody
	ResultData<User> getUser(Integer id){
		if(Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return userService.getUser(id);
	}
	
	@RequestMapping(value="/client/user/login", method = RequestMethod.POST)
	@ResponseBody
	ResultData<Integer> login(String loginId, String loginPw, HttpSession session){
		Integer loginedId = (Integer) session.getAttribute("loginedId");
		
		System.out.printf("%s %s\n", loginId, loginPw);
		
		if(loginedId != null) {
			return ResultData.from("F-logined", "로그인 상태입니다.");
		}
		if(Util.isEmpty(loginId)) {
			return ResultData.from("F-nullException", "loginId를 입력해주세요");
		}
		if(Util.isEmpty(loginPw)) {
			return ResultData.from("F-nullException", "loginPw를 입력해주세요");
		}
		ResultData<User> loginedUserRd = userService.login(loginId, loginPw);
		if(loginedUserRd.getData() == null) {
			return ResultData.from(loginedUserRd.getResultCode(), loginedUserRd.getMsg());
		}
		User loginedUser = (User) loginedUserRd.getData();
		session.setAttribute("loginedId", loginedUser.getId());
		
		return ResultData.from(loginedUserRd.getResultCode(), loginedUser.getNickname() + "님 환영합니다.", loginedUserRd.getDataName(), loginedUser.getId());
	}
	
	@RequestMapping("/client/user/logout")
	@ResponseBody
	ResultData logout(HttpSession session){
		
		Integer loginedId = (Integer) session.getAttribute("loginedId");
		if(loginedId == null) {
			return ResultData.from("F-notlogined", "로그인 상태가 아닙니다.");
		}
		
		ResultData logoutedId = userService.logout((HttpSession) session);
		
		return logoutedId;
	}
	
	@RequestMapping("/client/user/join")
	@ResponseBody
	ResultData join(HttpSession session, String loginId, String loginPw, String nickname, String email){
		
		Integer loginedId = (Integer) session.getAttribute("loginedId");
		if(loginedId != null) {
			return ResultData.from("F-logined", "로그인 상태입니다.");
		}
		
		if(Util.isEmpty(loginId)) {
			return ResultData.from("F-nullException", "loginId를 입력해주세요");
		}
		if(Util.isEmpty(loginPw)) {
			return ResultData.from("F-nullException", "loginPw를 입력해주세요");
		}
		if(Util.isEmpty(email)) {
			return ResultData.from("F-nullException", "email를 입력해주세요");
		}
		if(Util.isEmpty(nickname)) {
			return ResultData.from("F-nullException", "nickname를 입력해주세요");
		}	
		
		ResultData logoutedId = userService.join(loginId, loginPw, email, nickname);
		
		return logoutedId;
	}
}
