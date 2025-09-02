package com.reward_project.coin.controller.converter;

import com.reward_project.coin.controller.vo.CoinRecordVO;
import com.reward_project.coin.entity.CoinRecord;

import java.util.ArrayList;
import java.util.List;

public class CoinRecordVOConverter {
    public static CoinRecordVO convertToVO (CoinRecord coinRecord) {
        CoinRecordVO coinRecordVO = new CoinRecordVO();
        coinRecordVO.setId(coinRecord.getId());
        coinRecordVO.setCoinType(coinRecord.getType());
        coinRecordVO.setAmount(coinRecord.getAmount());
        coinRecordVO.setStatus(coinRecord.getStatus());
        coinRecordVO.setTheme(coinRecord.getTheme());
        coinRecordVO.setStatus(coinRecord.getStatus());
        coinRecordVO.setOutBizNo(coinRecord.getOutBizNo());
        coinRecordVO.setPrizeTime(coinRecord.getPrizeTime());
        coinRecordVO.setCreateTime(coinRecord.getCreateTime());
        coinRecordVO.setUpdateTime(coinRecord.getUpdateTime());
        return coinRecordVO;
    }

    public static List<CoinRecordVO> coinRecordVOList(List<CoinRecord> coinRecordList) {
        List<CoinRecordVO> coinRecordVOList = new ArrayList<>();
        for(CoinRecord coinRecord : coinRecordList) {
            CoinRecordVO coinRecordVO = convertToVO(coinRecord);
            coinRecordVOList.add(coinRecordVO);
        }
        return coinRecordVOList;
    }
}
