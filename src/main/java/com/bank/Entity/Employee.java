package com.bank.Entity;

import java.util.Date;

public class Employee extends Person{
    private int registrationNbr;
    private Date dateOfRecrutment;
    public Employee(){}

    public Employee(String firstName, String lastName, Date birthDay, String phone, String address, int registrationNbr, Date dateOfRecrutment) {
        super(firstName, lastName, birthDay, phone, address);
        this.registrationNbr = registrationNbr;
        this.dateOfRecrutment = dateOfRecrutment;
    }

    public int getRegistrationNbr() {
        return registrationNbr;
    }

    public void setRegistrationNbr(int registrationNbr) {
        this.registrationNbr = registrationNbr;
    }

    public Date getDateOfRecrutment() {
        return dateOfRecrutment;
    }

    public void setDateOfRecrutment(Date dateOfRecrutment) {
        this.dateOfRecrutment = dateOfRecrutment;
    }
}
