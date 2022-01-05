package com.min.kim.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MoneyDao {

	Integer getMoneyIdByMoney(int money);

	void insertMoney(int money);

	Integer getLastId();

}
