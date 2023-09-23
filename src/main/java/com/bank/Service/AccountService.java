package com.bank.Service;

import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.ClientDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Client;
import com.bank.Entity.SavingAccount;
import com.bank.Enum.AccountStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class AccountService {
    AccountDAOImpl AccountDao;

    public AccountService(){
        AccountDao = new AccountDAOImpl();
    }

    public void addServingAccount(){
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        System.out.print("solde:");
        account.setBalance(sc.nextDouble());
        System.out.println("date de création:");
        String tmp_date = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
        account.setCreationDate(inputDate);
        System.out.println("status(ACTIVE(0)|SUSPEND(1)|BANNED(2))");
        switch (sc.nextInt()){
            case 0:
                account.setStatus(AccountStatus.ACTIVE);
                break;
            case 1:
                account.setStatus(AccountStatus.SUSPEND);
                break;
            case 2:
                account.setStatus(AccountStatus.BANNED);
                break;
            default:
                System.out.println("*****   STATUS NOT VALIDE   *****");
                return;
        }
        System.out.println("code du client:");
        Optional<Client> optionalClt = new ClientDAOImpl().findByCode(sc.nextLine());
        if(optionalClt.isPresent())
            optionalClt.ifPresent((clt)->{
                System.out.println("code:");
                String code = sc.nextLine();
                System.out.println("tax:");
                double tax = sc.nextDouble();
                if(tax > 100 || tax < 0){
                    System.out.println("*****   TAX DOIT ETRE ENTRE 0 EST 100   *****");
                    return;
                }
                SavingAccount savingAccount = new SavingAccount(account, tax, code);
                Optional<SavingAccount> optinalSaving = AccountDao.createSavingAccount(savingAccount);
                optinalSaving.ifPresent((saving)->{
                    System.out.println("*****   NOUVEAU COMPTE D'EMPRANGE ET CRÉER AVEC SUCCESS   *****");
                });
            });
        else{
            System.out.println("*****   SVP CRÉER UN NOUVEAU CLIENT POUR ASSIGNER UN COMPTE   *****");
            return;
        }

    }
}
