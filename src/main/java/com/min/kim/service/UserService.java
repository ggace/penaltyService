package com.min.kim.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.min.kim.dao.UserDao;
import com.min.kim.dto.ResultData;
import com.min.kim.dto.User;
import com.min.kim.util.Util;

@Service
public class UserService {

	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public ResultData<User> getUser(int id) {
		User user = userDao.getUser(id);
		return ResultData.from("S-1", "유저 정보입니다.", "user", user);
	}

	public ResultData<User> login(String loginId, String loginPw) {
		User user = userDao.getUserByLoginId(loginId);
		
		if(user == null) {
			return ResultData.from("F-nullException", "존재하지 않는 아이디 입니다.");
		}
		
		if(!Util.encoding(loginPw).equals(user.getLoginPw())) {
			return ResultData.from("F-nullException", "비밀번호가 일치하지 않습니다.");
		}
		
		return ResultData.from("S-1", "로그인에 성공했습니다.", "loginedId", user); 
	}

	public ResultData logout(HttpSession session) {
		session.removeAttribute("loginedId");
		return ResultData.from("S-1", "로그아웃되었습니다.");
	}

	public ResultData join(String loginId, String loginPw, String email, String nickname) {
		userDao.join(loginId, Util.encoding(loginPw), email, nickname);
		return ResultData.from("S-1", "회원가입되었습니다.");
	}
	
}
