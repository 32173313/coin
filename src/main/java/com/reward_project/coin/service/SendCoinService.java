package com.reward_project.coin.service;

import com.reward_project.coin.controller.cmd.SendCoinCmd;

public interface SendCoinService {
    void sendCoin(SendCoinCmd sendCoinCmd);
}
