package com.bank.Service;

import com.bank.DAO.AccountDAOImpl;
import com.bank.DAO.ClientDAOImpl;
import com.bank.Entity.Account;
import com.bank.Entity.Client;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;
import com.bank.Enum.AccountStatus;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class AccountService {
    private AccountDAOImpl accountDao;

    public AccountService(){
        accountDao = new AccountDAOImpl();
    }

    public void addSavingWithAccount(Account account, Optional<Client> optionalClt, String code, double tax){
        if(optionalClt.isPresent())
            optionalClt.ifPresent((clt)->{
                account.setClient(clt);
                if(tax > 100 || tax < 0){
                    System.out.println("*****   TAX DOIT ETRE ENTRE 0 EST 100   *****");
                        return;
                    }
                SavingAccount savingAccount = new SavingAccount(accountDao.createAccount(account).get(), tax, code);
                System.out.println(savingAccount.getCode());
                Optional<SavingAccount> optinalSaving = accountDao.createSavingAccount(savingAccount);
                optinalSaving.ifPresent((saving)->{
                    System.out.println("*****   NOUVEAU COMPTE D'EMPRANGE ET CRÉER AVEC SUCCESS   *****");
                });
            });
        else{
            System.out.println("*****   SVP CRÉER UN NOUVEAU CLIENT POUR ASSIGNER UN COMPTE   *****");
            return;
        }

    }

    public void addCurrentWithAccount(Account account, Optional<Client> optionalClt, String code, double overDraft){
        if(optionalClt.isPresent())
            optionalClt.ifPresent((clt)->{
                account.setClient(clt);
                if(overDraft < 0){
                    System.out.println("*****   LA DECOUVERT DOIT ETRE SUPERIEUR DE 0   *****");
                        return;
                }
                CurrentAccount currentAccount = new CurrentAccount(accountDao.createAccount(account).get(), overDraft, code);
                Optional<CurrentAccount> optinalCurrent = accountDao.createCurrentAccount(currentAccount);
                optinalCurrent.ifPresent((current)->{
                    System.out.println("*****   NOUVEAU COMPTE CURRENT ET CRÉER AVEC SUCCESS   *****");
                });
            });
        else{
            System.out.println("*****   SVP CRÉER UN NOUVEAU CLIENT POUR ASSIGNER UN COMPTE   *****");
            return;
        }

    }

    public void deleteSavingAccount(String code){
        int account_nbr = accountDao.findSaving(code).get().getNumber();
        if(accountDao.deleteSaving(code) == 1){
            accountDao.delete(account_nbr);
            System.out.println("*****   COMPTE D'EPRANGE SUPPRIMER AVEC SUCCESS   *****");
        }else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void deleteCurrentAccount(String code){
        int account_nbr = accountDao.findCurrent(code).get().getNumber();
        if(accountDao.deleteCurrent(code) == 1){
            accountDao.delete(account_nbr);
            System.out.println("*****   COMPTE CURRENT SUPPRIMER AVEC SUCCESS   *****");
        }else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void updateStatusAccount(int code, String status){
        Account account = accountDao.findAccountByNbr(code).get();
        if(accountDao.updateStatus(account, AccountStatus.valueOf(status)).isPresent())
            System.out.println("*****   STATUS DU COMPTE CHANGEE   *****");
        else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void getAllAccount(){
        System.out.println("*****   LISTE DES COMPTES CURRENTS   *****");
        accountDao.findAllCurrent().ifPresent((list)->{
            for(CurrentAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
        });
        System.out.println("*****   LISTE DES COMPTES D'EMPRANGES   *****");
        accountDao.findAllSaving().ifPresent((list)->{
            for(SavingAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
        });
    }

    public void updateSavingAccount(Account acc){
        accountDao.update(acc).ifPresent((listEmp)->{
            System.out.println("*****   COMPTE MODIFIER AVEC SUCCESS   *****");
        });
    }
    public void updateCurrentAccount(Account acc){
            accountDao.update(acc).ifPresent((listEmp)->{
                System.out.println("*****   COMPTE MODIFIER AVEC SUCCESS   *****");
            });
    }
    public void getAccountStatus(String status){
        System.out.println("*****   LISTE DES COMPTES CURRENTS   *****");
        accountDao.findCurrentStatus(AccountStatus.valueOf(status)).ifPresent((list)->{
            for(CurrentAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
        });
        System.out.println("*****   LISTE DES COMPTES D'EMPRANGES   *****");
        accountDao.findSavingStatus(AccountStatus.valueOf(status)).ifPresent((list)->{
            for(SavingAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
        });
    }

    public void getAccountByDate(LocalDate date){
        System.out.println("*****   LISTE DES COMPTES CURRENTS   *****");
        accountDao.findCurrentByDate(date).ifPresent((list)->{
            for(CurrentAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
        });
        System.out.println("*****   LISTE DES COMPTES D'EMPRANGES   *****");
        accountDao.findSavingByDate(date).ifPresent((list)->{
            for(SavingAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
        });
    }

}
