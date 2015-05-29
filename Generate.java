import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Generate here.
 * 
 * @author Kris Leung
 * @version (a version number or a date)
 */
public class Generate
{
    String array[][][] = new String[58][56][3];
    private int width, height, xPlace, yPlace;
    public String[][][] generateMap(){
        generateBorder();
        generateStartRoom();
        return array;
    }

    public void generateBorder(){
        // 4x3, 4x52, 53x3, 53x52
        for(int i = 4; i < 52; i++){
            array[4][i][0] = "border";
            array[53][i][0] = "border";
        }
        for(int j = 4; j < 54; j++){
            array[j][4][0] = "border";
            array[j][52][0] = "border";
        }
    }

    public void generateStartRoom(){
        width = getRandWidth();
        height = getRandHeight();
        xPlace = getRandX(width);
        yPlace = getRandY(height);
        System.out.println(width);
        System.out.println(height);
        System.out.println(xPlace);
        System.out.println(yPlace);
        for(int i = xPlace; i < xPlace + width + 1; i++){
            array[i][yPlace][0] = "wall";
            array[i][yPlace+height+1][0] = "wall";
        }
        for(int j = yPlace; j < yPlace + height + 1; j++){
            array[xPlace][j][0] = "wall";
            array[xPlace+width][j][0] = "wall";
        }
        for(int k = xPlace + 1; k < xPlace + width; k++){
            for(int l = yPlace + 1; l < yPlace + height+1; l++){
                array[k][l][0] = "floorTile";
            }
        }
    }

    public void generateEndRoom(){

    }

    public void generateRoom(){

    }

    public String[][][] returnMap(){
        return array;
    }
    
    public int getRandWidth(){
        return Greenfoot.getRandomNumber(7) +3;
    }
    public int getRandHeight(){
        return Greenfoot.getRandomNumber(7) +3;
    }
    public int getRandX(int w){
        return Greenfoot.getRandomNumber(48-w)+5;
    }
    public int getRandY(int h){
        return Greenfoot.getRandomNumber(47-h) +5;
    }
    
}
