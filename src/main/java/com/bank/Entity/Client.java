package com.bank.Entity;

import java.util.Date;

public class Client extends Person{
    private String code;

    public Client(){}
    public Client(String firstName, String lastName, Date birthDay, String phone, String address) {
        super(firstName, lastName, birthDay, phone, address);
    }

    public String getCode() {
        return code;
    }
}
