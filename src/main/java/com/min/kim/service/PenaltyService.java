package com.min.kim.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.kim.dao.MoneyDao;
import com.min.kim.dao.PenaltyDao;
import com.min.kim.dto.Penalty;
import com.min.kim.dto.ResultData;


@Service
public class PenaltyService {

	private PenaltyDao penaltyDao;
	private MoneyDao moneyDao;
	
	public PenaltyService(PenaltyDao penaltyDao, MoneyDao moneyDao) {
		this.penaltyDao = penaltyDao;
		this.moneyDao = moneyDao;
		
	}

	public ResultData<List<Penalty>> getPenalties() {
		List<Penalty> penalties = penaltyDao.getPenalties();
		return ResultData.from("S-1", "요금 종류들입니다.", "penalties", penalties);
	}

	public ResultData<List<Penalty>> getPenaltiesWithMoney() {
		List<Penalty> penalties = penaltyDao.getPenaltiesWithMoney();
		return ResultData.from("S-1", "요금 종류들과 그에 따른 돈입니다.", "penaltiesWithMoney", penalties);
	}

	public ResultData<Penalty> getPenalty(int id) {
		Penalty penalty = penaltyDao.getPenalty(id);
		if (penalty == null) {
			return ResultData.from("S-1", "존재하지 않는 요금입니다.");
		}
		return ResultData.from("S-1", "요금입니다.", "penalty", penalty);
	}

	public ResultData<Penalty> getPenaltyWithMoney(int id) {
		Penalty penalty = penaltyDao.getPenaltyWithMoney(id);
		if (penalty == null) {
			return ResultData.from("S-1", "존재하지 않는 요금입니다.");
		}
		return ResultData.from("S-1", "요금과 그에 따른 돈입니다.", "penalty", penalty);
	}

	public ResultData<Object> insertPenalty(String content, int money) {

		Integer moneyId = moneyDao.getMoneyIdByMoney(money);
		int type = 1;

		if (moneyId == null) {
			moneyDao.insertMoney(money);
			moneyId = moneyDao.getLastId();
		}
		if (money == 0) {
			type = 0;
		}

		penaltyDao.insertPenalty(content.trim(), moneyId, type);
		
		return ResultData.from("S-1", "요금이 추가되었습니다.");
	}

	public ResultData<Object> deletePenalty(int id) {
		Penalty penalty = penaltyDao.getPenalty(id);
		if (penalty == null) {
			return ResultData.from("S-1", "존재하지 않는 요금입니다.");
		}
		penaltyDao.deletePenalty(id);
		return ResultData.from("S-1", "요금이 삭제되었습니다.");
	}

	public ResultData<Object> updatePenalty(int id, String content, Integer money) {
		Penalty penalty = penaltyDao.getPenalty(id);
		if (penalty == null) {
			return ResultData.from("S-1", "존재하지 않는 요금입니다.");
		}
		Integer moneyId = null;
		Integer type = 1;

		if (money != null) {
			moneyId = moneyDao.getMoneyIdByMoney(money);

			if (moneyId == null) {
				moneyDao.insertMoney(money);
				moneyId = moneyDao.getLastId();
			}
			if (money == 0) {
				type = 0;
			}
		}
		penaltyDao.updatePenalty(id, content.trim(), moneyId, type);
		return ResultData.from("S-1", "요금이 수정되었습니다.");
	}

}
