package com.min.kim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PenaltyWithMoney {
	private int id;
	private String content;
	private int moneyId;
	private String regDate;
	private String updateDate;
	private boolean type;
	private int money;
}
