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
    //private int coor[] = new int[4];
    ArrayList<int[]> rooms = new ArrayList<int[]>(); // 0=w, 1=h, 2=x, 3=y
    ArrayList<int[]> doors = new ArrayList<int[]>();
    private boolean noSpace = false;
    private boolean doneOnce = false;
    public String[][][] generateMap(){
        generateBorder();
        generateStartRoom();
        generateEndRoom();
        generateRoom(5);
        //         addDoors(0);
        //         addDoors(1);
        //         addDoors(2);
        //         addDoors(3);
        //         addDoors(4);
        floodGrid(5, 5);
        //for(int i = 0; i < rooms.size(); i++){
        //addDoors(i);
        //}
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
        int coor[] = new int[4];
        coor[0] = getRandWidth();
        coor[1] = getRandHeight();
        coor[2] = getRandX(coor[0]);
        coor[3] = getRandY(coor[1]);
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
            int listN[] = new int[4];
            int amtDoors = getRandDoor();
            listN = getRandList();
            for(int i = 0; i < amtDoors; i++){
                if(listN[i]==1){
                    int d1 = coor[2]+(coor[0]/2);
                    array[d1][coor[3]][0] = null;
                    array[d1][coor[3]][0] = "door";
                    array[d1+1][coor[3]][0] = null;
                    array[d1+1][coor[3]][0] = "door";
                }
                else if(listN[i]==2){
                    int d2 = coor[3]+(coor[1]/2);
                    array[coor[2]][d2][0] = null;
                    array[coor[2]][d2][0] = "door";
                    array[coor[2]][d2+1][0] = null;
                    array[coor[2]][d2+1][0] = "door";
                }
                else if(listN[i]==3){
                    int d3 = coor[2]+(coor[0]/2);
                    array[d3][coor[3]+coor[1]+1][0] = null;
                    array[d3][coor[3]+coor[1]+1][0] = "door";
                    array[d3+1][coor[3]+coor[1]+1][0] = null;
                    array[d3+1][coor[3]+coor[1]+1][0] = "door";
                }
                else if(listN[i]==4){
                    int d4 = coor[3]+(coor[1]/2);
                    array[coor[2]+coor[0]][d4][0] = null;
                    array[coor[2]+coor[0]][d4][0] = "door";
                    array[coor[2]+coor[0]][d4+1][0] = null;
                    array[coor[2]+coor[0]][d4+1][0] = "door";
                }
            }
            doneOnce = true;
            rooms.add(coor);
        }
    }

    public void generateEndRoom(){
        int coor[] = new int[4];
        do{
            coor[0] = getRandWidth();
            coor[1] = getRandHeight();
            coor[2] = getRandX(coor[0]);
            coor[3] = getRandY(coor[1]);
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
                int listN[] = new int[4];
                int amtDoors = getRandDoor();
                listN = getRandList();
                for(int i = 0; i < amtDoors; i++){
                    if(listN[i]==1){
                        int d1 = coor[2]+(coor[0]/2);
                        array[d1][coor[3]][0] = null;
                        array[d1][coor[3]][0] = "door";
                        array[d1+1][coor[3]][0] = null;
                        array[d1+1][coor[3]][0] = "door";
                    }
                    else if(listN[i]==2){
                        int d2 = coor[3]+(coor[1]/2);
                        array[coor[2]][d2][0] = null;
                        array[coor[2]][d2][0] = "door";
                        array[coor[2]][d2+1][0] = null;
                        array[coor[2]][d2+1][0] = "door";
                    }
                    else if(listN[i]==3){
                        int d3 = coor[2]+(coor[0]/2);
                        array[d3][coor[3]+coor[1]+1][0] = null;
                        array[d3][coor[3]+coor[1]+1][0] = "door";
                        array[d3+1][coor[3]+coor[1]+1][0] = null;
                        array[d3+1][coor[3]+coor[1]+1][0] = "door";
                    }
                    else if(listN[i]==4){
                        int d4 = coor[3]+(coor[1]/2);
                        array[coor[2]+coor[0]][d4][0] = null;
                        array[coor[2]+coor[0]][d4][0] = "door";
                        array[coor[2]+coor[0]][d4+1][0] = null;
                        array[coor[2]+coor[0]][d4+1][0] = "door";
                    }
                }
                doneOnce = true;
                rooms.add(coor);
            }
        }
        while(noSpace == false && doneOnce == false);
    }

    public void generateRoom(int amtOfRooms){
        int coor[] = new int[4];
        int r = amtOfRooms+1;
        for(int a = 0; a< r; a++){
            do{
                coor[0] = getRandWidth();
                coor[1] = getRandHeight();
                coor[2] = getRandX(coor[0]);
                coor[3] = getRandY(coor[1]);
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
                    int listN[] = new int[4];
                    int amtDoors = getRandDoor();
                    listN = getRandList();
                    for(int i = 0; i < amtDoors; i++){
                        if(listN[i]==1){
                            int d1 = coor[2]+(coor[0]/2);
                            array[d1][coor[3]][0] = null;
                            array[d1][coor[3]][0] = "door";
                            array[d1+1][coor[3]][0] = null;
                            array[d1+1][coor[3]][0] = "door";
                        }
                        else if(listN[i]==2){
                            int d2 = coor[3]+(coor[1]/2);
                            array[coor[2]][d2][0] = null;
                            array[coor[2]][d2][0] = "door";
                            array[coor[2]][d2+1][0] = null;
                            array[coor[2]][d2+1][0] = "door";
                        }
                        else if(listN[i]==3){
                            int d3 = coor[2]+(coor[0]/2);
                            array[d3][coor[3]+coor[1]+1][0] = null;
                            array[d3][coor[3]+coor[1]+1][0] = "door";
                            array[d3+1][coor[3]+coor[1]+1][0] = null;
                            array[d3+1][coor[3]+coor[1]+1][0] = "door";
                        }
                        else if(listN[i]==4){
                            int d4 = coor[3]+(coor[1]/2);
                            array[coor[2]+coor[0]][d4][0] = null;
                            array[coor[2]+coor[0]][d4][0] = "door";
                            array[coor[2]+coor[0]][d4+1][0] = null;
                            array[coor[2]+coor[0]][d4+1][0] = "door";
                        }
                    }
                    doneOnce = true;
                    rooms.add(coor);
                }
            }
            while(noSpace == false && doneOnce == false);
        }
    }

    public void returnRooms(int a){
        int w, h, x, y;
        //for(int a =0; a< rooms.size();a++){
        w = rooms.get(a)[0];
        h = rooms.get(a)[1];
        x = rooms.get(a)[2];
        y = rooms.get(a)[3];
        System.out.println(w);
        System.out.println(h);
        System.out.println(x);
        System.out.println(y);
        //}
    }

    public void addDoors(int a){
        int amtDoors, w, h, x, y;
        int listN[] = new int[4];
        //for(int a =0; a< rooms.size();a++){
        //System.out.println(rooms.size());
        amtDoors = getRandDoor();
        //System.out.println(amtDoors);
        //System.out.println(a);
        //System.out.println(" ");
        w = rooms.get(a)[0];
        h = rooms.get(a)[1];
        x = rooms.get(a)[2];
        y = rooms.get(a)[3];
        //System.out.println(w);
        //System.out.println(h);
        //System.out.println(x);
        //System.out.println(y);
        //System.out.println(" ");
        listN = getRandList();
        for(int i = 0; i < amtDoors; i++){
            if(listN[i]==1){
                int d1 = x+(w/2);
                array[d1][y][0] = null;
                array[d1][y][0] = "door";
                array[d1+1][y][0] = null;
                array[d1+1][y][0] = "door";
            }
            else if(listN[i]==2){
                int d2 = y+(h/2);
                array[x][d2][0] = null;
                array[x][d2][0] = "door";
                array[x][d2+1][0] = null;
                array[x][d2+1][0] = "door";
            }
            else if(listN[i]==3){
                int d3 = x+(w/2);
                array[d3][y+h+1][0] = null;
                array[d3][y+h+1][0] = "door";
                array[d3+1][y+h+1][0] = null;
                array[d3+1][y+h+1][0] = "door";
            }
            else if(listN[i]==4){
                int d4 = y+(h/2);
                array[x+w][d4][0] = null;
                array[x+w][d4][0] = "door";
                array[x+w][d4+1][0] = null;
                array[x+w][d4+1][0] = "door";
            }
        }
        //}
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

    public void floodGrid (int x, int y){
        // base case: array already has something in it
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

    public int getRandDoor(){
        return Greenfoot.getRandomNumber(4)+1;
    }

    public int getStartingCoorX(){
        return rooms.get(0)[2]+(rooms.get(0)[0]/2);
    }

    public int getStartingCoorY(){
        return rooms.get(0)[3]+(rooms.get(0)[1]/2);
    }

    public int[] getRandList(){
        int list[] = new int[4];
        list[0] = Greenfoot.getRandomNumber(4)+1;
        do{
            list[1] = Greenfoot.getRandomNumber(4)+1;
        }while(list[1] == list[0]);
        do{
            list[2] = Greenfoot.getRandomNumber(4)+1;
        }while(list[2] == list[0] && list[2] == list[1]);
        do{
            list[3] = Greenfoot.getRandomNumber(4)+1;
        }while(list[3] == list[0] && list[3] == list[1] && list[3] == list[2]);
        return list;
    }

    public int getRandNum(int n){
        return Greenfoot.getRandomNumber(n)+1;
    }

    public int getRandNum(int s, int e){
        return Greenfoot.getRandomNumber(e-s)+s;
    }
}
