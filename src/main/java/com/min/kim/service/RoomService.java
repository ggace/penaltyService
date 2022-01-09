package com.min.kim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.kim.dao.RoomDao;
import com.min.kim.dto.ResultData;
import com.min.kim.dto.Room;

@Service
public class RoomService {

	private RoomDao roomDao;
	
	
	public RoomService(RoomDao classDao) {
		this.roomDao = classDao;		
	}

	public ResultData<List<Room>> getRooms() {
		List<Room> rooms = roomDao.getRooms();
		return ResultData.from("S-1", "방들입니다.", "rooms", rooms);
	}

	public ResultData<Room> getRoom(int id) {
		Room room = roomDao.getRoom(id);
		if(room == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		return ResultData.from("S-1", "방들입니다.", "room", room);
	}

	public ResultData<Object> insertRoom(String title, int adminId) {
		roomDao.insertRoom(title, adminId);
		int id = roomDao.getLastId();
		
		long seed = System.currentTimeMillis();

		roomDao.setRoomPw(id, String.valueOf(seed));
		return ResultData.from("S-1", "방 추가에 성공했습니다.");
	}

	public ResultData<Object> deleteRoom(int id) {
		if(getRoom(id).getData() == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		roomDao.deleteRoom(id);
		return ResultData.from("S-1", "방 삭제에 성공했습니다.");
	}

	public ResultData<Object> updateRoom(int id, String title, Integer adminId) {
		if(getRoom(id).getData() == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		roomDao.updateRoom(id, title, adminId);
		return ResultData.from("S-1", "방 수정에 성공했습니다.");
	}

	public ResultData<Room> getRoomWithPw(Integer id) {
		Room room = roomDao.getRoomWithPw(id);
		if(room == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		return ResultData.from("S-1", "방들입니다.", "room", room);
	}

	public ResultData<List<Room>> getRoomsWithPw() {
		List<Room> rooms = roomDao.getRoomsWithPw();
		return ResultData.from("S-1", "방들입니다.", "rooms", rooms);
	}

}
