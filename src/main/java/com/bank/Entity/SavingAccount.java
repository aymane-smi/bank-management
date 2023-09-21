package com.bank.Entity;

import com.bank.Enum.AccountStatus;

import java.util.Date;

public class SavingAccount extends Account{
    private double overDraft;
    public SavingAccount(){}

    public SavingAccount(int number, double balance, Date creationDate, AccountStatus status, double overDraft) {
        super(number, balance, creationDate, status);
        this.overDraft = overDraft;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }
}
