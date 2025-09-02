package com.reward_project.coin.controller;

import com.reward_project.coin.controller.cmd.AddCoinCmd;
import com.reward_project.coin.controller.cmd.UpdateCoinCmd;
import com.reward_project.coin.controller.converter.CoinVOConverter;
import com.reward_project.coin.controller.vo.BaseVO;
import com.reward_project.coin.controller.vo.MultipleCoinPageVO;
import com.reward_project.coin.controller.vo.SingleCoinPageVO;
import com.reward_project.coin.entity.Coin;
import com.reward_project.coin.exception.CoinDuplicateException;
import com.reward_project.coin.exception.CoinNotExistException;
import com.reward_project.coin.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @PostMapping("/add")
    public BaseVO addCoin(@RequestBody AddCoinCmd addCoinCmd) {
        long start = System.currentTimeMillis();
        long end;
        try {
            coinService.addCoin(addCoinCmd);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (CoinDuplicateException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(501, end - start, false, e.getMessage());
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "金币添加失败，未知异常");
        }
    }

    @PutMapping("/update")
    public BaseVO updateCoin(@RequestBody UpdateCoinCmd updateCoinCmd) {
        long start = System.currentTimeMillis();
        long end;
        try {
            coinService.updateCoin(updateCoinCmd);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (CoinNotExistException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(501, end - start, false, e.getMessage());
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "金币更新失败，未知异常");
        }
    }

    @GetMapping("/search/by/code")
    public SingleCoinPageVO searchByCode(String type, String subType) {
        long start = System.currentTimeMillis();
        long end;
        SingleCoinPageVO singleCoinPageVO = new SingleCoinPageVO();
        try {
            Coin coin = coinService.queryByType(type, subType);
            singleCoinPageVO.setCoinVO(CoinVOConverter.convertToVO(coin));
            end = System.currentTimeMillis();
            singleCoinPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return singleCoinPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            singleCoinPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "金币不存在"));
            return singleCoinPageVO;
        }
    }

    @GetMapping("/search/by/status")
    public MultipleCoinPageVO searchByStatus(String status) {
        long start = System.currentTimeMillis();
        long end;
        MultipleCoinPageVO multipleCoinPageVO = new MultipleCoinPageVO();
        try {
            List<Coin> coins = coinService.queryByStatus(status);
            multipleCoinPageVO.setCoinVOList(CoinVOConverter.convertToVoList(coins));
            end = System.currentTimeMillis();
            multipleCoinPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return multipleCoinPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            multipleCoinPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "金币不存在"));
            return multipleCoinPageVO;
        }
    }

    @GetMapping("/search/by/source")
    public MultipleCoinPageVO searchBySource(String source) {
        long start = System.currentTimeMillis();
        long end;
        MultipleCoinPageVO multipleCoinPageVO = new MultipleCoinPageVO();
        try {
            List<Coin> coins = coinService.queryBySource(source);
            multipleCoinPageVO.setCoinVOList(CoinVOConverter.convertToVoList(coins));
            end = System.currentTimeMillis();
            multipleCoinPageVO.setBaseVO(BaseVO.buildBaseVo(200, end - start, true, null));
            return multipleCoinPageVO;
        } catch (Exception e) {
            end = System.currentTimeMillis();
            multipleCoinPageVO.setBaseVO(BaseVO.buildBaseVo(500, end - start, false, "金币不存在"));
            return multipleCoinPageVO;
        }
    }
}
