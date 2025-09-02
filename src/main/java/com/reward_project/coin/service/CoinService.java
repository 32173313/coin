package com.reward_project.coin.service;

import com.reward_project.coin.controller.cmd.AddCoinCmd;
import com.reward_project.coin.controller.cmd.UpdateCoinCmd;
import com.reward_project.coin.entity.Coin;

import java.util.List;

public interface CoinService {
    void addCoin(AddCoinCmd addCoinCmd);
    void updateCoin(UpdateCoinCmd updateCoinCmd);
    Coin queryByType(String type, String subType);
    Coin queryByTypeForUpdate(String type, String subType);
    List<Coin> queryByStatus(String status);
    List<Coin> queryBySource(String source);
}
