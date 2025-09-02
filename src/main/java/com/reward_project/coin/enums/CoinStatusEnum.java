package com.reward_project.coin.enums;

public enum CoinStatusEnum {
    ACTIVE("ACTIVE", "上架"),
    DEACTIVE("DEACTIVE", "下架");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    CoinStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    CoinStatusEnum() {
    }
}


