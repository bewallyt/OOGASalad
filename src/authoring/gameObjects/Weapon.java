package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import java.util.List;


public class Weapon {

    private String myName;
    private String myImage;
    private List<Attacks> myAttacks;
    private int mySpeed;
    private int myDamage;

    public Weapon(String name, String image, int speed, int damage, List<Attacks> attacks){
        myName = name;
        myImage = image;
        myAttacks = attacks;
        mySpeed = speed;
        myDamage = damage;
    }

    public String getMyName(){ return myName;}
    public String getMyImage(){ return myImage;}
    public List<Attacks> getMyAttacks(){ return myAttacks;}
    public int getMySpeed(){ return mySpeed;}
    public int getMyDamage(){ return myDamage;}
}
