package com.min.kim.dao;

import org.apache.ibatis.annotations.Mapper;

import com.min.kim.dto.User;

@Mapper
public interface UserDao {

	User getUser(int id);

	User getUserByLoginId(String loginId);

	void join(String loginId, String loginPw, String email, String nickname);

}
