package com.ecommerce.system.entities.interfaces;

public interface CreditCartPayment {
    boolean processCreditCardPayment(String creditCardNumber, int csv, String cardHolderName, double payment);
}
