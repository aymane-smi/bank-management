package com.bank.Entity;

import com.bank.Enum.AccountStatus;

import java.util.Date;

public class SavingAccount extends Account{
    private String code;
    private double tax;
    public SavingAccount(){}

    public SavingAccount(Account account, double tax, String code) {
        super(account);
        this.tax = tax;
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
