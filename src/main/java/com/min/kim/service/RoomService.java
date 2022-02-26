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
		
		roomDao.insertRoomMember(adminId, id);
		
		return ResultData.from("S-1", "방 추가에 성공했습니다.");
	}
	
	public ResultData insertRoomMember(int roomId, String pw, int userId) {
		
		Room room = roomDao.getRoomWithPw(roomId);
		
		if(room == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		else if(!room.getPw().equals(pw)) {
			return ResultData.from("F-nullException", "비밀번호가 일치하지 않습니다.");
		}
		
		Integer checkMember = roomDao.checkRoomMember(userId, roomId);
		
		if(checkMember == 1) {
			return ResultData.from("F-alreadyWorked", "이미 가입된 방입니다.");
		}
		
		roomDao.insertRoomMember(userId, roomId);
		
		return ResultData.from("S-1", "방 가입에 성공했습니다.");
	}

	public ResultData<Object> deleteRoom(int id, boolean isAdmin) {
		if(!isAdmin) {
			return ResultData.from("F-logined", "어드민이 아닙니다.");
		}
		if(getRoom(id).getData() == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		roomDao.deleteRoom(id);
		return ResultData.from("S-1", "방 삭제에 성공했습니다.");
	}

	public ResultData<Object> updateRoom(int id, String title, boolean isAdmin) {
		if(getRoom(id).getData() == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		if(!isAdmin) {
			return ResultData.from("F-logined", "어드민이 아닙니다.");
		}
		roomDao.updateRoom(id, title);
		return ResultData.from("S-1", "방 수정에 성공했습니다.");
	}

	public ResultData<Room> getRoomWithPw(Integer id) {
		Room room = roomDao.getRoomWithPw(id);
		if(room == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		return ResultData.from("S-1", "방입니다.", "room", room);
	}

	public ResultData<List<Room>> getRoomsWithPw() {
		List<Room> rooms = roomDao.getRoomsWithPw();
		return ResultData.from("S-1", "방들입니다.", "rooms", rooms);
	}

	public ResultData<List<Room>> getRoomsWithAdmin() {
		List<Room> rooms = roomDao.getRoomsWithAdmin();
		return ResultData.from("S-1", "방들입니다.", "rooms", rooms);
	}

	public ResultData<List<Room>> getRoomByUserId(Integer userId) {
		List<Room> rooms = roomDao.getRoomsByUserId(userId);
		
		return ResultData.from("S-1", "방입니다.", "rooms", rooms);
	}

	public ResultData<Room> getRoomWithAdminInfo(Integer id) {
		Room room = roomDao.getRoomWithAdminInfo(id);
		if(room == null) {
			return ResultData.from("F-nullException", "방이 존재하지 않습니다.");
		}
		return ResultData.from("S-1", "방입니다.", "room", room);
	}
	
	public ResultData checkUserInRoom(int userId, int roomId) {
		
		boolean isMember = roomDao.checkUserInRoom(userId, roomId); 
		
		return ResultData.from("S-1", "방에 구성원입니다.", "isMember", isMember);
	}
	public ResultData checkUserAdminInRoom(int userId, int roomId) {
		
		boolean isAdmin = roomDao.checkUserAdminInRoom(userId, roomId); 
		
		return ResultData.from("S-1", "방에 구성원입니다.", "isMember", isAdmin);
	}

}
