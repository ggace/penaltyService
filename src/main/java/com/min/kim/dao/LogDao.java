package com.min.kim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.min.kim.dto.Log;

@Mapper
public interface LogDao {

	List<Log> getLogsInRoom(int roomId);

	void insertLog(Integer roomId, Integer userId, Integer penaltyId);

	int getLastId();

	List<Log> getLogsInRoomWithExtra(Integer roomId);

	List<Log> getSumOfMoneyLogsInRoom(Integer roomId);

}
