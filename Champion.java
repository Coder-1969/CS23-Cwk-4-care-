package cwk4;

import java.util.HashMap;
import java.util.HashSet;

public class Champion {
    private String championName;
    private int entryFee;
    private String champType;
    private boolean isActive;
    private boolean disqualified;
    private int strength;


    public Champion(String name, String type,int entryFee, int str) {
        this.championName = name;

        if (type =="Warrior"||type=="Wizard"||type =="Dragon"){
            champType = type;
        }
        strength =str;
        this.entryFee = entryFee;
        this.isActive = false;
    }

    public int getEntryFee() {
        return entryFee;
    }
    public boolean isDisqualified() {
        return disqualified;
    }
    public void disqualify() {
        disqualified = true; // Method to disqualify the champion
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean isActive() {
        return isActive;
    }
    public String getChampionName() {
        return championName;
    }
    public int getStrength(){
        return strength;
    }
    public String getChampType() {
        return champType;
    }

    @Override
    public String toString() {
        return championName + (isActive ? " (active)" : "")+" "+ champType+(isDisqualified() ? " (disqualified)" : "") ;
    }
    // Method to check if the champion can meet the challenge
    public String toString() {
        return championName + (isActive ? " (active)" : "")+" "+ champType+(isDisqualified() ? " (disqualified)" : "") ;
    }
    // Method to check if the champion can meet the challenge
    public boolean meetChallenge(Challenge challenge) {
        if (strength >= challenge.getDifficulityLevel()){
            return true ; 
        }
        else{
            return false;
        }
    }
}
