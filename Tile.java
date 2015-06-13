import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * tiles of the map
 * 
 * @author Ryan Huang
 * @version (June 12 2015)
 */
public class Tile extends Actor
{
    int mapX;
    int mapY;
    boolean isAWall;
    
    /**
     * constructor for tile
     * 
     * @param getMapX x coordinate of tile
     * @param getMapY y coordinate of tile 
     * @param tileCode type of tile
     */
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
        if(tileCode == 4){
            setImage("portalEntrance.png");
            isAWall = false;
        }
        if(tileCode == 5){
            setImage("portalExit.png");
            isAWall = false;
        }
        mapX = getMapX;
        mapY = getMapY;
    }
}
