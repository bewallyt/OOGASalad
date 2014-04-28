package engine.battle;

import java.awt.Image;

import util.Constants;
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
	}


	public Attack(String name, int damage, int speed, String selected, int value, Boolean affects){
		myName = name;
		mySpeed = new Statistic("speed", speed, Weapon.DEFAULT_MAX);
		myDamage = new Statistic("damage", damage, Weapon.DEFAULT_MAX);
		setEffect(selected, affects, value);
	}

	public Statistic getDamage(){
		return myDamage;
	}
	
	public Statistic getSpeed(){
		return mySpeed;
	}

	public void setEffect(String statistic, boolean affectsSelf, int change){
		myEffect = new Effect(statistic, affectsSelf, change);
	}
	public void setDamage(int value, int max){
		myDamage = new Statistic(Constants.DAMAGE,value, max);
	}
	public void setSpeed(int value, int max){
		mySpeed = new Statistic(Constants.SPEED,value, max);
	}
	public Effect getEffect(){
		return myEffect;
	}
	public String getEffectMessage(){
		if(myEffect!=null)
			return myEffect.toString();
		return "";
	}
	public String getDataEffectMessage(){
		if(myEffect!=null)
			return myEffect.toStringData();
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
