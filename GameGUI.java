package cwk4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/01/24
 */
public class GameGUI 
{
    private CARE gp = new Tournament("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton meetBtn = new JButton("Meet Challenge");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();

    public static void main(String[] args)
    {
        new GameGUI();
    }
    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(meetBtn);
        eastPanel.add(viewBtn);
        eastPanel.add(clearBtn);
        eastPanel.add(quitBtn);

        
        clearBtn.addActionListener(new ClearBtnHandler());
        meetBtn.addActionListener(new MeetBtnHandler());
        quitBtn.addActionListener(new QuitBtnHandler());
        viewBtn.addActionListener(new ViewBtnHandler());

        meetBtn.setVisible(true);
        viewBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);

        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu championMenu = new JMenu("Champions");
        menubar.add(championMenu);

        JMenu challengeMenu = new JMenu("Challenges");
        menubar.add(challengeMenu);
        
        JMenuItem listChampionItem = new JMenuItem("List Champions in reserve");
        listChampionItem.addActionListener(new ListReserveHandler());
        championMenu.add(listChampionItem);

        JMenuItem listTeamItem = new JMenuItem("List of team");
        listTeamItem.addActionListener(new ListTeamHandler());
        championMenu.add(listTeamItem);

        JMenuItem viewChampion = new JMenuItem("View a Champion");
        viewChampion.addActionListener(new ViewHandler());
        championMenu.add(viewChampion);

        JMenuItem EnterChampionItem = new JMenuItem("Enter a Champion");
        EnterChampionItem.addActionListener(new EnterChampHandler());
        championMenu.add(EnterChampionItem);

        JMenuItem listchallenges = new JMenuItem("List all Challenges");
        listchallenges.addActionListener(new ListChallengeHandler());
        challengeMenu.add(listchallenges);
 
    }
    
    private class ListReserveHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getReserve();
            listing.setText(xx);
        }
    }
    private class ListTeamHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String vv = gp.getTeam();
        }
    }

    private class ListChallengeHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String yy = gp.getAllChallenges();
            listing.setText(yy);
        }
    }
    
   
    private class ClearBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText(" ");
        }
    }
    
    private class MeetBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int result = -1;
            String answer = "no such challenge";
            String inputValue = JOptionPane.showInputDialog("Challenge number ?: ");
            int num = Integer.parseInt(inputValue);
            result = gp.meetChallenge(num);
            switch (result)
            {
                case 0:answer = "challenge won by champion"; break;
                case 1:answer = "challenge lost on skills, champion disqualified";break;
                case 2:answer = "challenge lost as no suitable champion is available";break;
                case 3:answer = "challenge lost and vizier completely defeated";break;
            }
            
            JOptionPane.showMessageDialog(myFrame,answer);    
        }
    }
    
    private class QuitBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            int answer = JOptionPane.showConfirmDialog(myFrame,
                "Are you sure you want to quit?","Finish",
                JOptionPane.YES_NO_OPTION);
            // closes the application
            if (answer == JOptionPane.YES_OPTION)
            {
                System.exit(0); //closes the application
            }              
        }
    }

    private class ViewBtnHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String zz = gp.toString();
            listing.setText(zz);
        }

    }

    private class ViewHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String userInput = JOptionPane.showInputDialog(myFrame,
                    "Enter Champion Name:");
            if (userInput != null){
                JOptionPane.showMessageDialog(myFrame, userInput + gp.getChampionDetails(userInput));
            } else {
                JOptionPane.showMessageDialog(myFrame, "No Champion name entered");
            }
        }
    }

    private class EnterChampHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           String userInput =  JOptionPane.showInputDialog(myFrame, "Enter Champion name:");
            if (userInput != null){

               gp.isInReserve(userInput);

            } else {
                JOptionPane.showMessageDialog(myFrame, "No such Champion!");
            }
        }
      }
    }
