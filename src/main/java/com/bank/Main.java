package com.bank;

import com.bank.Service.AccountService;
import com.bank.Service.ClientService;
import com.bank.Service.EmployeeService;

public class Main {
    public static void main(String[] args){
        new AccountService().getAllAccount();
    }
}
