import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color; // Java Colors(for Health Levels)
import java.util.*; // All other Java tools

/**
 * Creates a HPBar that slowly turns from Green to Yellow to Orange to Red as the Health gets lower and lower
 * <p>
 * The HPBar slowly shrinks towards to the left as the health gets lower
 * <p>
 * The Size of the HPBar is fully adjustable- refer to API
 * <p>
 * The HPBar will be slightly above the owner and update every act based on the owner's health.
 * <p>
 * HPBar will also follow its owner
 * 
 * @author Sean Cao
 * @version March 2015
 */
public class HPBar extends Actor{
    //Declaring all private variables here:
    private GreenfootImage HPBar; //image of HPBar
    private int currentHealth = 0; //to store the current health when updating
    private int totalHealth; //to store the max health to be able to get the health percentage when updating
    private int height = 10; //height of the HPBar
    private int width = 70; //width of the HPBar
    private Actor owner; //owner to follow
    private int offsetOfHPBar = 40; //the HPBar's offset below its owner
    private Color for100Health = new Color(0, 255, 0); //green colour for health(100%-90%)25
    private Color for90Health = new Color(140, 255, 0); //greenish colour for health(90%-80%)
    private Color for80Health = new Color(153, 249, 79); //light green colour for health(80%-70%)
    private Color for70Health = new Color(207, 246, 79); //yellow-green colour for health(70%-60%)
    private Color for60Health = new Color(255, 255, 102); //light yellow colour for health(60%-50%)
    private Color for50Health = new Color(255, 255, 0); //yellow colour for health(50%-40%)
    private Color for40Health = new Color(255, 204, 0); //yellow-orange colour for health(40%-30%)
    private Color for30Health = new Color(255, 153, 0); //orange colour for health(30%-20%)
    private Color for20Health = new Color(255, 102, 0); //red-orange colour for health(20%-10%)
    private Color for10Health = new Color(255, 0, 0); //red colour for health(10%-0%)
    private Color grayHealth = new Color(128,128,128); //gray colour for lost health, anything not covered by the colours above
    private double healthPercent; //percentage of health
    private int health; //size of indicator for health(green to red)
    private int lostHealth; //size of indicator for lost health on the left (gray)

    /**
     * Act Method
     * <p>
     * <p>
     * Checks to see if its owner has moved, and if so, move accordingly
     */
    public void act(){
        //checks to see if there is an owner to follow
        if (owner.getWorld() == null){
            ((ScrollingMap)getWorld()).removeObject(this); //if there is nothing to follow, remove the HPBar
        }
        else if (owner.getWorld() != null){
            setLocation(owner.getX(), owner.getY() - offsetOfHPBar); //follow the owner of this HPBar
        }
    } 

    /**
     * Constructor 1 for a HPBar
     * <p>
     * <p>
     * Accepts a total Health which is the max HP
     * 
     * @param totalHP Max Health of the Health Bar
     */
    public HPBar (int totalHP){
        HPBar = new GreenfootImage(width, height); //makes a new Health Bar
        HPBar.setColor(for100Health); //sets the colour for the Health Bar to be green
        HPBar.fill(); //fill the Health Bar
        totalHealth = totalHP; //sets maxHealth
        this.setImage(HPBar); //sets the image defined above
    }
    
    /**
     * Constructor 2 for a HPBar
     * <p>
     * <p>
     * Accepts a total Health which is the max HP
     * <p>
     * Also accepts a desired width and height for the HPBar
     * 
     * @param totalHP Max Health of the Health Bar
     * @param wantedWidth Desired width of the HPBar
     * @param wantedHeight Desired height of the HPBar
     */
    public HPBar (int totalHP, int wantedWidth, int wantedHeight){
        width = wantedWidth; //sets width to desired amount
        height = wantedHeight; //sets height to desired amount
        HPBar = new GreenfootImage(width, height); //makes a new Health Bar
        HPBar.setColor(for100Health); //sets the colour for the Health Bar to be green
        HPBar.fill(); //fill the Health Bar
        totalHealth = totalHP; //sets maxHealth
        this.setImage(HPBar); //sets the image defined above 
    }
    
