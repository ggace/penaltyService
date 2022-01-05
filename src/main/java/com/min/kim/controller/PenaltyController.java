package com.min.kim.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.dto.Penalty;
import com.min.kim.dto.PenaltyWithMoney;
import com.min.kim.dto.ResultData;
import com.min.kim.service.PenaltyService;
import com.min.kim.util.Util;

@Controller
public class PenaltyController {
	private PenaltyService penaltyService;

	public PenaltyController(PenaltyService penaltyService) {
		this.penaltyService = penaltyService;
	}

	@RequestMapping("/client/penalty/getPenalties")
	@ResponseBody
	public ResultData<List<Penalty>> getPenalties() {
		return penaltyService.getPenalties();
	}

	@RequestMapping("/client/penalty/getPenaltiesWithMoney")
	@ResponseBody
	public ResultData<List<PenaltyWithMoney>> getPenaltiesWithMoney() {
		return penaltyService.getPenaltiesWithMoney();
	}

	@RequestMapping("/client/penalty/getPenalty")
	@ResponseBody
	public ResultData<Penalty> getPenalty(Integer id) {
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return penaltyService.getPenalty(id);
	}

	@RequestMapping("/client/penalty/getPenaltyWithMoney")
	@ResponseBody
	public ResultData<PenaltyWithMoney> getPenaltyWithMoney(Integer id) {
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return penaltyService.getPenaltyWithMoney(id);
	}

	@RequestMapping("/client/penalty/insertPenalty")
	@ResponseBody
	public ResultData<Object> insertPenalty(String content, Integer money) {
		if (Util.isEmpty(content)) {
			return ResultData.from("F-nullException", "content를 입력해주세요");
		}
		if (Util.isEmpty(money)) {
			return ResultData.from("F-nullException", "money를 입력해주세요");
		}
		return penaltyService.insertPenalty(content, money);
	}

	@RequestMapping("/client/penalty/deletePenalty")
	@ResponseBody
	public ResultData<Object> deletePenalty(Integer id) {
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return penaltyService.deletePenalty(id);
	}

	@RequestMapping("/client/penalty/updatePenalty")
	@ResponseBody
	public ResultData<Object> updatePenalty(Integer id, String content, Integer money) {
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		if (Util.isEmpty(content) && Util.isEmpty(money)) {
			return ResultData.from("F-nullException", "content 또는 money를 입력해주세요");
		}
		return penaltyService.updatePenalty(id, content, money);
	}
}
