import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Generate is a class with procedural programming to create the World for the RPG game
 * 
 * @author Kris Leung
 * @version June 2015 v2.0
 */
public class Generate
{
    private String array[][][] = new String[58][56][6]; //3D array of the grid (56x58x4)
    private ArrayList<int[]> rooms = new ArrayList<int[]>(); //ArrayList of the different rooms (0=w, 1=h, 2=x, 3=y)
    private ArrayList<int[]> doors = new ArrayList<int[]>(); //ArrayList of the doors (0=x, 1=y)
    private boolean noSpace = false;
    private boolean doneOnce = false;
    private int maxEnemies = 0;
    private int floor = 1;
    private boolean genOnce = false;

    /*
     *   Main class to create a grid
     *
     *   @return String[][][]    returns the full grid of the created map
     */
    public String[][][] generateMap(int fl){
        if(genOnce == true){
            rooms.removeAll(rooms);
            doors.removeAll(doors);
            floor++;
            for(int i = 4; i < 54; i++){
                for(int j = 4; j < 52; j++){
                    for(int k = 2; k < 6 ;k++){
                        array[i][j][k] = null;
                    }
                    array[i][j][0] = "floorTile";
                }
            }
            noSpace = false;
            doneOnce = false;
            Greenfoot.stop();
        }
        floor = fl;
        generateBorder();
        generateStartRoom();
        generateEndRoom();
        generatePortals();
        generateRoom(10);
        floodGrid();
        checkDoors();
        genOnce = true;
        return array;
    }

    /*
    * Creates the border of walls to prevent player from escaping the world
    */
    private void generateBorder(){
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

    /*
    * Creates the player start room
    */
    private void generateStartRoom(){
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
        noSpace = false;
    }

    /*
    * Creates the player end room
    */
    private void generateEndRoom(){
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
            noSpace = false;
        }
        while(doneOnce == false);
        doneOnce = false;
    }

    /*
    * Create a starting portal inside the starting room and the ending portal in the ending room
    */
    private void generatePortals(){
        int sX = getStartingCoorX();
        int sY = getStartingCoorY();
        array[sX][sY][0] = "sPortal";
        int eX = getEndingCoorX();
        int eY = getEndingCoorY();
        array[eX][eY][0] = "ePortal";
    }

