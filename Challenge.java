package cwk4;

class Challenge {
    private int number;
    private String opponentName;
    private String type;
    private int requiredSkillLevel;
    private int reward;
    private int difficulityLevel;

    public Challenge(int number, String enemyName, String type, int requiredSkillLevel, int reward,int difficulityLevel) {
        this.number = number;
        this.opponentName = enemyName;
        this.type = type;
        this.requiredSkillLevel = requiredSkillLevel;
        this.reward = reward;
        this.difficulityLevel = difficulityLevel;
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
    public int getDifficulityLevel(){
        return difficulityLevel;
    }
    public int getReward() {
        return reward;
    }
}
