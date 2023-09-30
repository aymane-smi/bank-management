package com.bank.vue;

import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.DAO.OperationDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Employee;
import com.bank.Entity.Operation;
import com.bank.Enum.OperationType;
import com.bank.Service.OperationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OperationVUE {
    private OperationService operationService;
    public OperationVUE(){
        operationService = new OperationService();
    }

    public void createOperation(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("nombre employee:");
            Employee emp = new EmployeeDAOImpl().findByRegistrationNbr(sc.nextInt()).get();
            sc.nextLine();
            System.out.print("nombre account:");
            Account acc = new AccountDAOImpl().findAccountByNbr(sc.nextInt()).get();
            sc.nextLine();
            System.out.print("Date de creation(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            LocalDate inputDate;
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                inputDate = LocalDate.parse(tmp_date, formatter);
            }else{
                throw new Exception("*****   DATE INVALIDE   *****");
            }
            System.out.print("type d'operation(PAYMENT|DEPOSIT):");
            String tmp_string = sc.nextLine();
            System.out.print("montant:");
            double amount = sc.nextDouble();
            if(tmp_string.equals("WITHDRAWAL")){
                if(acc.getBalance()-amount < 0)
                    throw new Exception("*****   COMPTE AVEC MONTANT INSUFFISANT   *****");
                acc.setBalance(acc.getBalance() - amount);
            }else if(tmp_string.equals("DEPOSIT"))
                acc.setBalance(acc.getBalance() + amount);
            Operation op = new Operation(0, inputDate, amount, OperationType.valueOf(tmp_string));
            new AccountDAOImpl().update(acc);
            op.setAccount(acc);
            op.setEmployee(emp);
            operationService.createOperation(op);
        }catch(Exception e){
            System.out.print(e.getStackTrace());

            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void deleteOperation(){
        Scanner sc =  new Scanner(System.in);
        System.out.print("number d'operation:");
        Operation op = new Operation();
        op.setNumber(sc.nextInt());
        op = new OperationDAOImpl().findByNumber(op).get();
        operationService.deleteOperation(op);
    }

    public void findOperation(){
        Scanner sc =  new Scanner(System.in);
        System.out.print("number d'operation:");
        int number = sc.nextInt();
        sc.nextLine();
        Operation op = new Operation();
        op.setNumber(number);
        operationService.findOperation(op);
    }
}
