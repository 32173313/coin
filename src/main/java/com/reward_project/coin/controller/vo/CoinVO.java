package com.reward_project.coin.controller.vo;

import java.util.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinVO {
    private int id;
    // 一般用枚举定义coin的类型
    private String type;
    private String subType;
    private String icon;
    // 奖品来源
    private String source;
    private long stock;
    private String status;
    private Date createTime;
    private Date updateTime;

}
