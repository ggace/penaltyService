package com.min.kim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String loginId;
	private String loginPw;
	private String nickname;
	private String email;
	private String regDate;
	private String updateDate;
	private boolean delStatus;
	private String delDate;
}
