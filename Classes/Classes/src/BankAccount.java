import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BankAccount {

    private static List<BankAccount> accounts = new ArrayList<>();

    private static int nextId = 1; // Static field to track the next available account ID

    private int Id;

    private double balance;

    private static double interestRate = 0.02;

    public BankAccount(){
        this.Id = nextId ++;
        this.balance = 0.0;
        accounts.add(this);
    }

    public BankAccount(double balance){
        this.Id = nextId ++;
        this.balance = balance;
    }

    public static void setInterestRate(double value){
        interestRate = value;
    }
    public double getInterest(int years){
        return interestRate * this.balance * years;
    }
    public void deposit(double amount){
        this.balance += amount;
    }

    public static BankAccount getBankAccount(int id){
        for (var account : accounts) {
            if (account.Id == id) {
                return account;
            }
        }
        return null;
    }

 }
