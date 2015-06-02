import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Dirt here.
 * 
 * @author Ryan Huang
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    int mapX;
    int mapY;
    boolean isAWall;
    
    public Tile(int getMapX, int getMapY, int tileCode)
    {
        if(tileCode == 1){
            setImage("floorTile.png");
            isAWall = false;
        }
        if(tileCode == 2){
            setImage("floorTile2.png");
            isAWall = false;
        }
        if(tileCode == 3){
            setImage("wall.png");
            isAWall = true;
        }
        mapX = getMapX;
        mapY = getMapY;
    }
}
