package com.bank.vue;

import com.bank.Entity.Employee;
import com.bank.Service.EmployeeService;

import java.util.Scanner;

public class EmployeeVUE {
    private EmployeeService employeeService;
    public EmployeeVUE(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public void changeEmployeeAgence(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("employee matricule");
            Employee emp = employeeService.findEmployee(sc.nextInt());
            String tmp;
            System.out.print("agence id");
            tmp = sc.nextLine();
            if(!tmp.isEmpty())
                employeeService.changeAgency(emp, tmp);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
