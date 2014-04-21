package engine.battle;

import engine.Statistic;
import engine.dialogue.MatrixNode;
import engine.gridobject.person.Person;

public class Attack implements MatrixNode,BattleExecutable {
	private String myName;
	private Statistic myDamage;
	private Statistic mySpeed;
	private Effect myEffect;
	public Attack(String name){
		myName=name;
		mySpeed = new Statistic("Speed", Weapon.DEFAULT_SPEED,Weapon.DEFAULT_MAX);
		myDamage = new Statistic("Damage",Weapon.DEFAULT_DAMAGE,Weapon.DEFAULT_MAX);
	}
	
	public Statistic getDamage(){
		return myDamage;
	}
	
	public Statistic getSpeed(){
		return mySpeed;
	}

	public void setEffect(String statistic, Person personToActOn, int change){
		myEffect = new Effect(statistic, personToActOn, change);
	}
	public void setDamage(int value, int max){
		myDamage = new Statistic("damage",value, max);
	}
	public void setSpeed(int value, int max){
		mySpeed = new Statistic("speed",value, max);
	}
	public Effect getEffect(){
		return myEffect;
	}

	@Override
	public String toString() {
		return myName;
	}
	
	
}
