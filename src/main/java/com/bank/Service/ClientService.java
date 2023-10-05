package com.bank.Service;

import com.bank.DAO.ClientDAOImpl;
import com.bank.Entity.Client;
import com.bank.Entity.CurrentAccount;
import com.bank.Entity.SavingAccount;

import java.util.List;
import java.util.Optional;

public class ClientService {
    private ClientDAOImpl ClientDao;
    public ClientService(){
        ClientDao = new ClientDAOImpl();
    }

    public void addClient(Client clt){
        Optional<Client> optionalClt = ClientDao.create(clt);
        optionalClt.ifPresent(val->System.out.println(String.format("*****   AJOUT D'UN CLIENT AVEC CODE[%s]   *****", val.getCode())));
    }
    public void deleteClient(String code){
        int result = ClientDao.delete(code);
        System.out.println(String.format("*****   NOMBRE DES ELEMENTS SUPPRIMEES EST:%d   *****", result));
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

    public Client findClientByCode(String code){
            Optional<Client> Clt= ClientDao.findByCode(code);
            Clt.ifPresent((clt)->{
                System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] EMPLOYEE_REGISTRATION[%d]   *****", clt.getCode(), clt.getFirstName(), clt.getLastName(), clt.getBirthDay().toString(), clt.getPhone(), clt.getAddress(), clt.getEmployee().getRegistrationNbr()));
            });
        return Clt.get();
    }

    public void updateClient(Client clt){
            ClientDao.update(clt).ifPresent((listEmp)->{
                System.out.println("*****   CLIENT MODIFIER AVEC SUCCESS   *****");
            });
    }
    public void findClientByAttribute(Client clt){
        ClientDao.find(clt).ifPresent((listClt)->{
            for(Client c:listClt){
                System.out.println(String.format("*****   CODE[%s] NOM[%s] PRENOM[%s] DATE_NAISSANCE[%s] TELE[%s] ADRESSE[%s] EMPLOYEE_REGISTRATION[%d]   *****", c.getCode(), c.getFirstName(), c.getLastName(), c.getBirthDay().toString(), c.getPhone(), c.getAddress(), clt.getEmployee().getRegistrationNbr()));
            }
        });
    }

    public void getClientAccount(Client clt){
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
    }
}
