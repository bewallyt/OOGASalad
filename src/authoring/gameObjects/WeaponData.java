package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import java.util.List;


public class WeaponData {

    private String myName;
    private String myImage;
    private List<AttacksData> myAttacks;
    private int mySpeed;
    private int myDamage;

    public WeaponData(String name, String image, int speed, int damage, List<AttacksData> attacks){
        myName = name;
        myImage = image;
        myAttacks = attacks;
        mySpeed = speed;
        myDamage = damage;
    }

    public String getMyName(){ return myName;}
    public String getMyImage(){ return myImage;}
    public List<AttacksData> getMyAttacks(){ return myAttacks;}
    public int getMySpeed(){ return mySpeed;}
    public int getMyDamage(){ return myDamage;}
}
