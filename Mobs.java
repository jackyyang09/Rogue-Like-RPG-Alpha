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
    
    public double getXp() 
    {
        return xp;
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
        if(heal + currentHp <=baseHp){
            currentHp +=  heal;
        }
        else
        {
            currentHp = baseHp; 
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

   /**
     * 
     */
    public int giveXp() 
    {
        return value*level/6;
    }  

   public void attack(Mobs enemy)
    {
        FadingWord text;
        FadingWord critical;
        double chance = enemy.getDexterity() - hit;
        if (Greenfoot.getRandomNumber(100) + 1 <= chance)
        {
            text = new FadingWord("Miss!");
        }
        else
        {
            // double random = Greenfoot.getRandomNumber(3);
            //if(Greenfoot.getRandomNumber(2)==0)
            //{
             //   random = -random;
            //}
            //double lucky = attack + random;
            double lucky = attack;
            if (Greenfoot.getRandomNumber(100) + 1 <= luck)
            {
                lucky = 2*attack;
                critical = new FadingWord("Critical!");
                getWorld().addObject(critical, enemy.getX(), enemy.getY() - 31);
            } 
            if(lucky > enemy.getDefense())
            {
                enemy.hurtMe(lucky-enemy.getDefense());
                String damage = "Dmg " + (lucky-enemy.getDefense());
                text = new FadingWord(damage);
            }
            else
            {
                text = new FadingWord("Too Weak");
            }
            getWorld().addObject(text, enemy.getX(), enemy.getY() - 31);
            if(enemy.getHp() == 0)
            {
                xp += enemy.giveXp();
               getWorld().removeObject(enemy);
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
