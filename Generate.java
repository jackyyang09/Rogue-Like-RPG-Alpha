import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Generate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Generate
{
    String array[][][] = new String[58][56][3];

    public void generateMap(int lvl){

    }
    
    public void generateBorder(){
        // 4x3, 4x52, 53x3, 53x52
        for(int i = 3; i < 52; i++){
            array[4][i][0] = "border";
            array[53][i][0] = "border";
        }
        for(int j = 4; j < 53; j++){
            array[j][4][0] = "border";
            array[j][52][0] = "border";
        }
    }

    public void generateStartRoom(){

    }
    
    public void generateEndRoom(){
        
    }
    
    public void generateRoom(){
        
    }

    public String[][][] returnMap(){
        return array;
    }
}
