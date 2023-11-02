package com.ecommerce.system.entities;

public class AccountCredentials {
    private String cardNumber;
    private int cvv;
    private String cardHolderName;

    public AccountCredentials(String cardNumber, int cvv, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
    }

    public AccountCredentials() {}

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
