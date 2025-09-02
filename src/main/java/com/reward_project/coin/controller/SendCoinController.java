package com.reward_project.coin.controller;

import com.reward_project.coin.controller.cmd.SendCoinCmd;
import com.reward_project.coin.controller.vo.BaseVO;
import com.reward_project.coin.exception.CoinDeactiveException;
import com.reward_project.coin.exception.CoinInsufficientException;
import com.reward_project.coin.exception.CoinNotExistException;
import com.reward_project.coin.exception.OutBizNoDuplicateException;
import com.reward_project.coin.service.SendCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coin")
public class SendCoinController {

    @Autowired
    private SendCoinService sendCoinService;

    @PostMapping("/send")
    public BaseVO sendCoin(@RequestBody SendCoinCmd sendCoinCmd) {
        long start = System.currentTimeMillis();
        long end;
        try {
            sendCoinService.sendCoin(sendCoinCmd);
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(200, end - start, true, null);
        } catch (OutBizNoDuplicateException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(201, end - start, true, e.getMessage());
        } catch (CoinNotExistException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(501, end - start, false, e.getMessage());
        } catch (CoinDeactiveException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(502, end - start, false, e.getMessage());
        } catch (CoinInsufficientException e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(503, end - start, false, e.getMessage());
        } catch (Exception e) {
            end = System.currentTimeMillis();
            return BaseVO.buildBaseVo(500, end - start, false, "金币发放失败，未知异常");
        }
    }
}
