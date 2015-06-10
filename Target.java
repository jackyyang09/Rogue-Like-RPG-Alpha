import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Target here.
 * 
 * @author Ryan Huang
 * @version June 2015
 */
public class Target extends Actor
{
    int mapX;
    int mapY;
    public Target(int getMapX, int getMapY){
        setImage("crosshair.png");
        mapX = getMapX;
        mapY = getMapY;
    }

    public void setMapX(int newMapX){
        mapX = newMapX;
    }

    public void setMapY(int newMapY){
        mapY = newMapY;
    }

    public int getMapX(){
        return mapX;
    }

    public int getMapY(){
        return mapY;
    }
}
