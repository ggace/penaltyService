package com.min.kim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.min.kim.dto.Penalty;
import com.min.kim.dto.PenaltyWithMoney;

@Mapper
public interface PenaltyDao {

	List<Penalty> getPenalties();

	List<PenaltyWithMoney> getPenaltiesWithMoney();

	Penalty getPenalty(int id);

	PenaltyWithMoney getPenaltyWithMoney(int id);

	void insertPenalty(String content, int moneyId, int type);

	void deletePenalty(int id);

	void updatePenalty(int id, String content, Integer moneyId, Integer type);

}
