//package com.reward_project.coin.mapper;
//
//import com.reward_project.coin.entity.Coin;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class CoinMapperTest {
//
//    @Autowired
//    private CoinMapper coinMapper;
//    // 有期待结果 比如查询时 用Assertion验证查询结果
//
//    // @Test是给方法执行的主方法入口
//    @Test
//    public void addCoinTest() {
//        Coin coin = new Coin();
//        coin.setCode("holiday");
//        coin.setIcon("www.music.com");
//        coin.setSource("google");
//        coin.setStatus("Active");
//        coin.setStock(1234L);
//        coinMapper.addCoin(coin);
//    }
//
//    @Test
//    public void updateCoinTest() {
//        Coin coin = coinMapper.queryByType("holiday");
//        coin.setStock(5555L);
//        coin.setCode("holiday");
//        coinMapper.updateCoin(coin);
//    }
//
//    @Test
//    public void queryByTypeTest() {
//        Coin coin = coinMapper.queryByType("holiday");
//        Assertions.assertEquals("holiday", coin.getCode());
//        Assertions.assertEquals("google", coin.getSource());
//    }
//
//    @Test
//    public void queryByStatusTest() {
//        List<Coin> coins = coinMapper.queryByStatus("Active");
//        Assertions.assertEquals(1, coins.size());
//        Assertions.assertEquals("google", coins.get(0).getSource());
//    }
//}