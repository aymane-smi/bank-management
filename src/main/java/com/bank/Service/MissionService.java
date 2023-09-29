package com.bank.Service;

import com.bank.DAO.MissionDAOImpl;
import com.bank.Entity.Mission;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
                missionOptional.ifPresent((obj)->{
                    System.out.println("*****   MISSION CREER AVEC SUCCESS   *****");
                });
            else
                throw new Exception("*****   IMPOSSIBLE DE CREER LA MISSION   *****");

        }catch(Exception e){
        }

    }

    public void deleteMission(int code){
        try{
            if(code == 0)
                throw new Exception("*****   LA MISSION NE PEUT PAS ETRE VIDE   *****");
            int result = MissionDao.delete(code);
            if(result == 1)
                System.out.println("*****   MISSION SUPPRIMER AVEC SUCCESS   *****");
            else
                System.out.println("*****   IMPOSSIBLE DE SUPPRIMER LA MISSION   *****");
        }catch(Exception e){
            System.out.println(e.getClass()+"::"+e.getMessage());
        }
    }

    public void findAllMission(){
        Optional<List<Mission>> optionalMissionList = new MissionDAOImpl().findAll();
        if(!optionalMissionList.isPresent())
            System.out.println("*****   ACUNNES MISSION N'EXISTES   *****");
        optionalMissionList.ifPresent((list)->{
            Stream<Mission> stream = list.stream();
            stream.forEach(mission->{
                System.out.println(String.format("*****   CODE[%d] NOM[%s] DESCRIPTION[%s]   *****", mission.getCode(), mission.getName(), mission.getDescription()));
            });
        });
    }
}
