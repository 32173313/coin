package com.reward_project.coin.controller.vo;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinRecordPageVO {
    private List<CoinRecordVO> coinRecordVOList;
    private BaseVO baseVO;
}
