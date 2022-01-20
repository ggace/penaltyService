package com.min.kim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.min.kim.dto.Room;


@Mapper
public interface RoomDao {

	public List<Room> getRooms();

	public Room getRoom(int id);

	public void insertRoom(String title, int adminId);

	public void deleteRoom(int id);

	public void updateRoom(int id, String title, Integer adminId);

	public int getLastId();

	public void setRoomPw(int id, String pw);

	public Room getRoomWithPw(Integer id);

	public List<Room> getRoomsWithPw();

	public List<Room> getRoomsWithAdmin();

}
