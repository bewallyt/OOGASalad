package engine.gridobject.item;

import engine.Statistic;

public class Attack {
	private String myName;
	private Statistic myDamage;
	private Statistic mySpeed;
	public Attack(String name){
		myName=name;
	}
	
	public Statistic getDamage(){
		return myDamage;
	}
	
	public Statistic getSpeed(){
		return mySpeed;
	}
	
	public String getName(){
		return myName;
	}
}
