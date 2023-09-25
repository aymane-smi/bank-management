package com.bank.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends Person{
    private String code;
    private List<Account> accounts= new ArrayList<>();

    public Client(){}
    public Client(String code, String firstName, String lastName, LocalDate birthDay, String phone, String address) {
        super(firstName, lastName, birthDay, phone, address);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
