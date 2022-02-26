package com.min.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.dto.Log;
import com.min.kim.dto.ResultData;
import com.min.kim.service.LogService;
import com.min.kim.service.RoomService;
import com.min.kim.util.Util;

@Controller
public class LogController {
	private LogService logService;
	private RoomService roomService;
	
	public LogController(LogService logService, RoomService roomService) {
		this.logService = logService;
		this.roomService = roomService;
	}

	@RequestMapping("/admin/log/getLogsInRoom")
	@ResponseBody
	public ResultData<List<Log>> getLogsInRoom(Integer roomId, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		
		if(roomId == null) {
			return ResultData.from("F-nullException", "roomId를 입력해주세요");
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, roomId).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return logService.getLogsInRoom(roomId);
	}
	
	@RequestMapping("/admin/log/insertLogWithPenalty")
	@ResponseBody
	public ResultData insertLog(Integer roomId, Integer userId, Integer penaltyId, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		if(roomId == null) {
			return ResultData.from("F-nullException", "roomId를 입력해주세요");
		}
		if(userId == null) {
			return ResultData.from("F-nullException", "userId를 입력해주세요");
		}
		if(penaltyId == null) {
			return ResultData.from("F-nullException", "penaltyId를 입력해주세요");
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, roomId).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return logService.insertLog(roomId, userId, penaltyId);
	}
	
	@RequestMapping("/admin/log/insertLogWithNew")
	@ResponseBody
	public ResultData insertLog(Integer roomId, Integer userId, Integer money, String content, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		if(roomId == null) {
			return ResultData.from("F-nullException", "roomId를 입력해주세요");
		}
		if(userId == null) {
			return ResultData.from("F-nullException", "userId를 입력해주세요");
		}
		if(money == null) {
			return ResultData.from("F-nullException", "money를 입력해주세요");
		}
		if(Util.isEmpty(content)) {
			return ResultData.from("F-nullException", "content를 입력해주세요");
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, roomId).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return logService.insertLog(roomId, userId, money, content);
	}
	@RequestMapping("/admin/log/getLogsInRoomWithExtra")
	@ResponseBody
	public ResultData getLogsInRoomWithExtra(Integer roomId, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
				
		if(roomId == null) {
			return ResultData.from("F-nullException", "roomId를 입력해주세요");
		}
		if(!(boolean) roomService.checkUserInRoom(loginedId, roomId).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return logService.getLogsInRoomWithExtra(roomId);
	}
	
	@RequestMapping("/admin/log/getSumOfMoneyLogsInRoomByUserId")
	@ResponseBody
	public ResultData getSumOfMoneyLogsInRoomByUserId(Integer roomId, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
				
		if(roomId == null) {
			return ResultData.from("F-nullException", "roomId를 입력해주세요");
		}
		if(!(boolean) roomService.checkUserInRoom(loginedId, roomId).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return logService.getSumOfMoneyLogsInRoom(roomId);
	}
}
