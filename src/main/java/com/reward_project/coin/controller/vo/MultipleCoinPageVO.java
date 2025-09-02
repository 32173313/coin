package com.reward_project.coin.controller.vo;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MultipleCoinPageVO {
    private List<CoinVO> coinVOList;
    private BaseVO baseVO;
}
