package com.bank.Service;

import com.bank.DAO.AssertionDAOImpl;
import com.bank.Entity.MissionEmployee;

import java.util.Optional;

public class AssertionService {
    AssertionDAOImpl AssertionDao;
    public AssertionService(){
        AssertionDao = new AssertionDAOImpl();
    }

    public void createAssertion(MissionEmployee assertion){
        try{
            if(assertion == null)
                throw new Exception("*****   L'AFFECTATION NE PEUT PAS ETRE VIDE   *****");
            Optional<MissionEmployee> result = AssertionDao.create(assertion);
            if(!result.isPresent())
                System.out.println("*****   ERREUR LORS DE LA CREAION D'UN AFFECTATION   *****");
            else
                System.out.print("*****   AFFECTATION CREER AVEC SUCCESS   *****");
        }catch(Exception e) {

        }
    }
}
