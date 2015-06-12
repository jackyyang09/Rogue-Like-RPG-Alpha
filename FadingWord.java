import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * Creates pop up messages that float up and fade away
 * 
 * @author (Kajamugesh) 
 * @version (12/06/2015)
 */
public class FadingWord extends Actor
{
    private int transparency;//transparency level of image from 0 to 255
    private GreenfootImage image;
    private int timer = 0;//used for as counter for increasing transparency
    /**
     * constructor for the FadingWord class
     * 
     * @param text word that will pop up
     */
    public FadingWord (String text)
    {
        Color transparent = new Color(0,0,0,0);//background must be transparent
        Font font = new Font("OCR A Extended", 3, 20);
        transparency = 255;//starts opaque
        GreenfootImage image = new GreenfootImage (text, 12, Color.RED, transparent);
        setImage(image);
    }

    /**
     * Act - do whatever the fadingWord wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (timer%4 == 0){
            setLocation(getX(), getY() -2);//movess position of word
        }
        timer+=2;
        if (timer >= 100){
            getImage().setTransparency(getImage().getTransparency() - 2);//quickly make the image fade
        }
        if (timer >= 195){
            getWorld().removeObject(this);//removes object when faded enough
        }
    }    
}
