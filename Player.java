import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Mobs
{
    public Player(){
        setImage("player.png");
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMov = 2;
    }
}
