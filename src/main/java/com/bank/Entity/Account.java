package com.bank.Entity;

import com.bank.Enum.AccountStatus;

import java.time.LocalDate;

public class Account {
    private int number;
    private double balance;
    private LocalDate creationDate;
    private AccountStatus status;
    private Client client;
    public Account(){}

    public Account(int number, double balance, LocalDate creationDate, AccountStatus status, Client client) {
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
        this.status = status;
        this.client = client;
    }

    public Account(Account account) {
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.creationDate = account.getCreationDate();
        this.status = account.getStatus();
        this.client = account.getClient();
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
