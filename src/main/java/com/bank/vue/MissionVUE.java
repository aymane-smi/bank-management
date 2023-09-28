package com.bank.vue;

import com.bank.Entity.Mission;
import com.bank.Service.MissionService;

import java.util.Scanner;

public class MissionVUE {
    private MissionService missionService;
    public MissionVUE(){
        missionService = new MissionService();
    }
    public void addMission(){
        Scanner sc = new Scanner(System.in);
        System.out.print("nom:");
        String name = sc.nextLine();
        System.out.print("description:");
        String description = sc.nextLine();
        Mission mission = new Mission(0, name, description);
        missionService.addMission(mission);
        sc.close();
    }
    public void deleteMission(){
        Scanner sc = new Scanner(System.in);
        System.out.print("code:");
        missionService.deleteMission(sc.nextInt());
        sc.close();
    }
}
