package com.reward_project.coin.exception;

public class CoinInsufficientException extends  RuntimeException{
    public CoinInsufficientException(String message) {
        super(message);
    }
}
