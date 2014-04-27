package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import java.util.ArrayList;
import java.util.List;

import engine.battle.Attack;
import engine.item.Weapon;


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
    
    public Weapon makeWeapon() {
    	return new Weapon(getMyName(), getMyImage(), getMySpeed(), getMyDamage(), makeAttacks());
    }
    
    private List<Attack> makeAttacks() {
    	List<Attack> retAttacks = new ArrayList<Attack>();
    	for(int i = 0; i < getMyAttacks().size(); i++) {
    		Attack currAttack = new Attack(
    				getMyAttacks().get(i).getMyName(),
    				getMyAttacks().get(i).getMyDamage(),
    				getMyAttacks().get(i).getMySpeed(),
    				getMyAttacks().get(i).getAffectAttribute(),
    				getMyAttacks().get(i).getAffectValue(),
    				true);
    		retAttacks.add(currAttack);
    	}
    	return null;
    }

    public String getMyName(){ return myName;}
    public String getMyImage(){ return myImage;}
    public List<AttacksData> getMyAttacks(){ return myAttacks;}
    public int getMySpeed(){ return mySpeed;}
    public int getMyDamage(){ return myDamage;}
}
