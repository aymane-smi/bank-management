package com.bank.vue;

import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Employee;
import com.bank.Entity.Payment;
import com.bank.Service.PaymentService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class PaymentVUE {
    private PaymentService paymentService;
    private AccountDAOImpl accountDao;
    private EmployeeDAOImpl employeeDao;
    private Payment payment;
    private Scanner sc;
    public PaymentVUE(PaymentService paymentService, Scanner sc, AccountDAOImpl accountDao, EmployeeDAOImpl employeeDao, Payment payment){
        this.paymentService = paymentService;
        this.sc = sc;
        this.accountDao = accountDao;
        this.employeeDao = employeeDao;
        this.payment = payment;
    }

    public void createPayment(){
        try{
            System.out.print("numero de compte donneur:");
            Account from = accountDao.findAccountByNbr(sc.nextInt()).get();
            System.out.print("numero de compte beneficiaire:");
            Account to = accountDao.findAccountByNbr(sc.nextInt()).get();
            System.out.print("matricule employee:");
            Employee emp = employeeDao.findByRegistrationNbr(sc.nextInt()).get();
            System.out.print("montant");
            double balance = sc.nextDouble();
            payment.setTo(to);
            payment.setFrom(from);
            payment.setEmployee(emp);
            payment.setBalance(balance);
            payment.setTransaction_time(LocalDateTime.now());
            if(paymentService.create(payment) != null)
                System.out.println("*****   VIRMENT FAIT AVEC SUCCESS   *****");
        }catch (Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
