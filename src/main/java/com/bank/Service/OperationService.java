package com.bank.Service;

import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.DAO.OperationDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Employee;
import com.bank.Entity.Operation;
import com.bank.Enum.OperationType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class OperationService {
    private OperationDAOImpl OperationDao;
    public OperationService(){
        OperationDao = new OperationDAOImpl();
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
            System.out.print("type d'operation(PAYMENT|WITHDRAWAL):");
            String tmp_string = sc.nextLine();
            System.out.print("montant:");
            double amount = sc.nextDouble();
            if(tmp_string.equals("WITHDRAWAL")){
                if(acc.getBalance()-amount < 0)
                    throw new Exception("*****   COMPTE AVEC MONTANT INSUFFISANT   *****");
                acc.setBalance(acc.getBalance() - amount);
            }else if(tmp_string.equals("PAYMENT"))
                acc.setBalance(acc.getBalance() + amount);
            Operation op = new Operation(0, inputDate, amount, OperationType.valueOf(tmp_string));
            new AccountDAOImpl().update(acc);
            op.setAccount(acc);
            op.setEmployee(emp);
            Optional<Operation> optionalOperation = OperationDao.create(op);
            optionalOperation.ifPresent((val)->{
                System.out.print("*****   OPERATION FAITE AVEC SUCCESS   *****");
            });
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
        op = OperationDao.findByNumber(op).get();
        if(op.getType() == OperationType.PAYMENT){
            Account acc = new AccountDAOImpl().findAccountByNbr(op.getAccount().getNumber()).get();
            acc.setBalance(acc.getBalance() - op.getAmount());
            new AccountDAOImpl().update(acc);
            if(OperationDao.delete(op) == 1)
                System.out.println("*****   OPERATION SUPPRIMER AVEC SUCCESS   *****");
        }else if(op.getType() == OperationType.WITHDRAWAL){
            Account acc = new AccountDAOImpl().findAccountByNbr(op.getAccount().getNumber()).get();
            acc.setBalance(acc.getBalance() + op.getAmount());
            new AccountDAOImpl().update(acc);
            if(OperationDao.delete(op) == 1)
                System.out.println("*****   OPERATION SUPPRIMER AVEC SUCCESS   *****");
        }
    }
    public void findOperation(){
        Scanner sc =  new Scanner(System.in);
        System.out.print("number d'operation:");
        int number = sc.nextInt();
        sc.nextLine();
        Operation op = new Operation();
        op.setNumber(number);
        op = OperationDao.findByNumber(op).get();
        System.out.print(String.format("*****   OPERATION_NUMBER[%d] MONTANT[%f] TYPE[%s] NOMBRE_COMPTE[%d] EMPLOYEE_REGISTRATION[%d]", op.getNumber(), op.getAmount(), op.getType().name(), op.getAccount().getNumber(), op.getEmployee().getRegistrationNbr()));
    }
}
