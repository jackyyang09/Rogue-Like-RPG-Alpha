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
    protected double baseHit;
    protected double baseLuk;
    protected int baseMove;
    protected double attack;
    protected double defense;
    protected double dexterity;
    protected double hit;
    protected double maxHp;
    protected double luck;
    protected int move;
    protected double currentHp;
    protected int mapX;
    protected int mapY;
    protected int playerX;
    protected int playerY;

    public void decreaseMove(){
        move--;
        System.out.println(move);
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

    public void attack(Mobs enemy)
    {
        double chance = enemy.getDexterity() - hit;
        if (Greenfoot.getRandomNumber(100) + 1 <= chance)
        {
            System.out.println("Miss!");
        }
        else
        {
            double lucky = attack;
            if (Greenfoot.getRandomNumber(100) + 1 <= luck)
            {
                lucky = 2*attack;
                System.out.println("Critical!");
            } 
            if(lucky > enemy.getDefense())
            {
                enemy.hurtMe(lucky-enemy.getDefense());
            }
            else
            {
                System.out.println("Too weak");
            }
        }
    }

    public void convertToTile(){
        mapX = (mapX - 43) / 86;
        mapY = (mapY - 43) / 86;
    }

    public void convertToPixel(){
        mapX = (mapX * 86) + 43;
        mapY = (mapY * 86) + 43;
    }

    public int getMapX(){
        return this.mapX;
    }

    public int getMapY(){
        return this.mapY;
    }
}
