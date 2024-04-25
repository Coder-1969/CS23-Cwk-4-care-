package cwk4;

import java.util.HashMap;
import java.util.HashSet;

public class Champion {
    private String championName;
    private int entryFee;
    private String champType;
    private boolean isActive;
    private boolean disqualified;



    public Champion(String name, String type,int entryFee) {
        this.championName = name;

        if (type =="Warrior"||type=="Wizard"||type =="Dragon"){
            champType = type;
        }

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

    public String getChampType() {
        return champType;
    }

    @Override
    public String toString() {
        return championName + (isActive ? " (active)" : "")+" "+ champType+(isDisqualified() ? " (disqualified)" : "") ;
    }
    // Method to check if the champion can meet the challenge
    public int meetChallenge(Challenge challenge) {
        if (!isActive || disqualified)
            return 2; // Challenge lost as no suitable champion available

        if (challenge.getType().equals(champType)) {
            if (challenge.getRequiredSkillLevel() <= 5) { // Assuming skill level of champions is 5
                return 0; // Challenge won
            } else {
                disqualify();
                return 1; // Challenge lost on skill level
            }
        } else {
            return -1; // No such challenge
        }
    }
}
