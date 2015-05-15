import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dirt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    int mapX;
    int mapY;
    public Tile(int getMapX, int getMapY, int tileCode)
    {
        if(tileCode == 1){
            mapX = getMapX;
            mapY = getMapY;
            setImage("dirt.jpg");
        } else if(tileCode == 2){
            mapX = getMapX;
            mapY = getMapY;
            setImage("stone.png");
        }
    }
}
