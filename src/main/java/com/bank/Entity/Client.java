package com.bank.Entity;

import java.time.LocalDate;
import java.util.*;

public class Client extends Person{
    private String code;

    public Map<String, List> getAccounts() {
        return accounts;
    }
    public Employee employee;

    public void setAccounts(Map<String, List> accounts) {
        this.accounts = accounts;
    }

    private Map<String, List> accounts= new HashMap<>();
    private Agency agency;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
