package com.min.kim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.min.kim.dto.Penalty;

@Mapper
public interface PenaltyDao {

	List<Penalty> getPenalties();

	List<Penalty> getPenaltiesWithMoney();

	Penalty getPenalty(int id);

	Penalty getPenaltyWithMoney(int id);

	void insertPenalty(String content, int moneyId, int type);

	void deletePenalty(int id);

	void updatePenalty(int id, String content, Integer moneyId, Integer type);
	
	int getLastID();

	Penalty getPenaltyWithRoom(Integer id);

	List<Penalty> getPenaltiesWithRoom();

	Penalty getPenaltyWithRoomAndMoney(Integer id);

	List<Penalty> getPenaltiesWithRoomAndMoney();

}
