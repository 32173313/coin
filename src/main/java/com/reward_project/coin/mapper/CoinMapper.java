package com.reward_project.coin.mapper;

import com.reward_project.coin.entity.Coin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoinMapper {
    void addCoin(Coin coin);
    void updateCoin(Coin coin);
    Coin queryByType(String type, String subType);
    Coin queryByTypeForUpdate(String type, String subType);
    List<Coin> queryByStatus(String status);
    List<Coin> queryBySource(String source);
}
