package com.reward_project.coin.controller.cmd;


public class AddCoinCmd {
    // 一般用枚举定义coin的类型
    private String type;
    private String subType;
    private String icon;
    // 奖品来源
    private String source;
    private long stock;
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddCoinCmd(String type, String subType, String source, String icon, long stock, String status) {
        this.type = type;
        this.subType = subType;
        this.source = source;
        this.icon = icon;
        this.stock = stock;
        this.status = status;
    }

    public AddCoinCmd() {
    }

    @Override
    public String toString() {
        return "AddCoinCmd{" +
                "type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", icon='" + icon + '\'' +
                ", source='" + source + '\'' +
                ", stock=" + stock +
                ", status='" + status + '\'' +
                '}';
    }
}
