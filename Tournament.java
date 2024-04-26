package cwk4;
import java.lang.reflect.Type;
import java.util.*;
import java.util.HashMap;
/**
 * This interface specifies the behaviour expected from CARE
 * as required for 5COM2007 Cwk 4
 * 
 * @author 
 * @version 
 */

public class Tournament implements CARE
{
   
    private String vizier;
    private HashMap<String, Champion> champions ;
    private HashSet<String> team;
    private int treasury;
    private HashMap<Integer,Challenge>challenges;


//**************** CARE ************************** 
    /** Constructor requires the name of the vizier
     * @param viz the name of the vizier
     */  
    public Tournament(String viz)
    {
        this.vizier = viz;
        champions = new HashMap<>();
        team = new HashSet<>();
        treasury = 1000;

        setupChampions();
        setupChallenges();
    }
    
    /** Constructor requires the name of the vizier and the
     * name of the file storing challenges
     * @param viz the name of the vizier
     * @param filename name of file storing challenges
     */  
    public Tournament(String viz, String filename)  //Task 3.5
    {
       this(viz);
        
       setupChampions();
       readChallenges(filename);
    }
    
    
    /**Returns a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     * 
     * @return a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     **/
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("\nVizier: ").append(vizier);
        s.append("\nTreasury: ").append(treasury);
        s.append("\nDefeated: ").append(isDefeated() ? "Yes" : "No");
        s.append("\nChampions in the team: ");

        if (team.isEmpty()){
            s.append("No Chapions");

        } else {
            for (String champName : team){
                s.append(champions.get(champName).toString()).append(",  ");

            }

            int len = s.length();
            s.delete(len - 2, len);
        }
        
