package com.min.kim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room {
	private int id;
	private String title;
	private String regDate;
	private String updateDate;
	private int adminId;
	private String adminName;
	
	private String pw;
}
