import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
/**
 * Write a description of class fadingWord here.
 * 
 * @author (Kajamugesh) 
 * @version (a version number or a date)
 */
public class fadingWord extends Actor
{
    private String phrase;
    private int factor;
    private Font font;
    private int transparency;
    private GreenfootImage image = new GreenfootImage(getImage());
    public fadingWord (String phrase, int acts, int x, int y)
    {
        this.phrase = phrase;
        factor = transparency/acts;
        font = new Font("OCR A Extended", 3, 20);
        transparency = 255;
        image.setFont(font);
        image.drawString(phrase,x,y);
        setImage(image);
    }

    /**
     * Act - do whatever the fadingWord wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
//         if(transparency <= 0)
//         {
//             getWorld().removeObject(this);   
//         }
//         image.setTransparency(transparency);
//         transparency -= factor;
    }    

}
