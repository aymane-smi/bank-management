package com.bank.Entity;

import java.time.LocalDate;
import java.util.Date;

public class Employee extends Person{
    private int registrationNbr;
    private LocalDate dateOfRecrutment;
    public Employee(){}

    public Employee(String firstName, String lastName, LocalDate birthDay, String phone, String address, int registrationNbr, LocalDate dateOfRecrutment) {
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

    public LocalDate getDateOfRecrutment() {
        return dateOfRecrutment;
    }

    public void setDateOfRecrutment(LocalDate dateOfRecrutment) {
        this.dateOfRecrutment = dateOfRecrutment;
    }
}
