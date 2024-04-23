package cwk4;

import java.io.*;
/**
 * Enumeration class ChallengeType - list possible types of challenges
 * 
 * @author A.A.Marczyk
 * @version 01/03/2024
 */
public enum ChallengeType implements Serializable
{
    MAGIC(" Magic"), FIGHT("Fight"), MYSTERY ("Mystery");
    private String type;
    
    private ChallengeType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}
