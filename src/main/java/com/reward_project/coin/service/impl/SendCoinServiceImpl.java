package com.reward_project.coin.service.impl;

import com.reward_project.coin.controller.cmd.SendCoinCmd;
import com.reward_project.coin.controller.cmd.UpdateCoinCmd;
import com.reward_project.coin.entity.Coin;
import com.reward_project.coin.entity.CoinRecord;
import com.reward_project.coin.enums.CoinRecordStatusEnum;
import com.reward_project.coin.enums.CoinStatusEnum;
import com.reward_project.coin.exception.CoinDeactiveException;
import com.reward_project.coin.exception.CoinInsufficientException;
import com.reward_project.coin.exception.CoinNotExistException;
import com.reward_project.coin.exception.CoinReductionAndAddCoinRecordException;
import com.reward_project.coin.service.CoinRecordService;
import com.reward_project.coin.service.CoinService;
import com.reward_project.coin.service.SendCoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SendCoinServiceImpl implements SendCoinService {

    @Autowired
    private CoinService coinService;

    @Autowired
    private CoinRecordService coinRecordService;

    Logger logger = LoggerFactory.getLogger(SendCoinServiceImpl.class);

    @Override
    public void sendCoin(SendCoinCmd sendCoinCmd) {

        // 1. 检查coin的状态（active）及数量（大于等于发放数量）
        Coin coin = checkCoinStatusAndStock(sendCoinCmd);

        // 2. 以事务的形式进行 1) 对coin进行扣减 2）插入coin发放流水
        try {
            coinReductionAndAddCoinRecord(sendCoinCmd, coin);
        } catch (Exception e) {
            logger.error(String.format("send coin或插入流水失败，外部业务号为%s", sendCoinCmd.getOutBizNo()));
            throw new CoinReductionAndAddCoinRecordException(String.format("send coin或插入流水失败，外部业务号为%s", sendCoinCmd.getOutBizNo()));
        }

    }

    // 事务中的方法都不能catch异常 否则不会回滚 但事务外部被调用的时候需要catch
    // 如果有要catch异常的方法 则封装两个版本：有异常+catch异常

//    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
//    private void coinReductionAndAddCoinRecord(SendCoinCmd sendCoinCmd, Coin coin) {
//        UpdateCoinCmd updateCoinCmd = buildUpdateCoinCmd(sendCoinCmd, coin);
//        coinService.updateCoin(updateCoinCmd);
//
//        CoinRecord coinRecord = buildCoinRecord(sendCoinCmd);
//        coinRecordService.addCoinRecordWithoutException(coinRecord);
//    }

    // 多系统多数据库时 手动把更新的数据换回去
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    private void coinReductionAndAddCoinRecord(SendCoinCmd sendCoinCmd, Coin coin) {
        UpdateCoinCmd updateCoinCmd = buildUpdateCoinCmd(sendCoinCmd, coin);
        coinService.updateCoin(updateCoinCmd);

        CoinRecord coinRecord = buildCoinRecord(sendCoinCmd);
        try {
            coinRecordService.addCoinRecordWithoutException(coinRecord);
        } catch (Exception e) {
            logger.error(String.format("发subType为%s的coin失败，外部业务号为%s，已退还数量为%d的coin", coin.getSubType(), sendCoinCmd.getOutBizNo(), sendCoinCmd.getPrizeAmount()));
            updateCoinCmd.setStock(updateCoinCmd.getStock() + sendCoinCmd.getPrizeAmount());
            coinService.updateCoin(updateCoinCmd);
        }

    }

    private static CoinRecord buildCoinRecord(SendCoinCmd sendCoinCmd) {
        CoinRecord coinRecord = new CoinRecord();
        coinRecord.setUserId(sendCoinCmd.getUserId());
        coinRecord.setType(sendCoinCmd.getPrizeType());
        coinRecord.setSubType(sendCoinCmd.getPrizeSubType());
        coinRecord.setAmount(sendCoinCmd.getPrizeAmount());
        coinRecord.setStatus(CoinRecordStatusEnum.COMPLETE.getCode());
        coinRecord.setOutBizNo(sendCoinCmd.getOutBizNo());
        coinRecord.setTheme(sendCoinCmd.getTheme());
        return coinRecord;
    }

    private UpdateCoinCmd buildUpdateCoinCmd(SendCoinCmd sendCoinCmd, Coin coin) {
        UpdateCoinCmd updateCoinCmd = new UpdateCoinCmd();
        updateCoinCmd.setType(sendCoinCmd.getPrizeType());
        updateCoinCmd.setSubType(sendCoinCmd.getPrizeSubType());
        updateCoinCmd.setStock(coin.getStock() - sendCoinCmd.getPrizeAmount());
        return updateCoinCmd;
    }

    private Coin checkCoinStatusAndStock(SendCoinCmd sendCoinCmd) {
        // 用加了写锁的方法 防止多个项目同时对数据库的数据进行写操作
        Coin coin = coinService.queryByTypeForUpdate(sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType());
        if(coin == null) {
            logger.error(String.format("type为%s, subType为%s的奖励不存在", sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType()));
            throw new CoinNotExistException(String.format("type为%s, subType为%s的奖励不存在", sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType()));
        }
        if(!coin.getStatus().equals(CoinStatusEnum.ACTIVE.getCode())) {
            logger.error(String.format("type为%s, subType为%s的奖励非法", sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType()));
            throw new CoinDeactiveException(String.format("type为%s, subType为%s的奖励非法", sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType()));
        }
        if(coin.getStock() < sendCoinCmd.getPrizeAmount()) {
            logger.error(String.format("type为%s, subType为%s的奖励数量不足，stock为%d", sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType(), coin.getStock()));
            throw new CoinInsufficientException(String.format("type为%s, subType为%s的奖励数量不足，stock为%d", sendCoinCmd.getPrizeType(), sendCoinCmd.getPrizeSubType(), coin.getStock()));
        }
        return coin;
    }
}
