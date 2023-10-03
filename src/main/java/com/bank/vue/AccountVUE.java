package com.bank.vue;

import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.AgencyDAOImpl;
import com.bank.DAO.ClientDAOImpl;
import com.bank.Entity.*;
import com.bank.Enum.AccountStatus;
import com.bank.Service.AccountService;
import com.bank.Service.AgencyService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class AccountVUE {
    private AccountService accountService;
    private AgencyDAOImpl agencyDAOImpl;
    public AccountVUE(AgencyDAOImpl agencyDAOImpl, AccountService accountService){
        this.accountService = accountService;
        this.agencyDAOImpl = agencyDAOImpl;
    }

    public void addSaving(){
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        System.out.print("solde:");
        String tmp_input = sc.nextLine();
        if(!tmp_input.isEmpty())
            account.setBalance(Double.parseDouble(tmp_input));
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
        sc.nextLine();
        System.out.println("code du client:");
        Optional<Client> optionalClt = new ClientDAOImpl().findByCode(sc.nextLine());
        System.out.print("code d'agence:");
        Agency agency = agencyDAOImpl.findByCode(sc.nextLine()).get();
        account.setAgency(agency);
        if(optionalClt.isPresent()){
            System.out.println("code:");
            String code = sc.nextLine();
            System.out.println("tax:");
            String tmp_tax = sc.nextLine();
            if(!tmp_tax.isEmpty())
                accountService.addSavingWithAccount(account, optionalClt, code, Double.parseDouble(tmp_tax));
        }
    }

    public void addCurrent(){
        Account account = new Account();
        Scanner sc = new Scanner(System.in);
        System.out.print("solde:");
        String tmp_input = sc.nextLine();
        if(!tmp_input.isEmpty())
            account.setBalance(Double.parseDouble(tmp_input));
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
        sc.nextLine();
        System.out.println("code du client:");
        Optional<Client> optionalClt = new ClientDAOImpl().findByCode(sc.nextLine());
        if(optionalClt.isPresent()){
            System.out.println("code:");
            String code = sc.nextLine();
            System.out.println("decouvert:");
            String tmp_overDraft = sc.nextLine();
            if(!tmp_overDraft.isEmpty())
                accountService.addCurrentWithAccount(account, optionalClt, code, Double.parseDouble(tmp_overDraft));
        }
    }

    public void deleteSaving(){
        Scanner sc = new Scanner(System.in);
        System.out.println("code du compte:");
        String code = sc.nextLine();
        accountService.deleteSavingAccount(code);
    }

    public void deleteCurrent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("code du compte:");
        String code = sc.nextLine();
        accountService.deleteCurrentAccount(code);
    }

    public void updateAccountStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.print("matricule du compte:");
        int code = sc.nextInt();
        sc.nextLine();
        System.out.print("status(ACTIVE|SUSPEND|BANNED)");
        String status = String.valueOf(AccountStatus.valueOf(sc.nextLine()));
        accountService.updateStatusAccount(code, status);
    }

    public void getAllAccount(){
        accountService.getAllAccount();
    }

    public void updateSavingAccount(){
        try {
            Scanner sc = new Scanner(System.in);
            SavingAccount acc;
            System.out.print("code account:");
            acc = new AccountDAOImpl().findSaving(sc.nextLine()).get();
            System.out.print("balance:");
            String tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setBalance(Double.parseDouble(tmp_str));
            System.out.print("status(ACTIVE|SUSPEND|BANNED):");
            tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setStatus(AccountStatus.valueOf(tmp_str));
            System.out.print("Date de creation(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if (!tmp_date.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                acc.setCreationDate(inputDate);
            }
            System.out.print("client code:");
            tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setClient(new ClientDAOImpl().findByCode(tmp_str).get());
            System.out.print("tax:");
            tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setTax(Double.parseDouble(tmp_str));
            accountService.updateSavingAccount(acc);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void updateCurrentAccount(){
        try {
            Scanner sc = new Scanner(System.in);
            CurrentAccount acc;
            System.out.print("code account:");
            acc = new AccountDAOImpl().findCurrent(sc.nextLine()).get();
            System.out.print("balance:");
            String tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setBalance(Double.parseDouble(tmp_str));
            System.out.print("status(ACTIVE|SUSPEND|BANNED):");
            tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setStatus(AccountStatus.valueOf(tmp_str));
            System.out.print("Date de creation(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if (!tmp_date.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                acc.setCreationDate(inputDate);
            }
            System.out.print("client code:");
            tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setClient(new ClientDAOImpl().findByCode(tmp_str).get());
            System.out.print("decouvert:");
            tmp_str = sc.nextLine();
            if (!tmp_str.isEmpty())
                acc.setOverDraft(Double.parseDouble(tmp_str));
            accountService.updateCurrentAccount(acc);
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void getAccountStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("status:");
        String status = sc.nextLine();
        accountService.getAccountStatus(status);
    }

    public void getAccountByDate(){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("date de creation:");
        LocalDate date = LocalDate.parse(sc.next(), formatter);
        accountService.getAccountByDate(date);
    }
}