    /**
     * Constructor 3 for a HPBar
     * <p>
     * <p>
     * Accepts a total Health which is the max HP that the owner has and accepts an actor to be set as the owner
     * 
     * @param totalHP Max Health of the Health Bar's owner
     * @param target The owner of this Health Bar
     */
    public HPBar (int totalHP, Actor target){
        HPBar = new GreenfootImage(width, height); //makes a new Health Bar
        HPBar.setColor(for100Health); //sets the colour for the Health Bar to be green
        HPBar.fill(); //fill the Health Bar
        totalHealth = totalHP; //sets maxHealth
        this.owner = target; //sets its owner
        this.setImage(HPBar); //sets the image defined above
    }

    /**
     * Constructor 4 for a HPBar
     * <p>
     * <p>
     * Accepts a total Health which is the max HP that the owner has and accepts an actor to be set as the owner
     * <p>
     * Also accepts a desired width and height for the HPBar
     * 
     * @param totalHP Max Health of the Health Bar's owner
     * @param target The owner of this Health Bar
     * @param wantedWidth Desired width of the HPBar
     * @param wantedHeight Desired height of the HPBar
     */
    public HPBar (int totalHP, Actor target, int wantedWidth, int wantedHeight){
        width = wantedWidth; //sets width to desired amount
        height = wantedHeight; //sets height to desired amount
        HPBar = new GreenfootImage(width, height); //makes a new Health Bar
        HPBar.setColor(for100Health); //sets the colour for the Health Bar to be green
        HPBar.fill(); //fill the Health Bar
        totalHealth = totalHP; //sets maxHealth
        this.owner = target; //sets its owner
        this.setImage(HPBar); //sets the image defined above 
    }

    /**
     * Sets the health for the HPBar
     * <p>
     * <p>
     * Accepts the percentage of health left
     * 
     * @param percentHealth Percentage of Health Remaining
     */
    public void setHealth(double percentHealth){
        if(healthPercent >= 0.9)
            HPBar.setColor(for100Health);
        else if(healthPercent >= 0.8)
            HPBar.setColor(for90Health);
        else if(healthPercent >= 0.7)
            HPBar.setColor(for80Health);
        else if(healthPercent >= 0.6)
            HPBar.setColor(for70Health);
        else if(healthPercent >= 0.5)
            HPBar.setColor(for60Health);
        else if(healthPercent >= 0.4)
            HPBar.setColor(for50Health);
        else if(healthPercent >= 0.3)
            HPBar.setColor(for40Health);
        else if(healthPercent >= 0.2)
            HPBar.setColor(for30Health);
        else if(healthPercent >= 0.1)
            HPBar.setColor(for20Health);
        else
            HPBar.setColor(for10Health);
    }
    
    /**
     * Update method 1 for HPBar
     * <p>
     * <p>
     * Requires a boolean and int input to add or remove a custom amount of hit points from the HPBar
     * 
     * @param addOrRemove True is to add hit point(s), False is to decrease hit point(s)
     * @param healthToChange HP to change to
     */
    public void update(boolean addOrRemove, int healthToChange){
        //checks if the user wants to add or subtract
        if(addOrRemove)
            currentHealth += healthToChange; //changes the current health status
        else if(!addOrRemove)
            currentHealth -= healthToChange; //changes the current health status
        healthPercent = (double) currentHealth / totalHealth; //find percentage of health
        health = (int) (healthPercent * width); //sets health to how much health is remaining
        lostHealth = width - health;
        setHealth(healthPercent);
        HPBar.fillRect(0, 0, health, height);
        HPBar.setColor(grayHealth);
        HPBar.fillRect(health, 0, lostHealth, height);
        this.setImage(HPBar); //sets the image
    }

    /**
     * Update method 2 for HPBar
     * <p>
     * <p>
     * Requires the new HP to change to
     * 
     * @param newHealth new HP to be updated to
     */
    public void update(int newHealth){
        currentHealth = newHealth; //changes the health status
        healthPercent = (double) currentHealth / totalHealth; //find percentage of health
        health = (int) (healthPercent * width); //sets health to how much health is remaining
        lostHealth = width - health;
        setHealth(healthPercent);
        HPBar.fillRect(0, 0, health, height);
        HPBar.setColor(grayHealth);
        HPBar.fillRect(health, 0, lostHealth, height);
        this.setImage(HPBar); //sets the image
    }
}
