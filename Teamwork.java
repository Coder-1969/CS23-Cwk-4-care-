package cwk4; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS23";
        
        details[1] = "Pathak";
        details[2] = "Sahil";
        details[3] = "21091945";

        details[4] = "Prajapati";
        details[5] = "Aarya";
        details[6] = "21085565";

        details[7] = "Chaudhary";
        details[8] = "Hritik";
        details[9] = "21081214";


        details[10] = "Varghese";
        details[11] = "Alvin";
        details[12] = "21070011";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
