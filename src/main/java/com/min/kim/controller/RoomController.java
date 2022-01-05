package com.min.kim.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.kim.dto.ResultData;
import com.min.kim.dto.Room;
import com.min.kim.service.RoomService;

@Controller
public class RoomController {
	
	private RoomService classService;
	
	public RoomController(RoomService classService) {
		this.classService = classService;
	}
	
	@RequestMapping("/client/room/getRooms")
	@ResponseBody
	public ResultData<List<Room>> getRooms() {
		
		return classService.getRooms();
	}
	
	@RequestMapping("/client/room/getRoom")
	@ResponseBody
	public ResultData<Room> getRoom(Integer id) {
		
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		
		return classService.getRoom(id);
	}
	
	@RequestMapping("/client/room/insertRoom")
	@ResponseBody
	public ResultData<Object> insertRoom(String title, Integer adminId) {
		
		if(title == null || title == "") {
			return ResultData.from("F-nullException", "title을 입력해주세요");
		}
		else if(adminId == null) {
			return ResultData.from("F-nullException", "adminId를 입력해주세요");
		}
		
		return classService.insertRoom(title, adminId);
	}
	
	@RequestMapping("/client/room/deleteRoom")
	@ResponseBody
	public ResultData<Object> deleteRoom(Integer id) {
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		return classService.deleteRoom(id);
	}
	
	@RequestMapping("/client/room/updateRoom")
	@ResponseBody
	public ResultData<Object> updateRoom(Integer id, String title, Integer adminId) {
		if(id == null) {
			return ResultData.from("F-nullException", "id를 입력해주세요");
		}
		else if(title == null || title == "") {
			return ResultData.from("F-nullException", "title을 입력해주세요");
		}
		return classService.updateRoom(id, title, adminId);
	}
}
