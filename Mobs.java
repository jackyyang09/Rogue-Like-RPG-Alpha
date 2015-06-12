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
    protected int xp;
    protected int value;
    protected double baseHp;
    protected double baseAtt;
    protected double baseDef;
    protected double baseDex;
    protected double baseHit;
    protected double baseLuk;
    protected int baseMove;
    protected double attack;
    protected double defense;
    protected double dexterity;
    protected double hit;
    protected double luck;
    protected int move;
    protected double currentHp;
    protected int mapX;
    protected int mapY;
    protected int playerX;
    protected int playerY;

    public void decreaseMove(int amt){
        if(move - amt <= 0){
            move = 0;
        } else {
            move -= amt;
        }
    }

    public void resetMove(){
        move = baseMove;
    }

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
    public double getHp() 
    {
        return currentHp;
    }

    /**
     * 
     */
    public double getBaseHp() 
    {
        return baseHp;
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
    public double getHit() 
    {
        return hit;
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
