package com.reward_project.coin.controller;

import com.reward_project.coin.controller.converter.CoinRecordVOConverter;
import com.reward_project.coin.controller.vo.BaseVO;
import com.reward_project.coin.controller.vo.CoinRecordPageVO;
import com.reward_project.coin.entity.CoinRecord;
import com.reward_project.coin.service.CoinRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coin/record")
public class CoinRecordController {

    @Autowired
    private CoinRecordService coinRecordService;

    @PostMapping("/add")
    // @RequestBody - 把前端传入的json序列化 转换为对象
    public BaseVO addCoinRecord(@RequestBody CoinRecord coinRecord) {
        long start = System.currentTimeMillis();
        long end;
        try {
            coinRecordService.addCoinRecord(coinRecord);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "添加发奖流水失败");
        }
    }

    @GetMapping("/search")
    public CoinRecordPageVO searchByUserIdAndCoinTypeAndDate(int userId, String type, String subType, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") Date endTime) {
        long start = System.currentTimeMillis();
        long end;
        CoinRecordPageVO coinRecordPageVO = new CoinRecordPageVO();
        try {
            List<CoinRecord> coinRecordList = coinRecordService.queryByUserIdAndCoinTypeAndDate(userId, type, subType, startTime, endTime);
            coinRecordPageVO.setCoinRecordVOList(CoinRecordVOConverter.coinRecordVOList(coinRecordList));
            end = System.currentTimeMillis();
            coinRecordPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return coinRecordPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            coinRecordPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "查询发奖流水失败"));
            return coinRecordPageVO;
        }
    }
}