    /*
    * Create a number of rooms
    * @param amtOfRooms   Number of rooms being created
    */
    private void generateRoom(int amtOfRooms){
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
                    spawnEnemy(coor, 4, floor);
                    spawnChest(coor);
                    doneOnce = true;
                    noSpace = true;
                    rooms.add(coor);
                }
                noSpace = false;
            }
            while(doneOnce == false);
            doneOnce = false;
        }
    }

    /*
    * Creates the actual room with coordinates
    * @param coor array of coordinates (0=w, 1=h, 2=x, 3=y)
    */
    private void room(int[] coor){
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
        //Create first door
        int d1 = coor[2]+(coor[0]/2);
        array[d1][coor[3]][0] = null;
        array[d1][coor[3]][0] = "door";
        putDoors(d1, coor[3]);
        array[d1+1][coor[3]][0] = null;
        array[d1+1][coor[3]][0] = "door";
        putDoors(d1+1, coor[3]);
        //Create second door
        int d2 = coor[3]+(coor[1]/2);
        array[coor[2]][d2][0] = null;
        array[coor[2]][d2][0] = "door";
        putDoors(coor[2], d2);
        array[coor[2]][d2+1][0] = null;
        array[coor[2]][d2+1][0] = "door";
        putDoors(coor[2], d2+1);
        //Create third door
        int d3 = coor[2]+(coor[0]/2);
        array[d3][coor[3]+coor[1]+1][0] = null;
        array[d3][coor[3]+coor[1]+1][0] = "door";
        putDoors(d3, coor[3]+coor[1]+1);
        array[d3+1][coor[3]+coor[1]+1][0] = null;
        array[d3+1][coor[3]+coor[1]+1][0] = "door";
        putDoors(d3+1, coor[3]+coor[1]+1);
        //Create fourth door
        int d4 = coor[3]+(coor[1]/2);
        array[coor[2]+coor[0]+1][d4][0] = null;
        array[coor[2]+coor[0]+1][d4][0] = "door";
        putDoors(coor[2]+coor[0]+1, d4);
        array[coor[2]+coor[0]+1][d4+1][0] = null;
        array[coor[2]+coor[0]+1][d4+1][0] = "door";
        putDoors(coor[2]+coor[0]+1, d4+1);
    }

    /*
    * Add doors with coordinates into array list
    * @param x   x coordinate of door
    * @param y   y coordinate of door
    */
    private void putDoors(int x, int y){
        int dc[] = new int[2];
        dc[0] = x;
        dc[1] = y;
        doors.add(dc);
    }

    /*
    * Check all the doors if they are accessible
    */
    private void checkDoors(){
        int amtWalls;
        boolean r, l, u, d;
        for(int[] ds : doors){
            if(ds[0] == 5 || ds[0] == 52 || ds[1] == 5 || ds[1] == 51){
                array[ds[0]][ds[1]][0] = "wall";
            }
            else{
                amtWalls = 0;
                r = false;
                l = false;
                u = false;
                d = false;
                if(array[ds[0]+1][ds[1]][0] == "wall"){
                    amtWalls++;
                    r = true;
                }
                if(array[ds[0]-1][ds[1]][0] == "wall"){
                    amtWalls++;
                    l = true;
                }
                if(array[ds[0]][ds[1]+1][0] == "wall"){
                    amtWalls++;
                    u = true;
                }
                if(array[ds[0]][ds[1]-1][0] == "wall"){
                    amtWalls++;
                    d = true;
                }
                if(amtWalls >= 2){
                    array[ds[0]][ds[1]][0] = "wall";
                }
            }
        }
    }

    /*
    * Spawn enemy in room with coordinates
    * @param room   array of coordinates of the room (0=w, 1=h, 2=x, 3=y)
    * @param ePerR   max enemies per room
    * @param floor   floor number
    */
    private void spawnEnemy (int[] room, int ePerR, int floor){
        int amt = Greenfoot.getRandomNumber(ePerR);
        for(int i = 0; i < amt; i++){
            if(maxEnemies > 0){
                do{
                    int xCor = getRandNum(room[2]+1, room[2]+room[0]+1);
                    int yCor = getRandNum(room[3]+1, room[3]+room[1]+1);
                    if(array[xCor][yCor][2] == null){
                        int enemyType = Greenfoot.getRandomNumber(5)+1;
                        array[xCor][yCor][2] = pickEnemy(floor);
                        maxEnemies--;
                        doneOnce = true;
                    }
                }while(doneOnce == false);
                doneOnce = false;
            }
            else{
                break;
            }
        }
    }

    /*
    * Spawn a chest in room with coordinates
    * @param room   array of coordinates of the room (0=w, 1=h, 2=x, 3=y)
    */
    private void spawnChest(int[] room){
        int xCor = room[2]+(room[0]/2)+1;
        int yCor = room[3]+(room[1]/2)+1;
        array[xCor][yCor][5] = "chest";
    }

    /*
    * Checks coordinates if its empty
    * @param (w, h, x, y)   coordinates
    */
    private boolean checkSpaces (int w, int h, int x, int y){
        for(int a = x; a < x + w + 1; a++){
            for(int b = y; b < y + h + 1; b++){
                if(array[a][b][0] != null){
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * Insert floor tile in every empty space
    */
    private void floodGrid(){
        for(int i = 5; i<54; i++){
            for(int j = 5; j<53;j++){
                if(array[i][j][0] == null){
                    array[i][j][0] = "floorTile";
                }
            }
        }
    }

    /*
    * Returns the entire grid
    * @param String[][][] entire grid
    */
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

    public void setMaxEnemies(int max){
        maxEnemies = max;
    }
    
    /*
    * Chooses enemy to create based on level of floor
    * @param floor   floor number
    */
    public String pickEnemy(int floor){
        int num = Greenfoot.getRandomNumber(100)+1;
        if(floor>=1 && floor<=5){
            if(num < 20){return "enemy2";}
            else{return "enemy1";}
        }
        else if(floor>=6 && floor<=10){
            if(num < 20){return "enemy3";}
            else if(num < 40){return "enemy2";}
            else{return "enemy1";}
        }
        else if(floor>=11 && floor<=15){
            if(num < 20){return "enemy4";}
            else if(num < 40){return "enemy2";}
            else{return "enemy3";}
        }
        else if(floor>=16 && floor<=20){
            if(num < 20){return "enemy4";}
            else if(num < 30){return "enemy2";}
            else{return "enemy3";}
        }
        else if(floor>=21 && floor<=30){
            if(num < 10){return "enemy4";}
            else if(num < 20){return "enemy5";}
            else if(num < 30){return "enemy2";}
            else{return "enemy3";}
        }
        else if(floor >= 31){
            if(num < 20){return "enemy4";}
            else if(num < 50){return "enemy5";}
            else{return "enemy3";}
        }
        else{
            return "enemy";
        }
    }
}
