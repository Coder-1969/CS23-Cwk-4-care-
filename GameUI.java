package cwk4;
import java.io.*;
import java.util.*;
/**
 * Command line interface
 *
 * @author A.A.Marczyk
 * @version 10/03/2024
 */
public class GameUI
{

    private Scanner myIn = new Scanner(System.in);

    public void runGame()
    {
        Tournament tr ;
        int choice;
        String vizierName;
        String output = "";
        int result = -1;
        try
        {
            System.out.println("Enter vizier's name");
            String s = myIn.nextLine();
            //myIn.hnextLine();
            tr = new Tournament(s); // create
            //tr = new Tournament(s,"challengesAM.txt"); // alternative create task 3.5
            choice = 100;
            while (choice != 0 )
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(tr.getReserve());
                }
                else if (choice == 2)
                {
                    System.out.println(tr.getTeam());
                }
                else if (choice == 3)
                {
                    System.out.println("Enter Champion name");
                    String ref = (myIn.nextLine()).trim();
                    System.out.println(tr.getChampionDetails(ref));
                }
                else if (choice == 4)
                {
                    System.out.println("Enter Champion Name");
                    String champName = myIn.nextLine().trim(); // getting champions name from users input
                    int res = tr.enterChampion(champName); // Assuming "tr" is your Tournment object and it has the the enter champion method
                    switch(res){
                        case -1:
                            System.out.println("Champion not Found in the reserve list ");
                            break;
                        case 0 :
                            System.out.println("Champion successfully added to the team!");
                            break;
                        case 1:
                            System.out.println("This champion is already in the team.");
                            break;
                        case 2:
                            System.out.println("Insufficient funds to enter this champion.");
                            break;
                        default:
                            System.out.println("Unexpected error occurred.");
                            break;

                    }
                    // provide code here
                    // output should be meaningful

                }
                else if (choice == 5)
                {
                    // Display all challenges
                    System.out.println(tr.getAllChallenges());

                    // Ask the user to enter the ID of the challenge they want to meet
                    System.out.println("Enter the ID of the challenge you want to meet:");
                    int challengeId;
                    try {
                        challengeId = Integer.parseInt(myIn.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric ID.");
                        continue; // Skip the rest of the loop iteration if input is not an integer
                    }

                    // Check if the challenge exists
                    if (!tr.isChallenge(challengeId)) {
                        System.out.println("No such challenge exists. Please try again.");
                        continue;
                    }

                    // Display the list of champions in the vizier's team
                    System.out.println("Select a champion from your team to meet the challenge:");
                    System.out.println(tr.getTeam());

                    // Ask for the champion's name
                    System.out.println("Enter the name of the champion from your team who will meet the challenge:");
                    String championName = myIn.nextLine().trim();

                    // Check if the champion is in the vizier's team
                    if (!tr.isInViziersTeam(championName)) {
                        System.out.println("This champion is not in your team or does not exist. Please try again.");
                        continue;
                    }

                    // Attempt to meet the challenge with the selected champion
                    int res = tr.meetChallenge(challengeId);

                    // Process the result based on the outcome of the challenge attempt
                    switch (res) {
                        case 0:
                            System.out.println("Challenge successfully met! Congratulations!");
                            break;
                        case 1:
                            System.out.println("Challenge lost on skills - your champion was not skilled enough.");
                            break;
                        case 2:
                            System.out.println("Challenge could not be met - no suitable champion was available.");
                            break;
                        case 3:
                            System.out.println("You have been defeated completely (no money and no champions to withdraw).");
                            break;
                        case -1:
                            System.out.println("No such challenge.");
                            break;
                        default:
                            System.out.println("Unexpected error occurred. Please try again.");
                            break;
                    }
                    // provide code here
                    // output should be meaningful
                }
                else if (choice==6)
                {
                    // Display the list of champions in the vizier's team
                    System.out.println("Select a champion from your team to retire:");
                    System.out.println(tr.getTeam());

                    // Check if there are any champions to retire
                    if (tr.getTeam().equals("************ Vizier's Team of champions********\nNO CHAMPIONS ENTERED")) {
                        System.out.println("There are no champions in your team to retire.");
                        continue;
                    }

                    // Ask for the champion's name to retire
                    System.out.println("Enter the name of the champion you wish to retire:");
                    String championName = myIn.nextLine().trim();

                    // Attempt to retire the champion
                    int res = tr.retireChampion(championName);

                    // Process the result based on the outcome of the retirement attempt
                    switch (res) {
                        case 0:
                            System.out.println("Champion successfully retired and returned to reserves.");
                            break;
                        case 1:
                            System.out.println("Cannot retire champion because they are disqualified.");
                            break;
                        case 2:
                            System.out.println("Champion not retired because they are not in the team.");
                            break;
                        case -1:
                            System.out.println("No such champion exists.");
                            break;
                        default:
                            System.out.println("Unexpected error occurred. Please try again.");
                            break;
                    // provide code here
                    // output should be meaningful
                        }
                }
                else if (choice==7)
                {
                    // Display the current state of the game
                    System.out.println("Current Game State:");
                    System.out.println(tr.toString());  // Assuming tr.toString() is implemented to return the desired game state info

                    // Optionally, could add more detailed statistics if necessary:
                    System.out.println("Active Champions in Team:");
                    System.out.println(tr.getTeam());  // List of active champions

                    System.out.println("Disqualified Champions:");
                    System.out.println(tr.getDisqualified());  // List of disqualified champions if this method is implemented
                }
                else if (choice==8)
                {
                    System.out.println(tr.getAllChallenges());
                }
                else if (choice == 9) // Task 3.5 only
                {
                    System.out.println("Write to file");
                    System.out.println("Enter file name");
                    String filename = myIn.nextLine();
                    tr.saveGame(filename);
                }
                else if (choice == 10) // Task 3.5 only
                {
                    System.out.println("Restore from file");
                    System.out.println("Enter file name");
                    String filename = myIn.nextLine();
                    CARE tr2= tr.loadGame(filename);
                    if (tr2 != null)
                    {
                        System.out.println(tr2.toString());
                    }
                    else
                    {
                        System.out.println("No such file");
                    }
                }
            }
        }
        catch (IOException e) {System.out.println (e);}
        System.out.println("Thank-you");
    }

    private int getMenuItem()throws IOException
    {   int choice = 100;
        System.out.println("\nMain Menu");
        System.out.println("0. Quit");
        System.out.println("1. List champions in reserve");
        System.out.println("2. List champions in viziers team");
        System.out.println("3. View a champion");
        System.out.println("4. Enter champion into vizier's team");
        System.out.println("5. Meet a challenge");
        System.out.println("6. Retire a champion");
        System.out.println("7. View game state");
        System.out.println("8. See all challenges");
        System.out.println("9. Save this game");
        System.out.println("10. Load this game");


        while (choice < 0 || choice  > 10)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        myIn.nextLine();
        return choice;
    }

    private String processChallengeResult(int res) {

        String out;
        if (res ==0)
        {
            out = "Challenge won";
        }
        else if (res ==1)
        {
            out = "Challenge lost on skill level";
        }
        else if (res ==2)
        {
            out = "Challenge lost as no champion available";
        }
        else if (res ==3)
        {
            out = "Challenge lost with no further resources. You lose the game ";
        }
        else if (res == -1)
        {
            out = "No such challenge";
        }
        else
        {
            out = "No such result";
        }
        return out;
    }

    public static void main(String[] args)
    {
        new GameUI().runGame();
    }
}
