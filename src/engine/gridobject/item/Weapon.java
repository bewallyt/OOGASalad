package engine.gridobject.item;

import java.util.ArrayList;
import java.util.List;

import engine.Statistic;

public class Weapon{
	private String myName;
	private String myImageName;
	private Statistic myDamage;
	private Statistic mySpeed;
	private List<Attack> myAttacks;
	public final static int DEFAULT_SPEED=1;
	public final static int DEFAULT_DAMAGE=1;
	public Weapon(String image, String name, List<Attack> attacks) {
		myName = name;
		myImageName = image;
		myAttacks = new ArrayList<Attack>(attacks);
		mySpeed = new Statistic("Speed", DEFAULT_SPEED);
		myDamage = new Statistic("Damage",DEFAULT_DAMAGE);
	}

	public void addAttack(Attack attack){
		myAttacks.add(attack);
	}
	public void removeAttack(Attack attack){
		myAttacks.remove(attack);
	}
	
	public List<Attack> getAttackList(){
		return myAttacks;
	}
	
	public Statistic getDamage(){
		return myDamage;
	}
	
	public Statistic getSpeed(){
		return mySpeed;
	}

}
