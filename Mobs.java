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
    protected int value;//value of the object for xp point gain
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

    /**
     * decreases movement speed
     * 
     * @param amt value of move you want to decrease
     */
    public void decreaseMove(int amt){
        if(move - amt <= 0){
            move = 0;
        } else {
            move -= amt;
        }
    }

    /**
     * resets movement speed to default
     */
    public void resetMove(){
        move = baseMove;
    }

    /**
     * returns defense
     * 
     * @return defense the defense stat
     */
    public double getDefense() 
    {
        return defense;
    }

    /**
     * returns attack
     * 
     * @return defense the attack stat
     */
    public double getAttack() 
    {
        return attack;
    }

    /**
     * returns  currentHp
     * 
     * @return currentHp the currentHp stat
     */
    public double getHp() 
    {
        return currentHp;
    }

    /**
     * returns  baseHp
     * 
     * @return baseHp the baseHp stat
     */
    public double getBaseHp() 
    {
        return baseHp;
    }

    /**
     * returns  dexterity
     * 
     * @return dexterity the dexterity stat
     */
    public double getDexterity() 
    {
        return dexterity;
    } 

    /**
     * returns  hit
     * 
     * @return hit the hit stat
     */
    public double getHit() 
    {
        return hit;
    } 

    /**
     * returns  move
     * 
     * @return move the move stat
     */
    public int getMove() 
    {
        return move;
    }

    /**
     * returns  luk
     * 
     * @return luk the luk stat
     */
    public double getLuk() 
    {
        return luck;
    }

    /**
     * returns  level
     * 
     * @return level the level stat
     */
    public int getLevel() 
    {
        return level;
    }

    /**
     * only here so that levelGenerate() will work for subclasses
     */
    public void levelUp() 
    {

    }

    /**
     * decreases the currentHp, minimum 0
     * 
     * @param dmg the currentHp that will be decreases
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
     * increases the currentHp, max baseHp stat
     * 
     * @param heal the currentHp that will be increases
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
     * generates the level of a character
     * 
     * @param level the level the object will reach
     */
    public void levelGenerate(int level) 
    {
        for(int i = getLevel(); i<level; i++)
        {
            levelUp();
        }
    } 

    /**
     * gives total xp value of character
     * 
     */
    public int giveXp() 
    {
        return value*level/7;
    }  

    
    /**
     * this unit attacks other unit with calculations
     * 
     * @param enemy the unit that is being attacked
     */
    public void attack(Mobs enemy)
    {
        FadingWord text;
        FadingWord critical;
        double chance = enemy.getDexterity() - hit;
        if (Greenfoot.getRandomNumber(100) + 1 <= chance)
        {
            text = new FadingWord("Miss!");
            getWorld().addObject(text, enemy.getX(), enemy.getY() - 31);//decides whether atacked unit dodges
        }
        else
        {
            double lucky = attack
            if (Greenfoot.getRandomNumber(100) + 1 <= luck)
            {
                lucky = 2*attack;
                critical = new FadingWord("Critical!");
                getWorld().addObject(critical, enemy.getX(), enemy.getY() - 31);//decides critical hit
            } 
            if(lucky > enemy.getDefense())
            {
                GreenfootSound atk = new GreenfootSound("atk_Player.mp3");
                enemy.hurtMe(lucky-enemy.getDefense());
                String damage = "Dmg " + (lucky-enemy.getDefense());
                text = new FadingWord(damage);//damages enemy
            }
            else
            {
                text = new FadingWord("Too Weak");
            }
            getWorld().addObject(text, enemy.getX(), enemy.getY() - 31);
            if(enemy.getHp() == 0)
            {
                xp += enemy.giveXp();
                getWorld().removeObject(enemy);//gives xp and removes enemy from world
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
