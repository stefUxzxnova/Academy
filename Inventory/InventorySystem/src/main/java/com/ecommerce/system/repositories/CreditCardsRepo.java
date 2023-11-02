package com.ecommerce.system.repositories;

import com.ecommerce.system.entities.AccountCredentials;
import com.ecommerce.system.entities.CreditCardAccount;
import com.ecommerce.system.repositories.interfaces.Repository;
import com.inventory.system.utils.FileCreator;
import com.inventory.system.utils.FileReader;
import com.inventory.system.utils.FileWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreditCardsRepo implements Repository {
    private static File accountsFile;

    public CreditCardsRepo() {
        accountsFile = FileCreator.createFile("accounts.json");
        if (accountsFile.length() == 0) {
            List<CreditCardAccount> accounts = backUpAccounts();
            FileWriter.writeJsonFile(accountsFile, accounts);
        }
    }

    private List<CreditCardAccount> backUpAccounts() {
        List<CreditCardAccount> accounts = new ArrayList<>();
        CreditCardAccount acc1 = new CreditCardAccount(new AccountCredentials("1234-5678-9012-3456", 345, "Stefani Uzunova"), 5000, 700);
        CreditCardAccount acc2 = new CreditCardAccount(new AccountCredentials("6789-5678-9012-1234", 125, "Ivan Petrov"), 1000, 100);
        CreditCardAccount acc3 = new CreditCardAccount(new AccountCredentials("9986-0256-1249-3456", 775, "Filip Dimitrov"), 10000, 1000);
        CreditCardAccount acc4 = new CreditCardAccount(new AccountCredentials("9742-5678-9012-4346", 495, "Svetla Marinova"), 2000, 500);
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);
        accounts.add(acc4);
        return accounts;
    }

    @Override
    public List<CreditCardAccount> getAll() {
        return FileReader.readFile(accountsFile, CreditCardAccount.class);
    }

    @Override
    public CreditCardAccount getById(long id) {
        List<CreditCardAccount> accounts = FileReader.readFile(accountsFile, CreditCardAccount.class);
        return accounts.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    @Override
    public CreditCardAccount create(Object entity) {
        List<CreditCardAccount> accounts = FileReader.readFile(accountsFile, CreditCardAccount.class);
        accounts.add((CreditCardAccount) entity);
        if (FileWriter.writeJsonFile(accountsFile, accounts)) {
            return (CreditCardAccount) entity;
        }
        return null;
    }
    public CreditCardAccount getByCredentials(long credentialsHash) {
        List<CreditCardAccount> accounts = FileReader.readFile(accountsFile, CreditCardAccount.class);
        return accounts.stream().filter(e -> e.getCredentials() == credentialsHash).findFirst().orElse(null);
    }
}
