package com.bank.Entity;

import com.bank.Enum.OperationType;

import java.util.Date;

public class Operation {
    private int number;
    private Date creationDate;
    private double amount;
    private OperationType type;
    public Operation(){}

    public Operation(int number, Date creationDate, double amount, OperationType type) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
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
}
