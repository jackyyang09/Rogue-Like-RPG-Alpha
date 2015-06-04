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
    //boolean[][] grid2;

    public Enemy(int getMapX, int getMapY){
        setImage("chest.png");
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMove = 2;
        //grid2 = ((ScrollingMap)getWorld()).getGrid();
        mapX = getMapX;
        mapY = getMapY;
    }

    public void act(){
        if(Greenfoot.isKeyDown("c")){
            convertToTile();
            Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
            boolean[][] grid2 = ((ScrollingMap)getWorld()).getGrid();
//             for(int i = 0; i < 58; i++){
//                 for(int j = 0; j < 56; j++){
//                     try{
//                         if(!((Tile)grid[i][j][0]).isAWall){
//                             System.out.print(0);
//                         } else {
//                             System.out.print(1);
//                         }
//                     } catch (Exception e){
// //                         System.out.print(0);
//                     }
//                 }
//                 System.out.println("");
//             }

            //         for(int i = 0; i < 58; i++){
            //             for(int j = 0; j < 56; j++){
            //                 if(grid2[i][j]){
            //                     System.out.print(0);
            //                 }
            //                 else{
            //                     System.out.print(1);
            //                 }
            //             }
            //             System.out.println();
            //         }

            for(int i = 0; i < 58; i++){
                for(int j = 0; j < 56; j++){
                    if(grid[i][j][1] != null){
                        playerX = i;//((Player)grid[i][j][1]).getMapX();
                        playerY = j;//((Player)grid[i][j][1]).getMapY();
                        System.out.println(playerX + "p" + playerY);
                        System.out.println(grid2[mapX][mapY]);
                        //convertToTilePlayer();
                    }
                }
            }
            //int playerX = grid[0][0][2].getMapX();
            //int playerY = grid[0][0][2].getMapY();
            System.out.println(mapX + "x" + mapY);
            moveTo = bfs.BFSPathFinding(mapY, mapX, playerY, playerX, grid2);
            bfs.reset();
            //System.out.println(moveTo);
            //middle = moveTo.indexOf('x');
            //mapX = Integer.parseInt(moveTo.substring(0,middle));
            //mapY = Integer.parseInt(moveTo.substring(middle + 1));
            if(moveTo.size() > 1){
                moveTo.removeFirst();
                mapY = moveTo.getFirst().x;
                mapX = moveTo.getFirst().y;
            }
            convertToPixel();
            ((ScrollingMap)getWorld()).update();
        }
    }
}
