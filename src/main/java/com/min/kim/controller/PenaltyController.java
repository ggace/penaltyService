package com.min.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.dto.Penalty;
import com.min.kim.dto.ResultData;
import com.min.kim.service.PenaltyService;
import com.min.kim.service.RoomService;
import com.min.kim.util.Util;

@Controller
public class PenaltyController {
	private PenaltyService penaltyService;
	
	public PenaltyController(PenaltyService penaltyService) {
		this.penaltyService = penaltyService;
	}

	@RequestMapping("/admin/penalty/getPenalties")
	@ResponseBody
	public ResultData<List<Penalty>> getPenalties(HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		return penaltyService.getPenalties();
	}

	@RequestMapping("/admin/penalty/getPenaltiesWithMoney")
	@ResponseBody
	public ResultData<List<Penalty>> getPenaltiesWithMoney(HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		return penaltyService.getPenaltiesWithMoney();
	}

	@RequestMapping("/admin/penalty/getPenalty")
	@ResponseBody
	public ResultData<Penalty> getPenalty(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return penaltyService.getPenalty(id);
	}

	@RequestMapping("/admin/penalty/getPenaltyWithMoney")
	@ResponseBody
	public ResultData<Penalty> getPenaltyWithMoney(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return penaltyService.getPenaltyWithMoney(id);
	}

	@RequestMapping("/admin/penalty/insertPenalty")
	@ResponseBody
	public ResultData<Object> insertPenalty(String content, Integer money, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		if (Util.isEmpty(content)) {
			return ResultData.from("F-nullException", "content를 입력해주세요");
		}
		if (Util.isEmpty(money)) {
			return ResultData.from("F-nullException", "money를 입력해주세요");
		}
		return penaltyService.insertPenalty(content, money);
	}

	@RequestMapping("/admin/penalty/deletePenalty")
	@ResponseBody
	public ResultData<Object> deletePenalty(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return penaltyService.deletePenalty(id);
	}

	@RequestMapping("/admin/penalty/updatePenalty")
	@ResponseBody
	public ResultData<Object> updatePenalty(Integer id, String content, Integer money, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		if (Util.isEmpty(id)) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		if (Util.isEmpty(content) && Util.isEmpty(money)) {
			return ResultData.from("F-nullException", "content 또는 money를 입력해주세요");
		}
		return penaltyService.updatePenalty(id, content, money);
	}
	
}
