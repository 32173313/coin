package com.reward_project.coin.mapper;

import com.reward_project.coin.entity.CoinRecord;
import com.reward_project.coin.util.TimeConverterUtil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
// 这个类必须是public的
public class CoinRecordMapperTest {

    @Autowired
    private CoinRecordMapper coinRecordMapper;

    @Test
    public void addCoinRecordTest() {
        CoinRecord coinRecord = new CoinRecord();
        coinRecord.setType("holiday");
        coinRecord.setAmount(1234L);
        coinRecord.setStatus("Active");
        coinRecord.setTheme("musicPlay");
        coinRecord.setOutBizNo("123");
        coinRecord.setUserId(5);
        coinRecordMapper.addCoinRecord(coinRecord);
    }

    @Test
    public void queryByUserIdAndCoinTypeAndDateTest() {
        List<CoinRecord> coins = coinRecordMapper.queryByUserIdAndCoinTypeAndDate(5, "holiday", TimeConverterUtil.setTime(0, 0, 0), TimeConverterUtil.setTime(23, 59, 59));
        Assertions.assertEquals(1, coins.size());
        Assertions.assertEquals("123", coins.get(0).getOutBizNo());
    }

}