package com.reward_project.coin.entity;

import java.util.Date;

public class Coin {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Coin(int id, String type, String subType, String icon, String source, long stock, String status, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.subType = subType;
        this.icon = icon;
        this.source = source;
        this.stock = stock;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Coin() {
    }

    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subType='" + subType + '\'' +
                ", icon='" + icon + '\'' +
                ", source='" + source + '\'' +
                ", stock=" + stock +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
