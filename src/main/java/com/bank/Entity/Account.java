package com.bank.Entity;

import com.bank.Enum.AccountStatus;

import java.util.Date;

public class Account {
    private int number;
    private double balance;
    private Date creationDate;
    private AccountStatus status;
    public Account(){}

    public Account(int number, double balance, Date creationDate, AccountStatus status) {
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
