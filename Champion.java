package cwk4;

public class Champion {
    private String championName;
    private int entryFee;
    private boolean isActive;

    public Champion(String name, int entryFee) {
        this.championName = name;
        this.entryFee = entryFee;
        this.isActive = false;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return championName + (isActive ? " (active)" : "");
    }
}