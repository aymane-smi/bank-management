package com.bank;

import com.bank.Service.AccountService;
import com.bank.Service.ClientService;
import com.bank.Service.EmployeeService;
import com.bank.Service.OperationService;

public class Main {
    public static void main(String[] args){
        new OperationService().createOperation();
    }
}
