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

    @Override
    public String toString() {
        return championName + (isActive ? " (active)" : "")+ champType+(isDisqualified() ? " (disqualified)" : "") ;
    }
}
