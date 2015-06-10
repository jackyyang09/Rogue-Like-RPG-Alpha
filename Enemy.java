import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.awt.Point;

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
    LinkedList<Point> moveTo;
    int tempX;
    int tempY;
    int ID;
    //boolean[][] grid2;

    /**
     * Constructor for Enemy
     * <p>
     * List of tasks:
     * -Sets image
     * -Sets Base Stats
     * -Sets a UNIQUE ID number
     * -Sets the desired 
     * 
     * @param getMapX
     * @param getMapY
     * @param IDnum
     */
    public Enemy(int getMapX, int getMapY, int IDnum){
        setImage("chest.png");
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMove = 2;
        ID = IDnum;
        //grid2 = ((ScrollingMap)getWorld()).getGrid();
        mapX = getMapX;
        mapY = getMapY;
    }

    /**
     * Act Method
     */
    public void act(){
        if(Greenfoot.isKeyDown("c")){
            convertToTile();
            Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
            boolean[][] grid2 = ((ScrollingMap)getWorld()).getGrid();
            for(int i = 0; i < 58; i++){
                for(int j = 0; j < 56; j++){
                    if(grid[i][j][1] != null){
                        playerX = i;
                        playerY = j;
                        System.out.println(playerX + "p" + playerY);
                        System.out.println(grid2[mapX][mapY]);

                    }
                }
            }
            System.out.println(mapX + "x" + mapY);
            moveTo = bfs.BFSPathFinding(mapY, mapX, playerY, playerX, grid2);
            bfs.reset();
            if(moveTo.size() == 2){
                moveTo.removeFirst();
                attack((Player)grid[moveTo.getFirst().y][moveTo.getFirst().x][1]);
            }
            else if(moveTo.size() > 1 && moveTo.size() < 17){
                moveTo.removeFirst();   
                tempY = moveTo.getFirst().x;
                tempX = moveTo.getFirst().y;
                ((ScrollingMap)getWorld()).field[tempX][tempY][2] = this;
                ((ScrollingMap)getWorld()).field[mapX][mapY][2] = null;
                mapY = tempY;
                mapX = tempX;
            }
            convertToPixel();
            ((ScrollingMap)getWorld()).update();
        }
    }

    /**
     * Returns the ID of this specific enemy
     * 
     * @return ID specific ID number for this enemy
     */
    public int getID(){
        return this.ID;
    }
    
}
