package com.bank.Entity;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
    private Agency agency;
    private List<Agency> agencyHistory;
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

    public List<Account> getCreatedAccounts() {
        return createdAccounts;
    }

    public void setCreatedAccounts(List<Account> createdAccounts) {
        this.createdAccounts = createdAccounts;
    }

    public List<Client> getCreatedClient() {
        return createdClient;
    }

    public void setCreatedClient(List<Client> createdClient) {
        this.createdClient = createdClient;
    }

    public List<Operation> getCreatedOperations() {
        return createdOperations;
    }

    public void setCreatedOperations(List<Operation> createdOperations) {
        this.createdOperations = createdOperations;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<Agency> getListAgencies() {
        return agencyHistory;
    }

    public void setListAgencies(List<Agency> agencyHistory) {
        this.agencyHistory = agencyHistory;
    }
}
