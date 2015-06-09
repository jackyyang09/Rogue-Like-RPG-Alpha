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
    boolean enemyTurn = false;
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
        setImage("slime.png");
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMove = 2;
        move = 0;
        ID = IDnum;
        //grid2 = ((ScrollingMap)getWorld()).getGrid();
        mapX = getMapX;
        mapY = getMapY;
    }

    /**
     * Act Method
     */
    public void act(){
        if(enemyTurn && move > 0){
            move--;
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

            }
            else if(moveTo.size() > 1 && moveTo.size() < 16){
                moveTo.removeFirst();
                mapY = moveTo.getFirst().x;
                mapX = moveTo.getFirst().y;
            }
            convertToPixel();
            ((ScrollingMap)getWorld()).update();
        }
        if(move == 0){
            enemyTurn = false;
        }
    }

    public void setEnemyTurn(boolean turn){
        if(turn){
            enemyTurn = true;
            move = baseMove;
        } else {
            enemyTurn = false;
            move = 0;
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
