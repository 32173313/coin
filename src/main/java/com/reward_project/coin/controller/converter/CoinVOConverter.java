package com.reward_project.coin.controller.converter;

import com.reward_project.coin.controller.vo.CoinVO;
import com.reward_project.coin.entity.Coin;

import java.util.ArrayList;
import java.util.List;

public class CoinVOConverter {
    public static CoinVO convertToVO(Coin coin) {
        CoinVO coinVO = new CoinVO();
        coinVO.setId(coin.getId());
        coinVO.setType(coin.getType());
        coinVO.setSubType(coin.getSubType());
        coinVO.setIcon(coin.getIcon());
        coinVO.setSource(coin.getSource());
        coinVO.setStatus(coin.getStatus());
        coinVO.setStock(coin.getStock());
        coinVO.setCreateTime(coin.getCreateTime());
        coinVO.setUpdateTime(coin.getUpdateTime());
        return coinVO;
    }

    public static List<CoinVO> convertToVoList(List<Coin> coinList) {
        List<CoinVO> coinVOList = new ArrayList<>();
        for (Coin coin : coinList) {
            CoinVO coinVO = convertToVO(coin);
            coinVOList.add(coinVO);
        }
        return coinVOList;
    }
}
