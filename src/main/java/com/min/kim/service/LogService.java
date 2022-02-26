package com.min.kim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.kim.dao.LogDao;
import com.min.kim.dao.MoneyDao;
import com.min.kim.dao.PenaltyDao;
import com.min.kim.dto.Log;
import com.min.kim.dto.Penalty;
import com.min.kim.dto.ResultData;

@Service
public class LogService {

	private LogDao logDao;
	private PenaltyDao penaltyDao;
	private MoneyDao moneyDao;
	
	public LogService(LogDao logDao, PenaltyDao penaltyDao, MoneyDao moneyDao) {
		this.logDao = logDao;
		this.penaltyDao = penaltyDao;
		this.moneyDao  = moneyDao;
	}

	public ResultData<List<Log>> getLogsInRoom(int roomId) {
		List<Log> logs = logDao.getLogsInRoom(roomId);
		return ResultData.from("S-1", "벌금 리스트입니다.", "logs", logs);
	}

	public ResultData<Object> insertLog(Integer roomId, Integer userId, Integer penaltyId) {
		
		if(penaltyDao.getPenalty(penaltyId) == null) {
			penaltyDao.insertPenalty(null, 0, 0);
		}
		
		logDao.insertLog(roomId, userId, penaltyId);
		
		return ResultData.from("S-1", "로그 추가에 성공했습니다.");
	}

	public ResultData insertLog(Integer roomId, Integer userId, Integer money, String content) {
		
		Integer moneyId = moneyDao.getMoneyIdByMoney(money);
		int type = 1;

		if (moneyId == null) {
			moneyDao.insertMoney(money);
			moneyId = moneyDao.getLastId();
		}
		if (money == 0) {
			type = 0;
		}
		
		
		List<Penalty> penalties = penaltyDao.getPenaltiesByMoneyIdAndContent(moneyId, content.trim());
		
		int penaltyId;
		
		if(penalties.size() == 0) {
			penaltyDao.insertPenalty(content.trim(), moneyId, type);
			
			penaltyId = penaltyDao.getLastID();
		}
		else {
			penaltyId = penalties.get(0).getId();
		}
		
		
		
		logDao.insertLog(roomId, userId, penaltyId);
		
		return ResultData.from("S-1", "로그 추가에 성공했습니다.");
	}

	public ResultData getLogsInRoomWithExtra(Integer roomId) {
		List<Log> logs = logDao.getLogsInRoomWithExtra(roomId);
		return ResultData.from("S-1", "벌금 리스트입니다.", "logs", logs);
	}

	public ResultData getSumOfMoneyLogsInRoom(Integer roomId) {
		List<Log> logs = logDao.getSumOfMoneyLogsInRoom(roomId);
		return ResultData.from("S-1", "벌금 리스트입니다.", "logs", logs);
	}

}
