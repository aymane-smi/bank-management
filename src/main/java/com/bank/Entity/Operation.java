package com.bank.Entity;

import com.bank.Enum.OperationType;

import java.time.LocalDate;
import java.util.Date;

public class Operation {
    private int number;
    private LocalDate creationDate;
    private double amount;
    private OperationType type;
    private Employee employee;
    private Account account;
    public Operation(){}

    public Operation(int number, LocalDate creationDate, double amount, OperationType type) {
        this.number = number;
        this.creationDate = creationDate;
        this.amount = amount;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
