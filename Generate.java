import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Generate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Generate
{
    String array[][][] = new String[58][56][3];
    private int widthST, heightST, xPlaceST, yPlaceST;
    private int widthED, heightED, xPlaceED, yPlaceED;
    private int coor[] = new int[4];
    ArrayList<int[]> rooms = new ArrayList<int[]>();
    private boolean noSpace = false;
    private boolean doneOnce = false;
    public String[][][] generateMap(){
        generateBorder();
        generateStartRoom();
        generateEndRoom();
        for(int i = 1; i<5; i++){
            generateRoom(i);
        }
        generateRoom(1);
        
        floodGrid(5, 5);
        return array;
    }

    public void generateBorder(){
        // 4x3, 4x52, 53x3, 53x52
        for(int i = 4; i < 52; i++){
            array[4][i][0] = "wall";
            array[53][i][0] = "wall";
        }
        for(int j = 4; j < 54; j++){
            array[j][4][0] = "wall";
            array[j][52][0] = "wall";
        }
    }

    public void generateStartRoom(){
        widthST = getRandWidth();
        heightST = getRandHeight();
        xPlaceST = getRandX(widthST);
        yPlaceST = getRandY(heightST);
        for(int i = xPlaceST; i < xPlaceST + widthST + 1; i++){
            array[i][yPlaceST][0] = "wall";
            array[i][yPlaceST+heightST+1][0] = "wall";
        }
        for(int j = yPlaceST; j < yPlaceST + heightST + 1; j++){
            array[xPlaceST][j][0] = "wall";
            array[xPlaceST+widthST][j][0] = "wall";
        }
        for(int k = xPlaceST + 1; k < xPlaceST + widthST; k++){
            for(int l = yPlaceST + 1; l < yPlaceST + heightST+1; l++){
                array[k][l][0] = "floorTile";
            }
        }
        System.out.println(" ");
    }

    public void generateEndRoom(){
        do{
            widthED = getRandWidth();
            heightED = getRandHeight();
            xPlaceED = getRandX(widthED);
            yPlaceED = getRandY(heightED);
            System.out.println(widthED-1);
            System.out.println(heightED);
            System.out.println(xPlaceED);
            System.out.println(yPlaceED);
            noSpace = checkSpaces(widthED, heightED, xPlaceED, yPlaceED);
            if(noSpace == false){
                for(int i = xPlaceED; i < xPlaceED + widthED + 1; i++){
                    array[i][yPlaceED][0] = "wall";
                    array[i][yPlaceED+heightED+1][0] = "wall";
                }
                for(int j = yPlaceED; j < yPlaceED + heightED + 1; j++){
                    array[xPlaceED][j][0] = "wall";
                    array[xPlaceED+widthED][j][0] = "wall";
                }
                for(int k = xPlaceED + 1; k < xPlaceED + widthED; k++){
                    for(int l = yPlaceED + 1; l < yPlaceED + heightED+1; l++){
                        array[k][l][0] = "floorTile";
                    }
                }
                doneOnce = true;
                System.out.println(" ");
            }
        }
        while(noSpace == false && doneOnce == false);
    }

    public void generateRoom(int rmNum){
        do{
            coor[0] = getRandWidth();
            coor[1] = getRandHeight();
            coor[2] = getRandX(coor[0]);
            coor[3] = getRandY(coor[1]);
            System.out.println(coor[0]-1);
            System.out.println(coor[1]);
            System.out.println(coor[2]);
            System.out.println(coor[3]);
            noSpace = checkSpaces(coor[0], coor[1], coor[2], coor[3]);
            if(noSpace == false){
                for(int i = coor[2]; i < coor[2] + coor[0] + 1; i++){
                    array[i][coor[3]][0] = "wall";
                    array[i][coor[3]+coor[1]+1][0] = "wall";
                }
                for(int j = coor[3]; j < coor[3] + coor[1] + 1; j++){
                    array[coor[2]][j][0] = "wall";
                    array[coor[2]+coor[0]][j][0] = "wall";
                }
                for(int k = coor[2] + 1; k < coor[2] + coor[0]; k++){
                    for(int l = coor[3] + 1; l < coor[3] + coor[1]+1; l++){
                        array[k][l][0] = "floorTile";
                    }
                }
                doneOnce = true;
                rooms.add(coor);
                System.out.println(" ");
            }
        }
        while(noSpace == false && doneOnce == false);
    }

    public boolean checkSpaces (int w, int h, int x, int y){
        for(int a = x; a < x + w + 1; a++){
            for(int b = y; b < y + h + 1; b++){
                if(array[a][b][0] != null){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void floodGrid (int x, int y)
    {
        // base case: char is already a flooder or a bounder
        if (array[x][y][0] != null){
            return;
        }

        array[x][y][0] = "floorTile";
        // move up:
        if (y >= 1)
        {
            floodGrid(x, y-1); 
        }
        if (y < array.length - 1)
        {
            floodGrid(x, y+1); 
        }
        if (x >= 1)
        {
            floodGrid(x - 1, y); 
        }
        if (x < array.length - 1)
        {
            floodGrid(x + 1, y);    
        }
    }

    public String[][][] returnMap(){
        return array;
    }

    public int getRandWidth(){
        return Greenfoot.getRandomNumber(7) +4;
    }

    public int getRandHeight(){
        return Greenfoot.getRandomNumber(7) +3;
    }

    public int getRandX(int w){
        return Greenfoot.getRandomNumber(47-w)+5;
    }

    public int getRandY(int h){
        return Greenfoot.getRandomNumber(46-h) +5;
    }

}
