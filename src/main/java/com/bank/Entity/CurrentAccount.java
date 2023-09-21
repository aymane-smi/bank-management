package com.bank.Entity;

import com.bank.Enum.AccountStatus;

import java.util.Date;

public class CurrentAccount extends Account{
    private double tax;
    public CurrentAccount(){}

    public CurrentAccount(int number, double balance, Date creationDate, AccountStatus status, double tax) {
        super(number, balance, creationDate, status);
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
