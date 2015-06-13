import greenfoot.*;
import java.awt.Font;
/**
 * Displays a window showing detailed information when hovering over an item
 * 
 * @author Jacky Yang
 * @version (June 12 2015)
 */
public class InfoTab extends Actor
{
    ItemInventory items;
    private boolean begin = false;
    private Font fontName, fontDesc, fontNums;
    
    /**
     * constructor for InfoTab class
     * 
     * @param iteminventory the iteminvetory item info is taken from
     */
    public InfoTab(ItemInventory iteminventory)
    {
        setImage("Info.png");
        items = iteminventory;
        fontName = new Font("OCR A Extended", 2, 20);
        fontDesc = new Font("OCR A Extended", 1, 13);
        fontNums = new Font("OCR A Extended", 3, 20);
    }

    /**
     * Act - do whatever the InfoTab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (begin == false){begin();} //Run only once
    }    

    /**
     * Method used to obtain data the item database and display it onto the original image
     */
    public void begin()
    {
        GreenfootImage image = new GreenfootImage(getImage());
        image.setFont(fontName);
        image.drawImage(items.getImage(), 23, 28); //Create item image in top left corner
        Items item = new Items(items.getItemID()); //Obtains references from the item database
        image.drawString(item.getName(), 213, 56); //Generate item's name
        image.setFont(fontDesc);
        image.drawString(item.getDescription(), 155, 85); //Generate item's description
        image.setFont(fontNums);
        image.drawString(Integer.toString(item.getAtkBuff()), 50, 167); //Generate attack stat
        image.drawString(Integer.toString(item.getDefBuff()), 105, 167); //Generate defense stat
        image.drawString(Integer.toString(item.getLukBuff()), 50, 192); //Generate luck stat
        image.drawString(Integer.toString(item.getDexBuff()), 105, 192); //Generate dexterity stat
        setImage(image);
        begin = true;
    }
}
