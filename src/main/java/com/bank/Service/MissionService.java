package com.bank.Service;

import com.bank.DAO.MissionDAOImpl;
import com.bank.Entity.Mission;

import java.util.Optional;

public class MissionService {
    private MissionDAOImpl MissionDao;
    public MissionService(){
        MissionDao = new MissionDAOImpl();
    }

    public void addMission(Mission mission){
        try{
            if(mission == null)
                throw new Exception("*****   LA MISSION NE PEUT PAS ETRE VIDE   *****");
            Optional<Mission> missionOptional = MissionDao.create(mission);
            if(missionOptional.isPresent())
                MissionDao.create(mission).ifPresent((obj)->{
                    System.out.println("*****   MISSION CREER AVEC SUCCESS   *****");
                });
            else
                throw new Exception("*****   IMPOSSIBLE DE CREER LA MISSION   *****");

        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }

    }
}
