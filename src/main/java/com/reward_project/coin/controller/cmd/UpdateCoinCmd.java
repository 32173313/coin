package com.reward_project.coin.controller.cmd;

public class UpdateCoinCmd {
    private String icon;
    private Long stock;
    private String status;
    private String type;
    private String subType;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public UpdateCoinCmd(String icon, Long stock, String status, String type, String subType) {
        this.icon = icon;
        this.stock = stock;
        this.status = status;
        this.type = type;
        this.subType = subType;
    }

    public UpdateCoinCmd() {
    }

    @Override
    public String toString() {
        return "UpdateCoinCmd{" +
                "icon='" + icon + '\'' +
                ", stock=" + stock +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                '}';
    }
}
