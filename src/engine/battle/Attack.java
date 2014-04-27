package engine.battle;

import java.awt.Image;

import engine.Statistic;
import engine.dialogue.MatrixNode;
import engine.gridobject.person.Person;
import engine.item.Weapon;

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
	
	public Attack(String name, int damage, int speed, String selected, int value, boolean affect){
		myName = name;
		mySpeed = new Statistic("Speed", speed, Weapon.DEFAULT_MAX);
		myDamage = new Statistic("Damage", damage, Weapon.DEFAULT_MAX);
		setEffect(selected, null, value);
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
	public String getEffectMessage(){
		if(myEffect!=null)
			return myEffect.toString();
		return "";
	}

	@Override
	public String toString() {
		return myName;
	}

	@Override
	public Image getImage() {
		//no image
		return null;
	}
	
	
}
