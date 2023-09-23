package com.bank.Entity;

import com.bank.Enum.AccountStatus;

import java.util.Date;

public class CurrentAccount extends Account{
    private double overDraft;
    private String code;
    public CurrentAccount(){}

    public CurrentAccount(Account account, double overDraft, String code) {
        super(account);
        this.overDraft = overDraft;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }
}
