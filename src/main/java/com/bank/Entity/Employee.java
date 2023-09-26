package com.bank.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee extends Person{
    private int registrationNbr;
    private LocalDate dateOfRecrutment;
    private List<Account> createdAccounts = new ArrayList<>();
    private List<Client> createdClient = new ArrayList<>();
    private List<Operation> createdOperations = new ArrayList<>();
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
