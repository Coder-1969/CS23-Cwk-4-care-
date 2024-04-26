package cwk4;

import java.util.HashMap;
import java.util.HashSet;

public class Champion {
    private String championName;
    private int entryFee;
    private String type;
    private boolean isActive;
    private boolean disqualified;
    private int skillLevel;


    public Champion(String name, String type,int entryFee, int skl) {
        this.championName = name;
        this.type = type;
        skillLevel = skl;
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
    public int getSkillLevel(){
        return skillLevel;
    }
    public String getChampType() {
        return type;
    }

    @Override
    public String toString() {
        return "Name:" + championName +" " + "Type:" + (type != null ? type : "Type not set") ;
    }
    // Method to check if the champion can meet the challenge
    // Method to check if the champion can meet the challenge
    public boolean meetChallenge(Challenge challenge) {
        if (skillLevel >= challenge.getRequiredSkillLevel()){
            return true ;
        }
        else{
            return false;
        }
    }
}
