package com.bank.Service;

import com.bank.DAO.ClientDAOImpl;
import com.bank.DAO.EmployeeDAOImpl;
import com.bank.Entity.Client;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.Employee;
import com.bank.Entity.SavingAccount;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {
    private ClientDAOImpl ClientDao;
    public ClientService(){
        ClientDao = new ClientDAOImpl();
    }

    public void addClient(){
        try{
            Scanner sc = new Scanner(System.in);
            Client clt = new Client();
            System.out.print("code:");
            clt.setCode(sc.nextLine());
            System.out.print("nom:");
            clt.setFirstName(sc.nextLine());
            System.out.print("prenom:");
            clt.setLastName(sc.nextLine());
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
            if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                clt.setBirthDay(inputDate);
            else
                throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR UN CLIENT   *****");
            System.out.print("telephone:");
            clt.setPhone(sc.nextLine());
            System.out.print("adresse:");
            clt.setAddress(sc.nextLine());
            System.out.print("employee code:");
            String str = sc.nextLine();
            clt.setEmployee(new EmployeeDAOImpl().findByRegistrationNbr(Integer.parseInt(str)).get());
            Optional<Client> optionalClt = ClientDao.create(clt);
            optionalClt.ifPresent(val->System.out.println(String.format("*****   AJOUT D'UN CLIENT AVEC CODE[%s]   *****", val.getCode())));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
    public void deleteClient(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("code:");
            int result = ClientDao.delete(sc.nextLine());
            System.out.println(String.format("*****   NOMBRE DES ELEMENTS SUPPRIMEES EST:%d   *****", result));
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findAllClients(){
        try{
            Optional<List<Client>> listClt= ClientDao.findAll();
            listClt.ifPresent((list)->{
                for(Client clt:list)
                    System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s]  EMPLOYEE_REGISTRATION[%d]   *****", clt.getCode(), clt.getFirstName(), clt.getLastName(), clt.getBirthDay().toString(), clt.getPhone(), clt.getAddress(), clt.getEmployee().getRegistrationNbr()));
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findClientByCode(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("code:");
            Optional<Client> Clt= ClientDao.findByCode(sc.nextLine());
            Clt.ifPresent((clt)->{
                System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] EMPLOYEE_REGISTRATION[%d]   *****", clt.getCode(), clt.getFirstName(), clt.getLastName(), clt.getBirthDay().toString(), clt.getPhone(), clt.getAddress(), clt.getEmployee().getRegistrationNbr()));
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void updateClient(){
        try{
            Scanner sc = new Scanner(System.in);
            Client clt;
            System.out.print("code client:");
            clt = ClientDao.findByCode(sc.nextLine()).get();
            System.out.print("nom:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setFirstName(tmp_str);
            System.out.print("prenom:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setLastName(tmp_str);
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                    clt.setBirthDay(inputDate);
                else
                    throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR LE CLIENT   *****");
            }
            System.out.print("telephone:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setPhone(tmp_str);
            System.out.print("adresse:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setAddress(tmp_str);
            ClientDao.update(clt).ifPresent((listEmp)->{
                System.out.println("*****   CLIENT MODIFIER AVEC SUCCESS   *****");
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findClientByAttribute(){
        try{
            Scanner sc = new Scanner(System.in);
            Client clt = new Client();
            System.out.print("nom:");
            String tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setFirstName(tmp_str);
            System.out.print("prenom:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setLastName(tmp_str);
            System.out.print("Date de naissance(aaaa-mm-jj):");
            String tmp_date = sc.nextLine();
            if(!tmp_date.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDate = LocalDate.parse(tmp_date, formatter);
                if(Period.between(inputDate, LocalDate.now()).getYears() >= 18)
                    clt.setBirthDay(inputDate);
                else
                    throw new Exception("*****   INVALIDE DATE DE NAISSANCE POUR LE CLIENT   *****");
            }
            System.out.print("telephone:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setPhone(tmp_str);
            System.out.print("adresse:");
            tmp_str = sc.nextLine();
            if(!tmp_str.isEmpty())
                clt.setAddress(tmp_str);
            ClientDao.find(clt).ifPresent((listClt)->{
                for(Client c:listClt){
                    System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] EMPLOYEE_REGISTRATION[%d]   *****", c.getCode(), c.getFirstName(), c.getLastName(), c.getBirthDay().toString(), c.getPhone(), c.getAddress(), clt.getEmployee().getRegistrationNbr()));
                }
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void getClientAccount(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("code:");
            Client clt = ClientDao.findByCode(sc.nextLine()).get();
            ClientDao.getAccounts(clt).ifPresent((client)->{
                System.out.println("*****   COMPTES D'EMPRANGES   *****");
                for(Object saving:client.getAccounts().get("saving")){
                    SavingAccount tmp = (SavingAccount) saving;
                    System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] TAX[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getTax()));
                }
                System.out.println("*****   COMPTES CURRENTS   *****");
                for(Object saving:client.getAccounts().get("current")){
                    CurrentAccount tmp = (CurrentAccount) saving;
                    System.out.println(String.format("*****   CODE[%s] BALANCE[%f] DATE_CREATION[%s] DECOUVERT[%f]  *****", tmp.getCode(), tmp.getBalance(), tmp.getCreationDate().toString(), tmp.getOverDraft()));
                }
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
