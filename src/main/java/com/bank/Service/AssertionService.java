package com.bank.Service;

import com.bank.DAO.AssertionDAOImpl;
import com.bank.DAO.MissionDAOImpl;
import com.bank.Entity.MissionEmployee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void deleteAssertion(int id){
        try{
            if(id == 0)
                throw new Exception("*****   LA ASSERTION NE PEUT PAS ETRE VIDE   *****");
            int result = AssertionDao.delete(id);
            if(result == 1)
                System.out.println("*****   ASSERTION SUPPRIMER AVEC SUCCESS   *****");
            else
                System.out.println("*****   IMPOSSIBLE DE SUPPRIMER LA ASSERTION   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void showStats(){
        HashMap<Integer, Integer> stats = AssertionDao.getStatistics().get();
        for (Map.Entry<Integer, Integer> entry : stats.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("MISSION_CODE[" + new MissionDAOImpl().findByCode(key).get().getName() + "] NOMBRE_EMPLOYEE[: " + value+"]");
        }
    }

    public void findAssertionByEmployee(int registrationNbr){
        try{
            if(registrationNbr == 0)
                throw new Exception("*****   LA ASSERTION NE PEUT PAS ACCEPTER UN EMPLOYEE AVEC MATRICULE 0   *****");
            else{
                Optional<List<MissionEmployee>> list = AssertionDao.findByEmployee(registrationNbr);
                list.get().forEach((obj)->{
                    System.out.println(String.format("*****   ID[%d] NOM_MISSION[%s] DATE_DEBUT[%s] DATE_FIN[%s]   *****", obj.getId(), obj.getMission().getName(), obj.getStartDate(), obj.getEndDate()));
                });
            }
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }
}
