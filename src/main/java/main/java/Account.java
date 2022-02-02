package main.java;

import java.math.BigDecimal;
import java.math.MathContext;

public class Account {
    private static int numberOfAccounts = 100;
    MathContext m = new MathContext(4);
    //private double balance = 0;
    private BigDecimal balance = new BigDecimal(0);
    private int accountNumber;

    Account() {
        accountNumber = numberOfAccounts++;
    }

    public static int getNextAccountNumber() {
        return ++numberOfAccounts;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean withdraw(double amount) {
        BigDecimal amounts = BigDecimal.valueOf(amount);
        BigDecimal b1 = balance.max(amounts);
        if (b1 == amounts) {
            System.out.println("Insufficient Balance");
            return false;
        } else {
            balance = balance.subtract(amounts);
            //balance = balance.round(m);
            //BigDecimal b2 = b1.round(m);
            System.out.println("New balance = " + balance);
            return true;
        }


    }

    public boolean depositt(double amount) {
        BigDecimal amounts = BigDecimal.valueOf(amount);
        if (amount < 0) {
            System.out.println("You can not deposit negative values");
            return false;
        } else {
            balance = balance.add(amounts);
            //balance = balance.round(m);
            //balance += amount;
            System.out.println("New balance = " + balance);
            return true;
        }

    }


    @Override
    public String toString() {
        return +getAccountNumber() + "\nBalance : " + balance;
    }
}
