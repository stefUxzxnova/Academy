package com.ecommerce.system.processors;

import com.ecommerce.system.entities.AccountCredentials;
import com.ecommerce.system.entities.CreditCardAccount;
import com.ecommerce.system.entities.CustomCredentialsHash;
import com.ecommerce.system.entities.interfaces.CreditCartPayment;
import com.ecommerce.system.entities.interfaces.PayPalPayment;
import com.ecommerce.system.repositories.CreditCardsRepo;

public class PaymentProcessor implements CreditCartPayment, PayPalPayment {

    private CreditCardsRepo creditCardsRepo;

    public PaymentProcessor() {
        this.creditCardsRepo = new CreditCardsRepo();
    }

    @Override
    public boolean processCreditCardPayment(String creditCardNumber, int csv, String cardHolderName, double payment) {
        CreditCardAccount account = authenticateAccount(new AccountCredentials(creditCardNumber, csv, cardHolderName));
        if (account != null) {
            if (!checkBalance(payment, account)) return false;
        }
        return true;
    }
    private CreditCardAccount authenticateAccount(AccountCredentials accountCredentials) {
        long credentialsHash = CustomCredentialsHash.hash(accountCredentials);
        CreditCardAccount account = creditCardsRepo.getByCredentials(credentialsHash);
        if (account != null) {
            return account;
        }
        return null;
    }

    private static boolean checkBalance(double payment, CreditCardAccount account) {
        if (account.getBalance() < payment) {
            return false;
        }
        if (account.getDailyLimit() < payment) {
            return false;
        }
        account.setBalance(account.getBalance() - payment);
        return true;
    }

    @Override
    public boolean processPayPalPayment(String paypalUsername) {
        // TODO: 2.11.2023 г.
        return false;
    }


}
