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
    AccountDAOImpl AccountDao;

    public AccountService(){
        AccountDao = new AccountDAOImpl();
    }

    public void addSavingWithAccount(){
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
        if(optionalClt.isPresent())
            optionalClt.ifPresent((clt)->{
                account.setClient(clt);
                System.out.println("code:");
                String code = sc.nextLine();
                System.out.println("tax:");
                String tmp_tax = sc.nextLine();
                if(!tmp_tax.isEmpty()){
                    double tax = Double.parseDouble(tmp_tax);
                    if(tax > 100 || tax < 0){
                        System.out.println("*****   TAX DOIT ETRE ENTRE 0 EST 100   *****");
                        return;
                    }
                    SavingAccount savingAccount = new SavingAccount(AccountDao.createAccount(account).get(), tax, code);
                    System.out.println(savingAccount.getCode());
                    Optional<SavingAccount> optinalSaving = AccountDao.createSavingAccount(savingAccount);
                    optinalSaving.ifPresent((saving)->{
                        System.out.println("*****   NOUVEAU COMPTE D'EMPRANGE ET CRÉER AVEC SUCCESS   *****");
                    });
                }
            });
        else{
            System.out.println("*****   SVP CRÉER UN NOUVEAU CLIENT POUR ASSIGNER UN COMPTE   *****");
            return;
        }

    }

    public void addCurrentWithAccount(){
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
        if(optionalClt.isPresent())
            optionalClt.ifPresent((clt)->{
                account.setClient(clt);
                System.out.println("code:");
                String code = sc.nextLine();
                System.out.println("decouvert:");
                String tmp_in = sc.nextLine();
                if(!tmp_in.isEmpty()){
                    double overDraft = Double.parseDouble(tmp_in);
                    if(overDraft < 0){
                        System.out.println("*****   LA DECOUVERT DOIT ETRE SUPERIEUR DE 0   *****");
                        return;
                    }
                    CurrentAccount currentAccount = new CurrentAccount(AccountDao.createAccount(account).get(), overDraft, code);
                    Optional<CurrentAccount> optinalCurrent = AccountDao.createCurrentAccount(currentAccount);
                    optinalCurrent.ifPresent((current)->{
                        System.out.println("*****   NOUVEAU COMPTE CURRENT ET CRÉER AVEC SUCCESS   *****");
                    });
                }

            });
        else{
            System.out.println("*****   SVP CRÉER UN NOUVEAU CLIENT POUR ASSIGNER UN COMPTE   *****");
            return;
        }

    }

    public void addSavingWithoutAccount(){
        Scanner sc = new Scanner(System.in);
        Account account = new Account();
        System.out.print("numero du compte:");
        String tmp_input = sc.nextLine();
        if(!tmp_input.isEmpty()){
            int tmp = Integer.parseInt(tmp_input);
            account = AccountDao.findAccountByNbr(tmp).get();
        }
        System.out.println("code:");
        String code = sc.nextLine();
        System.out.println("tax:");
        tmp_input = sc.nextLine();
        if(!tmp_input.isEmpty()){
            double tax = Double.parseDouble(tmp_input);
            if(tax > 100 || tax < 0){
                System.out.println("*****   TAX DOIT ETRE ENTRE 0 EST 100   *****");
                return;
            }
            SavingAccount savingAccount = new SavingAccount(account, tax, code);
            Optional<SavingAccount> optinalSaving = AccountDao.createSavingAccount(savingAccount);
            if(optinalSaving.isPresent())
                optinalSaving.ifPresent((saving)->{
                    System.out.println("*****   NOUVEAU COMPTE D'EMPRANGE ET CRÉER AVEC SUCCESS   *****");
                });
            else{
                System.out.println("*****   ERREUR LORS DE LA CRÉATION DU COMPTE   *****");
                return;
            }
        }

    }
    public void addCurrentWithoutAccount(){
        Scanner sc = new Scanner(System.in);
        Account account = new Account();
        System.out.print("numero du compte:");
        String tmp_input = sc.nextLine();
        if(!tmp_input.isEmpty()){
            int tmp = Integer.parseInt(tmp_input);
            account = AccountDao.findAccountByNbr(tmp).get();
        }
        System.out.println("code:");
        String code = sc.nextLine();
        System.out.println("decouvert:");
        tmp_input = sc.nextLine();
        if(!tmp_input.isEmpty()){
            double overDraft = Double.parseDouble(tmp_input);
            if(overDraft < 0){
                System.out.println("*****   LA DECOUVERT DOIT ETRE SUPERIEUR DE 0   *****");
                return;
            }
            CurrentAccount currentAccount = new CurrentAccount(account, overDraft, code);
            Optional<CurrentAccount> optinalCurrent = AccountDao.createCurrentAccount(currentAccount);
            if(optinalCurrent.isPresent())
                optinalCurrent.ifPresent((saving)->{
                    System.out.println("*****   NOUVEAU COMPTE CURRENT ET CRÉER AVEC SUCCESS   *****");
                });
            else{
                System.out.println("*****   ERREUR LORS DE LA CRÉATION DU COMPTE   *****");
                return;
            }
        }
    }
    public void deleteAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("numero du compte:");
        if(AccountDao.delete(sc.nextInt()) == 1)
            System.out.println("*****   COMPTE SUPPRIMER AVEC SUCCESS   *****");
        else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void deleteSavingAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("code du compte:");
        if(AccountDao.deleteSaving(sc.nextLine()) == 1)
            System.out.println("*****   COMPTE D'EPRANGE SUPPRIMER AVEC SUCCESS   *****");
        else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void deleteCurrentAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("code du compte:");
        if(AccountDao.deleteCurrent(sc.nextLine()) == 1)
            System.out.println("*****   COMPTE CURRENT SUPPRIMER AVEC SUCCESS   *****");
        else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void updateStatusAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.print("code du compte:");
        Account account = AccountDao.findAccountByNbr(sc.nextInt()).get();
        sc.nextLine();
        System.out.print("status(ACTIVE|SUSPEND|BANNED)");
        if(AccountDao.updateStatus(account, AccountStatus.valueOf(sc.nextLine())).isPresent())
            System.out.println("*****   STATUS DU COMPTE CHANGEE   *****");
        else
            System.out.println("*****   COMPTE INTROUVABLE   *****");
    }

    public void getAllAccount(){
        System.out.println("*****   LISTE DES COMPTES CURRENTS   *****");
        AccountDao.findAllCurrent().ifPresent((list)->{
            for(CurrentAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
        });
        System.out.println("*****   LISTE DES COMPTES D'EMPRANGES   *****");
        AccountDao.findAllSaving().ifPresent((list)->{
            for(SavingAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
        });
    }

    public void updateSavingAccount(){
        try{
            Scanner sc = new Scanner(System.in);
            SavingAccount acc;
            System.out.print("code account:");
            acc = AccountDao.findSaving(sc.nextLine()).get();
            System.out.print("balance:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setBalance(Double.parseDouble(tmp_str));
            System.out.print("status(ACTIVE|SUSPEND|BANNED):");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setStatus(AccountStatus.valueOf(tmp_str));
            System.out.print("Date de creation(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                acc.setCreationDate(inputDate);
            }
            System.out.print("client code:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setClient(new ClientDAOImpl().findByCode(tmp_str).get());
            System.out.print("tax:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setTax(Double.parseDouble(tmp_str));
            AccountDao.update(acc).ifPresent((listEmp)->{
                System.out.println("*****   COMPTE MODIFIER AVEC SUCCESS   *****");
            });
            sc.close();
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
    public void updateCurrentAccount(){
        try{
            Scanner sc = new Scanner(System.in);
            CurrentAccount acc;
            System.out.print("code account:");
            acc = AccountDao.findCurrent(sc.nextLine()).get();
            System.out.print("balance:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setBalance(Double.parseDouble(tmp_str));
            System.out.print("status(ACTIVE|SUSPEND|BANNED):");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setStatus(AccountStatus.valueOf(tmp_str));
            System.out.print("Date de creation(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                acc.setCreationDate(inputDate);
            }
            System.out.print("client code:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setClient(new ClientDAOImpl().findByCode(tmp_str).get());
            System.out.print("decouvert:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                acc.setOverDraft(Double.parseDouble(tmp_str));
            AccountDao.update(acc).ifPresent((listEmp)->{
                System.out.println("*****   COMPTE MODIFIER AVEC SUCCESS   *****");
            });
            sc.close();
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
    public void getAccountStatus(){
        Scanner sc = new Scanner(System.in);
        System.out.println("status:");
        String status = sc.nextLine();
        System.out.println("*****   LISTE DES COMPTES CURRENTS   *****");
        AccountDao.findCurrentStatus(AccountStatus.valueOf(status)).ifPresent((list)->{
            for(CurrentAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
        });
        System.out.println("*****   LISTE DES COMPTES D'EMPRANGES   *****");
        AccountDao.findSavingStatus(AccountStatus.valueOf(status)).ifPresent((list)->{
            for(SavingAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
        });
    }

    public void getAccountByDate(){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("date de creation:");
        LocalDate date = LocalDate.parse(sc.next(), formatter);
        System.out.println("*****   LISTE DES COMPTES CURRENTS   *****");
        AccountDao.findCurrentByDate(date).ifPresent((list)->{
            for(CurrentAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
        });
        System.out.println("*****   LISTE DES COMPTES D'EMPRANGES   *****");
        AccountDao.findSavingByDate(date).ifPresent((list)->{
            for(SavingAccount tmp:list)
                System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
        });
    }

}
