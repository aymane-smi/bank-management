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

    public void createOperation(Operation op){
        Optional<Operation> optionalOperation = OperationDao.create(op);
        optionalOperation.ifPresent((val)->{
            System.out.print("*****   OPERATION FAITE AVEC SUCCESS   *****");
        });
    }

    public void deleteOperation(Operation op){
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
    public void findOperation(Operation op){
        op = OperationDao.findByNumber(op).get();
        System.out.print(String.format("*****   OPERATION_NUMBER[%d] MONTANT[%f] TYPE[%s] NOMBRE_COMPTE[%d] EMPLOYEE_REGISTRATION[%d]", op.getNumber(), op.getAmount(), op.getType().name(), op.getAccount().getNumber(), op.getEmployee().getRegistrationNbr()));
    }
}
