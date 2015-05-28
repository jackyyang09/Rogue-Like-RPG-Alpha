import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
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
    int randFloor;
    public Tile(int getMapX, int getMapY, int tileCode)
    {
        Random rnd = new Random();
        if(tileCode == 1){
            randFloor = rnd.nextInt(2) + 1;
            mapX = getMapX;
            mapY = getMapY;
            if(randFloor == 1){
                setImage("floorTile.png");
            } else {
                setImage("floorTile2.png");
            }
        } else if(tileCode == 2){
            mapX = getMapX;
            mapY = getMapY;
            setImage("dirt.jpg");
        }
    }
}
