package com.bank.Service;

import com.bank.DAO.AgencyDAO;
import com.bank.DAO.AgencyDAOImpl;
import com.bank.Entity.Agency;

import java.util.Optional;

public class AgencyService {
    private AgencyDAOImpl agencyDao;
    public AgencyService(AgencyDAOImpl agencyDAO){
        this.agencyDao = agencyDAO;
    }

    public void create(Agency agency){
        try{
            if(agency == null)
                throw new Exception("*****   L'AGENCE NE PAS ETRE NULL   *****");
            Optional<Agency> optionalAgency = agencyDao.create(agency);
            optionalAgency.ifPresent((obj)->{
                System.out.println("*****   AGENCE CREER AVEC SUCCESS   *****");
            });
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void delete(String code){
        try{
            if(code == "")
                throw new Exception("*****   LE CODE D'AGENCE NE PAS ETRE VIDE   *****");
            if(agencyDao.delete(code) == 1)
                System.out.println("*****   AGENCE SUPPRIMER AVEC SUCCESS   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
