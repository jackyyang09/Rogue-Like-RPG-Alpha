import greenfoot.*;

/**
 * Universal Item class
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Items extends Actor
{
    private int id;
    private String name;
    private String fileName;
    private int equipType;
    private int atkBuff;
    private int defBuff;
    public Items(int num)
    {
        if (num == 1)
        {
            id = num;
            name = "Beam Blade";
            equipType = 1;
            setImage("beamblade1.png");
            fileName = ("beamblade3.png");
            atkBuff = 2;
            defBuff = 0;
        }
    }
    
    /**
     * Create custom weapon, debugging purposes
     */
    public Items(String name, int equipType, String image, int atkVar, int defVar)
    {
        this.name = name;
        this.equipType = equipType;
        setImage(image);
        atkBuff = atkVar;
        defBuff = defVar;
    }
    
    public String getImageFile()
    {
        return fileName;
    }
    
    public int getEquipType()
    {
        return equipType;
    }
}
