package authoring.gameObjects;


import java.util.ArrayList;
import java.util.List;

import engine.battle.Attack;
import engine.item.Weapon;

/**
 * Class that handles all information relevant to a weapon
 * @author Pritam M. 
 *
 */
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
    /**
     * Makes a weapon with the name, image, attacks, speed, and damage already specified
     */
    public Weapon makeWeapon() {
    	return new Weapon(getMyName(), getMyImage(), getMySpeed(), getMyDamage(), makeAttacks());
    }
    
    /**
     * Returns a list of all attacks available for the current weapon
     */
    private List<Attack> makeAttacks() {
    	List<Attack> retAttacks = new ArrayList<Attack>();
    	for(int i = 0; i < getMyAttacks().size(); i++) {
    		Attack currAttack = new Attack(
    				getMyAttacks().get(i).getMyName(),
    				getMyAttacks().get(i).getMyDamage(),
    				getMyAttacks().get(i).getMySpeed(),
    				getMyAttacks().get(i).getAffectAttribute(),
    				getMyAttacks().get(i).getAffectValue(),
    				getMyAttacks().get(i).getAffectPlayer());
    		retAttacks.add(currAttack);
    	}
    	return retAttacks;
    }

    /**
     * Returns the name of the weapon being made
     */
    public String getMyName(){ return myName;}
    /**
     * Returns the image of the weapon being made
     */
    public String getMyImage(){ return myImage;}
    /**
     * Returns a List of the attacks of the weapon being made
     */
    public List<AttacksData> getMyAttacks(){ return myAttacks;}
    /**
     * Returns the speed of the weapon being made
     */
    public int getMySpeed(){ return mySpeed;}
    /**
     * Returns the damage of the weapon being made
     */
    public int getMyDamage(){ return myDamage;}
}
