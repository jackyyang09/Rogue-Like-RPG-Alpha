import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Generate is a class with procedural programming to create the World for the RPG game
 * 
 * @author Kris Leung
 * @version June 2015 v2.0
 */
public class Generate
{(
    String array[][][] = new String[58][56][4]; //3D array of the grid (56x58x4)
    ArrayList<int[]> rooms = new ArrayList<int[]>(); //ArrayList of the different rooms (0=width, 1=h, 2=x, 3=y)
    ArrayList<int[]> doors = new ArrayList<int[]>(); //ArrayList of the doors (0=x, 1=y)
    private boolean noSpace = false;
    private boolean doneOnce = false;
    
    /*
    *   Main class to create a grid
    *
    *   @return String[][][]    returns the full grid of the created map
    */
    public String[][][] generateMap(){
        generateBorder();
        generateStartRoom();
        generateEndRoom();
        generatePortals();
        generateRoom(10);
        floodGrid();
        checkDoors();
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
            room(coor);
            //System.out.println(coor[0] +" "+coor[1] +" "+coor[2] +" "+coor[3]);
            noSpace = true;
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
                room(coor);
                doneOnce = true;
                noSpace = true;
                rooms.add(coor);
            }
        }
        while(doneOnce == false);
        doneOnce = false;
    }
    
    public void generatePortals(){
        int sX = getStartingCoorX();
        int sY = getStartingCoorY();
        array[sX][sY][0] = "sPortal";
        int eX = getEndingCoorX();
        int eY = getEndingCoorY();
        array[eX][eY][0] = "ePortal";
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
                    room(coor);
                    spawnEnemy(coor, 1);
                    doneOnce = true;
                    noSpace = true;
                    rooms.add(coor);
                }
            }
            while(doneOnce == false);
            doneOnce = false;
        }
    }

    public void room(int[] coor){
        for(int i = coor[2]; i < coor[2] + coor[0] + 1; i++){
            array[i][coor[3]][0] = "wall";
            array[i][coor[3]+coor[1]+1][0] = "wall";
        }
        for(int j = coor[3]; j < coor[3] + coor[1] + 1; j++){
            array[coor[2]][j][0] = "wall";
            array[coor[2]+coor[0]+1][j][0] = "wall";
        }
        array[coor[2] + coor[0] + 1][coor[3] + coor[1] + 1][0] = "wall";
        for(int k = coor[2] + 1; k < coor[2] + coor[0]+1; k++){
            for(int l = coor[3] + 1; l < coor[3] + coor[1]+1; l++){
                array[k][l][0] = "floorTile";
            }
        }
                int d1 = coor[2]+(coor[0]/2);
                array[d1][coor[3]][0] = null;
                array[d1][coor[3]][0] = "door";
                putDoors(d1, coor[3]);
                array[d1+1][coor[3]][0] = null;
                array[d1+1][coor[3]][0] = "door";
                putDoors(d1+1, coor[3]);

                int d2 = coor[3]+(coor[1]/2);
                array[coor[2]][d2][0] = null;
                array[coor[2]][d2][0] = "door";
                putDoors(coor[2], d2);
                array[coor[2]][d2+1][0] = null;
                array[coor[2]][d2+1][0] = "door";
                putDoors(coor[2], d2+1);
                
                int d3 = coor[2]+(coor[0]/2);
                array[d3][coor[3]+coor[1]+1][0] = null;
                array[d3][coor[3]+coor[1]+1][0] = "door";
                putDoors(d3, coor[3]+coor[1]+1);
                array[d3+1][coor[3]+coor[1]+1][0] = null;
                array[d3+1][coor[3]+coor[1]+1][0] = "door";
                putDoors(d3+1, coor[3]+coor[1]+1);

                int d4 = coor[3]+(coor[1]/2);
                array[coor[2]+coor[0]+1][d4][0] = null;
                array[coor[2]+coor[0]+1][d4][0] = "door";
                putDoors(coor[2]+coor[0]+1, d4);
                array[coor[2]+coor[0]+1][d4+1][0] = null;
                array[coor[2]+coor[0]+1][d4+1][0] = "door";
                putDoors(coor[2]+coor[0]+1, d4+1);
    }

    public void putDoors(int x, int y){
        int dc[] = new int[2];
        dc[0] = x;
        dc[1] = y;
        doors.add(dc);
    }

    public void checkDoors(){
        int amtWalls;
        boolean r, l, u, d;
        for(int[] ds : doors){
            //System.out.println(ds[0] + " " + ds[1]);
            if(ds[0] == 5 || ds[0] == 52 || ds[1] == 5 || ds[1] == 51){
                //System.out.println("wall change");
                array[ds[0]][ds[1]][0] = "wall";
            }
            else{
                amtWalls = 0;
                r = false;
                l = false;
                u = false;
                d = false;
                if(array[ds[0]+1][ds[1]][0] == "wall"){
                    //System.out.println("right");
                    amtWalls++;
                    r = true;
                }
                if(array[ds[0]-1][ds[1]][0] == "wall"){
                    //System.out.println("left");
                    amtWalls++;
                    l = true;
                }
                if(array[ds[0]][ds[1]+1][0] == "wall"){
                    //System.out.println("up");
                    amtWalls++;
                    u = true;
                }
                if(array[ds[0]][ds[1]-1][0] == "wall"){
                    //System.out.println("down");
                    amtWalls++;
                    d = true;
                }
                if(amtWalls >= 2){
                    //System.out.println("blocked");
                    //                     if(r == true && l != true){
                    //                         array[ds[0]+1][ds[1]][0] = "door";
                    //                     }
                    //                     if(l == true && r != true){
                    //                         array[ds[0]-1][ds[1]][0] = "door";
                    //                     }
                    //                     if(u == true && d != true){
                    //                         array[ds[0]][ds[1]+1][0] = "door";
                    //                     }
                    //                     if(d == true && u != true){
                    //                         array[ds[0]][ds[1]-1][0] = "door";
                    //                     }
                    //array[ds[0]][ds[1]][0] = null;
                    array[ds[0]][ds[1]][0] = "wall";
                }
            }
        }
    }

    public void spawnEnemy (int[] room, int maxEnemies){
        int times = Greenfoot.getRandomNumber(maxEnemies)+1;
        for(int i = 0; i < times; i++){
            int xCor = getRandNum(room[2]+1, room[2]+room[0]+1);
            int yCor = getRandNum(room[3]+1, room[3]+room[1]+1);
            array[xCor][yCor][2] = "enemy";
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

    public void floodGrid(){
        for(int i = 5; i<54; i++){
            for(int j = 5; j<53;j++){
                if(array[i][j][0] == null){
                    array[i][j][0] = "floorTile";
                }
            }
        }
    }

    public String[][][] returnMap(){
        return array;
    }

    public int getRandWidth(){
        return Greenfoot.getRandomNumber(7) +6;
    }

    public int getRandHeight(){
        return Greenfoot.getRandomNumber(7) +5;
    }

    /*
     * 53
     */
    public int getRandX(int w){
        return Greenfoot.getRandomNumber(47-w)+5;
    }

    /*
     * 52
     */
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
    
    public int getEndingCoorX(){
        return rooms.get(1)[2]+(rooms.get(1)[0]/2);
    }
    
    public int getEndingCoorY(){
        return rooms.get(1)[3]+(rooms.get(1)[1]/2);
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
