package cwk4;

class Challenge {
    private int number;
    private String opponentName;
    private String type;
    private int requiredSkillLevel;
    private int reward;


    public Challenge(int number, String enemyName, String type, int requiredSkillLevel, int reward) {
        this.number = number;
        this.opponentName = enemyName;
        this.type = type;
        this.requiredSkillLevel = requiredSkillLevel;
        this.reward = reward;

    }

    public int getNumber() {
        return number;
    }

    public String getEnemyName() {
        return opponentName;
    }

    public String getType() {
        return type;
    }

    public int getRequiredSkillLevel() {
        return requiredSkillLevel;
    }

    public int getReward() {
        return reward;
    }

    @Override
    public String toString(){
       return "Challenge #" + number + "against " + opponentName + "( " + type + " type) - Skill Level: " + requiredSkillLevel + ", Reward: " + reward;

    }
}
