package com.ecommerce.system.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCardAccount {

    @JsonProperty
    private static long uid = 1;
    @JsonProperty
    private long id;
    @JsonProperty
    private double balance;
    @JsonProperty
    private double dailyLimit;
    @JsonProperty
    private long credentials;

    public CreditCardAccount(AccountCredentials credentials, double balance, double dailyLimit) {
        this.id = uid++;
        this.credentials = CustomCredentialsHash.hash(credentials);
        this.balance = balance;
        this.dailyLimit = dailyLimit;
    }
    public CreditCardAccount(){}

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public long getCredentials() {
        return credentials;
    }

    @Override
    public String toString() {
        return "CreditCardAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", dailyLimit=" + dailyLimit +
                '}';
    }
}
