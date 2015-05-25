import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mobs here.
 * 
 * @author Kajamugesh Raneethran
 * @version May 2015
 */
public class Mobs extends Actor
{
    protected int level;
    protected int Xp;
    protected double baseHp;
    protected double baseAtt;
    protected double baseDef;
    protected double baseDex;
    protected double baseLuk;
    protected int baseMov;
    protected double attack;
    protected double defense;
    protected double dexterity;
    protected double hp;
    protected double luck;
    protected int move;

    /**
     * 
     */
    public double getDefense() 
    {
        return defense;
    }

    /**
     * 
     */
    public double getAttack() 
    {
        return attack;
    }

    /**
     * 
     */
    public double getHP() 
    {
        return hp;
    }

    /**
     * 
     */
    public double getDexterity() 
    {
        return dexterity;
    } 

    /**
     * 
     */
    public int getMove() 
    {
        return move;
    }

        /**
     * 
     */
    public double getDexterity() 
    {
        return dexterity;
    }
    
     /**
     * 
     */
    public int getLevel() 
    {
        return level;
    }
    
    /**
     * 
     */
    public void levelUp() 
    {
        
    }

    /**
     * 
     */
    public void levelGenerate(int level) 
    {
        for(int i = getLevel(); i<level; i++)
        {
            levelUp();
        }
    } 
}
