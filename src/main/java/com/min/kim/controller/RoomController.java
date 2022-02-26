package com.min.kim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.dao.RoomDao;
import com.min.kim.dto.ResultData;
import com.min.kim.dto.Room;
import com.min.kim.service.RoomService;

@Controller
public class RoomController {
	
	private RoomService roomService;
	
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	@RequestMapping("/admin/room/getRoomByUserId")
	@ResponseBody
	public ResultData<List<Room>> getRoomByUserId(HttpSession session, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		
		Integer loginedId = (Integer)request.getAttribute("loginedId");
		
			
		
		return roomService.getRoomByUserId(loginedId);
	}
	
	@RequestMapping("/admin/room/getRooms")
	@ResponseBody
	public ResultData<List<Room>> getRooms(HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		
		return roomService.getRooms();
	}
	
	@RequestMapping("/admin/room/getRoomsWithAdmin")
	@ResponseBody
	public ResultData<List<Room>> getRoomsWithAdmin(HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		
		return roomService.getRoomsWithAdmin();
	}
	
	@RequestMapping("/admin/room/getRoom")
	@ResponseBody
	public ResultData<Room> getRoom(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, id).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return roomService.getRoom(id);
	}
	@RequestMapping("/admin/room/getRoomWithAdminInfo")
	@ResponseBody
	public ResultData<Room> getRoomWithAdminInfo(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, id).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return roomService.getRoomWithAdminInfo(id);
	}
	@RequestMapping("/admin/room/getRoomWithPw")
	@ResponseBody
	public ResultData<Room> getRoomWithPw(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)(request.getAttribute("loginedId"));
		
		
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		
		if(!(boolean) roomService.checkUserInRoom(loginedId, id).getData()) {
			return ResultData.from("F-notmember", "가입 유저가 아닙니다.");
		}
		
		return roomService.getRoomWithPw(id);
	}
	
	@RequestMapping("/admin/room/insertRoom")
	@ResponseBody
	public ResultData<Object> insertRoom(String title, Integer adminId, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		if(title == null || title == "") {
			return ResultData.from("F-nullException", "title을 입력해주세요");
		}
		else if(adminId == null) {
			return ResultData.from("F-nullException", "adminId를 입력해주세요");
		}
		
		return roomService.insertRoom(title, adminId);
	}
	
	@RequestMapping("/admin/room/enterRoom")
	@ResponseBody
	public ResultData<Object> enterRoom(Integer roomId, String pw, HttpSession session, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		Integer loginedId = (Integer)request.getAttribute("loginedId");
		if(roomId == null) {
			return ResultData.from("F-nullException", "roomId을 입력해주세요");
		}
		else if(pw == null || pw == "") {
			return ResultData.from("F-nullException", "pw를 입력해주세요");
		}
		
		
		return roomService.insertRoomMember(roomId, pw, loginedId);
	}
	
	@RequestMapping("/admin/room/deleteRoom")
	@ResponseBody
	public ResultData<Object> deleteRoom(Integer id, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		Integer loginedId = (Integer)request.getAttribute("loginedId");

		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return roomService.deleteRoom(id, (boolean)roomService.checkUserAdminInRoom(loginedId, id).getData());
	}
	
	@RequestMapping("/admin/room/updateRoom")
	@ResponseBody
	public ResultData<Object> updateRoom(Integer id, String title, HttpServletRequest request) {
		if((boolean)(request.getAttribute("isDirectAccess"))) {
			return null;
		}
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return ResultData.from("F-logined", "로그인 상태가 아닙니다.");
		}
		
		//admin필요
		Integer loginedId = (Integer)request.getAttribute("loginedId");
		
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		else if(title == null || title == "") {
			return ResultData.from("F-nullException", "title을 입력해주세요");
		}
		return roomService.updateRoom(id, title, (boolean)roomService.checkUserAdminInRoom(loginedId, id).getData());
	}
}
