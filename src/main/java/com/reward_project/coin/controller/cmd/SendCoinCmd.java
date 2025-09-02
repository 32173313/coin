package com.reward_project.coin.controller.cmd;

public class SendCoinCmd {
    private int userId;
    private String theme;
    // prizeType = coin code
    private String prizeType;
    private String prizeSubType;
    private int prizeAmount;
    private String outBizNo;

    public String getPrizeSubType() {
        return prizeSubType;
    }

    public void setPrizeSubType(String prizeSubType) {
        this.prizeSubType = prizeSubType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public SendCoinCmd(int userId, String theme, String prizeType, String prizeSubType, int prizeAmount, String outBizNo) {
        this.userId = userId;
        this.theme = theme;
        this.prizeType = prizeType;
        this.prizeSubType = prizeSubType;
        this.prizeAmount = prizeAmount;
        this.outBizNo = outBizNo;
    }

    public SendCoinCmd() {
    }

    @Override
    public String toString() {
        return "SendCoinCmd{" +
                "userId=" + userId +
                ", theme='" + theme + '\'' +
                ", prizeType='" + prizeType + '\'' +
                ", prizeSubType='" + prizeSubType + '\'' +
                ", prizeAmount=" + prizeAmount +
                ", outBizNo='" + outBizNo + '\'' +
                '}';
    }
}
