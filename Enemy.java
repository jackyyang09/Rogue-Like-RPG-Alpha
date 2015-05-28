import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Mobs
{
    BFSPathFinding bfs = new BFSPathFinding();
    int mapX;
    int mapY;
    public Enemy(int getMapX, int getMapY){
        setImage("chest.png");
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMov = 2;
        mapX = getMapX;
        mapY = getMapY;
    }

    public void act(){
        convertToPixel();
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        //int playerX = grid[0][0][2].getMapX();
        //int playerY = grid[0][0][2].getMapY();
        String moveTo = bfs.main(mapX, mapY, 3, 4);
        int middle = moveTo.indexOf('x');
        mapX = Integer.parseInt(moveTo.substring(0,middle));
        mapY = Integer.parseInt(moveTo.substring(middle + 1));
        convertToTile();
        ((ScrollingMap)getWorld()).update();
    }

    public void convertToPixel(){
        mapX = (mapX - 43) / 86;
        mapY = (mapY - 43) / 86;
    }
    
    public void convertToTile(){
        mapX = (mapX * 86) + 43;
        mapY = (mapY * 86) + 43;
    }
}
