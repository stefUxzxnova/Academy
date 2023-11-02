package com.ecommerce.system.entities;

public class CustomCredentialsHash {
    public static long hash(AccountCredentials credentials) {
        long hash = credentials.getCardHolderName().getBytes().hashCode()
                + credentials.getCardNumber().getBytes().hashCode()
                + credentials.getCvv();
        return hash;
    }
}
