package com.reward_project.coin.exception;

public class CoinNotExistException extends RuntimeException{
    public CoinNotExistException(String message) {
        super(message);
    }
}
