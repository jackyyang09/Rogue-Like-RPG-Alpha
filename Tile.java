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
    boolean isWall;
    
    public Tile(int getMapX, int getMapY, int tileCode)
    {
        if(tileCode == 1){
            setImage("floorTile.png");
            isWall = false;
        }
        if(tileCode == 2){
            setImage("floorTile2.png");
            isWall = false;
        }
        if(tileCode == 3){
            setImage("wall.png");
            isWall = true;
        }
        mapX = getMapX;
        mapY = getMapY;
    }
}
