package com.reward_project.coin.service;

import com.reward_project.coin.entity.CoinRecord;

import java.util.Date;
import java.util.List;

public interface CoinRecordService {
    void addCoinRecord(CoinRecord coinRecord);
    void addCoinRecordWithoutException(CoinRecord coinRecord);
    List<CoinRecord> queryByUserIdAndCoinTypeAndDate(int userId, String type, String subType, Date startTime, Date endTime);
}
