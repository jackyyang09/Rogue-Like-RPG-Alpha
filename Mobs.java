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
    protected double maxHp;
    protected double luck;
    protected int move;
    protected double currentHp;
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
        return currentHp;
    }

    /**
     * 
     */
    public double getMaxHP() 
    {
        return maxHp;
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
    public double getLuk() 
    {
        return luck;
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
    public void hurtMe(double dmg) 
    {
        if(dmg<=currentHp){
            currentHp -=  dmg;
        }
        else
        {
            currentHp = 0; 
        }
    }

    /**
     * 
     */
    public void healMe(double heal) 
    {
        if(heal + currentHp <=maxHp){
            currentHp +=  heal;
        }
        else
        {
            currentHp = maxHp; 
        }
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
