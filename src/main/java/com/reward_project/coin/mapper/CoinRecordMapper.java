package com.reward_project.coin.mapper;

import com.reward_project.coin.entity.CoinRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CoinRecordMapper {
    void addCoinRecord(CoinRecord coinRecord);
    List<CoinRecord> queryByUserIdAndCoinTypeAndDate(int userId, String type, String subType, Date startTime, Date endTime);
}