        return s.toString();
    }
    
    
    /** returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     * @returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     */
    public boolean isDefeated()
    {
        if(treasury <= 0 && team.isEmpty()){
            return true;
        }
        for (String champName : team) {
            Champion champ = champions.get(champName);
            if (!champ.isDisqualified()){
                return false;
            }
        }

        return treasury <=0 ;
    }
    
    /** returns the amount of money in the Treasury
     * @returns the amount of money in the Treasury
     */
    public int getMoney()
    {
        return treasury;
    }
    
    
    /**Returns a String representation of all champions in the reserves
     * @return a String representation of all champions in the reserves
     **/
    public String getReserve()
    {   
        StringBuilder s = new StringBuilder ("************ Champions available in reserves********\n");

        for(Map.Entry<String, Champion> entry : champions.entrySet()){
            String champName = entry.getKey();
            Champion champ = entry.getValue();

            if(!team.contains(champName)){
                s.append(champ.toString()).append("\n");
            }
        }
        if (s.toString().equals("************ Champions available in reserves ********\n")){
            s.append("No champions in reserves");

        }
        return s.toString();

    }
    
        
    /** Returns details of the champion with the given name. 
     * Champion names are unique.
     * @return details of the champion with the given name
     **/
    public String getChampionDetails(String nme)
    {
       if (champions.containsKey(nme)){
           return champions.get(nme).toString();
       }
        return "\nNo such champion";
    }    
    
    /** returns whether champion is in reserve
    * @param nme champion's name
    * @return true if champion in reserve, false otherwise
    */
    public boolean isInReserve(String nme)
    {
        if(!champions.containsKey(nme)){
            return false;

        }
        return !team.contains (nme);
    }
 
    // ***************** Team champions ************************   
     /** Allows a champion to be entered for the vizier's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "active"
     * 0 if champion is entered in the vizier's team, 
     * 1 if champion is not in reserve, 
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param nme represents the name of the champion
     * @return as shown above
     **/        
    public int enterChampion(String nme)
    {
        if(!champions.containsKey(nme)){
          return -1;
    }
        if(team.contains(nme)) {
            return 1;
        }

        Champion champ = champions.get(nme);

        if(treasury < champ.getEntryFee()){
            return 2;
        }

        treasury -= champ.getEntryFee();
        team.add(nme);
        champ.setActive(true);

        return 0;
    }
        
     /** Returns true if the champion with the name is in 
     * the vizier's team, false otherwise.
     * @param nme is the name of the champion
     * @return returns true if the champion with the name
     * is in the vizier's team, false otherwise.
     **/
    public boolean isInViziersTeam(String nme)
    {
        return team.contains(nme);
    }
    
    /** Removes a champion from the team back to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because disqualified
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param nme is the name of the champion
     * @return as shown above 
     **/
    public int retireChampion(String nme)
    {
        if(!champions.containsKey(nme)){
            return -1;
        }
        if(!team.contains(nme)){
            return 2;
        }
        if (champions.get(nme).isDisqualified()){
            return 1;
        }

        team.remove(nme);
        return 0;
    }

    /**Returns a String representation of the champions in the vizier's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the vizier's team
     **/


    public String getTeam()
    {
        StringBuilder s = new StringBuilder("************ Vizier's Team of champions********\n");

        if (team.isEmpty()){
            s.append("NO CHAMPIONS ENTERED");
        } else {

            for (String champName : team){
                s.append(champions.get(champName).toString()).append("\n");
            }
        }
        
       
        return s.toString();
    }

     /**Returns a String representation of the disquakified champions in the vizier's team
     * or the message "No disqualified champions "
     * @return a String representation of the disqualified champions in the vizier's team
     **/
    public String getDisqualified()
    {
        StringBuilder s = new StringBuilder("************ Vizier's Disqualified champions********\n");
        boolean foundDisqualified = false;

        for(String champName : team){
            Champion champ = champions.get(champName);

            if (champ.isDisqualified()){
                s.append(champ.toString()).append("\n");
                foundDisqualified = true;
            }
        }

        if (!foundDisqualified){
            s.append("No disqualified Champions");
        }
        
        return s.toString();
    }
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the  number of the challenge
     * @return true if the  number represents a challenge
     **/
     public boolean isChallenge(int num)
     {
         return challenges.containsKey(num);
     }    
   
    /** Provides a String representation of an challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num)
    {
        if(challenges.containsKey(num)){
            return challenges.get(num).toString();
        }
        
        return "\nNo such challenge";
    }
    
    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges()
    {
        StringBuilder s = new StringBuilder ("\n************ All Challenges ************\n");
        if (challenges.isEmpty()) {
            s.append("NO CHALLENGES AVAILABLE");
        } else {
            for (Map.Entry<Integer, Challenge> entry : challenges.entrySet()) {
                s.append(entry.getValue().toString()).append("\n");  // Make sure newline is correct
            }
        }
        return s.toString();
    }
    
    
       /** Retrieves the challenge represented by the challenge 
     * number.Finds a champion from the team who can meet the 
     * challenge. The results of meeting a challenge will be 
     * one of the following:  
     * 0 - challenge won by champion, add reward to the treasury, 
     * 1 - challenge lost on skills  - deduct reward from
     * treasury and record champion as "disqualified"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and vizier completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */ 
    public int meetChallenge(int chalNo)
    {
        //Nothing said about accepting challenges when bust
        if(!challenges.containsKey(chalNo)){
            return -1;
        }

        Challenge challenge  = challenges.get(chalNo);

        if(team.isEmpty()){
            treasury -= challenge.getReward();
            if(treasury<=0){
                return 3;
            }
            return 2;
        }

        for(String champName : team){
            Champion champ = champions.get(champName);
            if(champ.meetChallenge(challenge)){
                team.remove(champName);
                treasury += challenge.getReward();
                return 0 ;
            }
        }

        treasury -= challenge.getReward();
        if(treasury <= 0){
            return 3;
        }

        String firstChampName = team.iterator().next();
        Champion firstChamp = champions.get(firstChampName);
        firstChamp.disqualify();

        
        return 1;
    }
 

    //****************** private methods for Task 3 functionality*******************
    //*******************************************************************************
    private void setupChampions()
    {
        champions = new HashMap<>();

        champions.put("Ganfrank", new Champion("Ganfrank" , " Wizard" , 400 , 7));
        champions.put("Rudolf", new Champion("Rudolf" , " Wizard" , 400 , 6 ));
        champions.put("Elblond", new Champion("Elblond" , " Warrior" , 150 , 1 ));
        champions.put("Flimsi", new Champion("Flimsi" , " Warrior" , 200 , 2 ));
        champions.put("Drabina", new Champion("Drabina" , " Dragon" , 500 , 7 ));
        champions.put("Golum", new Champion("Golum" , " Dragon" , 500, 7 ));
        champions.put("Argon", new Champion("Argon" , " Warrior" , 900 , 9 ));
        champions.put("Neon", new Champion("Neon" , " Wizard" , 300 , 2 ));
        champions.put("Xenon", new Champion("Xenon" , " Dragon" , 500 , 7 ));
        champions.put("Atlanta", new Champion("Atlanta" , " Warrior" , 500 , 5 ));
        champions.put("Krypton", new Champion("Krypton" , " Wizard" , 300 , 8 ));
        champions.put("Hedwig", new Champion("Hedwig" , " Wizard" , 400 , 1 ));




   }
     
    private void setupChallenges()
    {
       challenges = new HashMap<>();

       challenges.put(1, new Challenge(1, "Borg" , "Magic" , 3, 100 ));
       challenges.put(2, new Challenge(2 , "Huns" , "Fight" , 3 , 120 ));
        challenges.put(3, new Challenge(3, "Ferengi" , "Mystery" , 3 , 150 ));
        challenges.put(4, new Challenge(4 , "Vandal" , "Magic" , 9, 200 ));
        challenges.put(5, new Challenge(5, "Borg" , "Mystery" , 7, 90));
        challenges.put(6, new Challenge(6 , "Goth" , "Fight" , 8, 45 ));
        challenges.put(7, new Challenge(7, "Frank" , "Magic" , 10 , 200 ));
        challenges.put(8, new Challenge(8, "Sith" , "Fight" , 10, 170));
        challenges.put(9, new Challenge(9, "Cradashian" , "Mystery" , 9, 300 ));
        challenges.put(10, new Challenge(10, "Jute" , "Fight" , 2 , 300 ));
        challenges.put(11, new Challenge(11, "Celt" , "Magic" , 2 , 250 ));
        challenges.put(12, new Challenge(12, "Celt" , "Mystery" , 1, 250));





    }
        
    // Possible useful private methods
//     private Challenge getAChallenge(int no)
//     {
//         
//         return null;
//     }
//    
//     private Champion getChampionForChallenge(Challenge chal)
//     {
//         
//         return null;
//     }

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3.5 ************************************************/  
    
    // ***************   file write/read  *********************
    /**
     * reads challenges from a comma-separated textfile and stores in the game
     * @param filename of the comma-separated textfile storing information about challenges
     */
    public void readChallenges(String filename)
    { 
        
    }   
    
     /** reads all information about the game from the specified file 
     * and returns a CARE reference to a Tournament object, or null
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */
    public Tournament loadGame(String fname)
    {   // uses object serialisation 
       Tournament yyy = null;
       
       return yyy;
   }
   
   /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
   public void saveGame(String fname){
        // uses object serialisation 
        
    }
 

}



