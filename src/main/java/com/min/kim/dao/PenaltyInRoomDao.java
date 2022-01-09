package com.min.kim.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PenaltyInRoomDao {

	public void insertRow(int penaltyId, int roomId);

}
