package com.reward_project.coin.enums;

public enum CoinRecordStatusEnum {
    COMPLETE("COMPLETE", "发奖记录完成"),
    INCOMPLETE("INCOMPLETE", "发奖记录未完成");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    CoinRecordStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // 无参构造是序列化必须的东西
    CoinRecordStatusEnum() {
    }
}
