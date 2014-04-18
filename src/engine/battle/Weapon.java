package engine.battle;

import java.util.ArrayList;
import java.util.List;

import engine.Statistic;
import engine.dialogue.MatrixNode;
import engine.gridobject.Pickupable;
import engine.gridobject.person.Player;

public class Weapon implements Pickupable, MatrixNode, BattleExecutable{
	private String myName;
	private String myImageName;
	private Statistic myDamage;
	private Statistic mySpeed;
	private List<Attack> myAttacks;
	public final static int DEFAULT_SPEED=1;
	public final static int DEFAULT_DAMAGE=1;
	public final static int DEFAULT_MAX=100;
	public Weapon(String image, String name, List<Attack> attacks) {
		myName = name;
		myImageName = image;
		myAttacks = attacks;
		mySpeed = new Statistic("Speed", DEFAULT_SPEED,DEFAULT_MAX);
		myDamage = new Statistic("Damage",DEFAULT_DAMAGE,DEFAULT_MAX);
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
	public void setDamage(int value, int max){
		myDamage = new Statistic("damage",value, max);
	}
	public void setSpeed(int value, int max){
		mySpeed = new Statistic("speed",value, max);
	}

	@Override
	public void pickUp(Player player) {
		player.addWeapon(this);
		
	}

}
