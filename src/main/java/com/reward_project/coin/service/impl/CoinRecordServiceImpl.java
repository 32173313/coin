package com.reward_project.coin.service.impl;

import com.reward_project.coin.entity.CoinRecord;
import com.reward_project.coin.exception.OutBizNoDuplicateException;
import com.reward_project.coin.mapper.CoinRecordMapper;
import com.reward_project.coin.service.CoinRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CoinRecordServiceImpl implements CoinRecordService {

    @Autowired
    private CoinRecordMapper coinRecordMapper;

    Logger logger = LoggerFactory.getLogger(CoinRecordServiceImpl.class);

    @Override
    public void addCoinRecord(CoinRecord coinRecord) {
        try {
            addCoinRecordWithoutException(coinRecord);
        } catch (DuplicateKeyException e) {
            logger.error(String.format("该记录已存在，外部业务号为%s", coinRecord.getOutBizNo()));
            throw new OutBizNoDuplicateException(String.format("该记录已存在，外部业务号为%s", coinRecord.getOutBizNo()));
        } catch (Exception e) {
            logger.error(String.format("该记录插入失败，外部业务号为%s，未知异常", coinRecord.getOutBizNo()));
        }
    }

    @Override
    public void addCoinRecordWithoutException(CoinRecord coinRecord) {
        coinRecordMapper.addCoinRecord(coinRecord);
    }

    @Override
    public List<CoinRecord> queryByUserIdAndCoinTypeAndDate(int userId, String type, String subType, Date startTime, Date endTime) {
        return coinRecordMapper.queryByUserIdAndCoinTypeAndDate(userId, type, subType, startTime, endTime);
    }
}
