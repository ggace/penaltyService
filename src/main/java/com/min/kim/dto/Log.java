package com.min.kim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
	int id;
	int roomId;
	int userId;
	int penaltyId;
	
	String regDate;
	String updateDate;
	
	String content;
	Integer money;
	String nickname;
	
	Integer sumOfMoney;
}
