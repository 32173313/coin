package com.reward_project.coin.service.impl;

import com.reward_project.coin.controller.cmd.AddCoinCmd;
import com.reward_project.coin.controller.cmd.UpdateCoinCmd;
import com.reward_project.coin.entity.Coin;
import com.reward_project.coin.exception.CoinDuplicateException;
import com.reward_project.coin.exception.CoinNotExistException;
import com.reward_project.coin.mapper.CoinMapper;
import com.reward_project.coin.service.CoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoinServiceImpl implements CoinService {
    
    @Autowired
    private CoinMapper coinMapper;

    Logger logger = LoggerFactory.getLogger(CoinServiceImpl.class);

    @Override
    public void addCoin(AddCoinCmd addCoinCmd) {
        Coin coin = buildAddCoin(addCoinCmd);
        // 要把技术异常转换成业务异常 这样用户可以看懂
        try {
            coinMapper.addCoin(coin);
        } catch (DuplicateKeyException e) {
            throw new CoinDuplicateException(String.format("subType为%s的coin已存在，请勿重复添加", addCoinCmd.getSubType()));
        }
    }

    private static Coin buildAddCoin(AddCoinCmd addCoinCmd) {
        Coin coin = new Coin();
        coin.setType(addCoinCmd.getType());
        coin.setSubType(addCoinCmd.getSubType());
        coin.setStatus(addCoinCmd.getStatus());
        coin.setStock(addCoinCmd.getStock());
        coin.setSource(addCoinCmd.getSource());
        coin.setIcon(addCoinCmd.getIcon());
        return coin;
    }

    @Override
    // 为了扩展性和通用性 就算add和update用的参数一样 也要分开建cmd
    public void updateCoin(UpdateCoinCmd updateCoinCmd) {
        Coin coin = queryByType(updateCoinCmd.getType(), updateCoinCmd.getSubType());
        if(coin == null) {
            throw new CoinNotExistException(String.format("要修改的积分%s不存在", updateCoinCmd.getSubType()));
        }

        // 加一步判空 只有传入参数不为空时进行更新 enable只更新一个字段
        if(updateCoinCmd.getIcon() != null) {
            coin.setIcon(updateCoinCmd.getIcon());
        }
        if(updateCoinCmd.getStatus() != null) {
            coin.setStatus(updateCoinCmd.getStatus());
        }
        if(updateCoinCmd.getStock() != null) {
            coin.setStock(updateCoinCmd.getStock());
        }

        try {
            coinMapper.updateCoin(coin);
        } catch (Exception e) {
            logger.error(String.format("子类型为%s的coin更新失败", updateCoinCmd.getSubType()));
        }

    }

    @Override
    public Coin queryByType(String type, String subType) {
        return coinMapper.queryByType(type, subType);
    }

    @Override
    public Coin queryByTypeForUpdate(String type, String subType) {
        return coinMapper.queryByTypeForUpdate(type, subType);
    }

    @Override
    public List<Coin> queryByStatus(String status) {
        return coinMapper.queryByStatus(status);
    }

    @Override
    public List<Coin> queryBySource(String source) {
        return coinMapper.queryBySource(source);
    }
}
