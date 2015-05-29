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
    int middle;
    int playerX;
    int playerY;
    String moveTo;

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
        convertToTile();
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        for(int i = 0; i < 58; i++){
            for(int j = 0; j < 56; j++){
                if(grid[i][j][1] != null){
                    playerX = i;//((Player)grid[i][j][1]).getMapX();
                    playerY = j;//((Player)grid[i][j][1]).getMapY();

                    //convertToTilePlayer();
                }
            }
        }
        //int playerX = grid[0][0][2].getMapX();
        //int playerY = grid[0][0][2].getMapY();
        moveTo = bfs.BFSPathFinding(mapX, mapY, playerX, playerY);
        bfs.reset();
        middle = moveTo.indexOf('x');
        mapX = Integer.parseInt(moveTo.substring(0,middle));
        mapY = Integer.parseInt(moveTo.substring(middle + 1));
        convertToPixel();
        ((ScrollingMap)getWorld()).update();
    }
}
