package com.bank.Entity;

import java.time.LocalDate;
import java.util.Date;

public class Client extends Person{
    private String code;

    public Client(){}
    public Client(String firstName, String lastName, LocalDate birthDay, String phone, String address) {
        super(firstName, lastName, birthDay, phone, address);
    }

    public String getCode() {
        return code;
    }
}
