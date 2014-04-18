package engine.world;

import java.awt.Image;

import engine.battle.Weapon;
import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class ArenaWorld extends World {
	ScaledImage myBackground;
	Enemy myEnemy;
	WalkAroundWorld myPrevWorld;
	boolean randomEncounter=false;
	
	public ArenaWorld(String backgroundImage, int playWidth, int playHeight, Player p, Enemy enemy, WalkAroundWorld prevWorld) {
		super(playWidth, playHeight, p);
		myEnemy = enemy;
		myBackground = new ScaledImage(playWidth,playHeight,backgroundImage);
		myPrevWorld=prevWorld;
	}
	
	public Enemy getEnemy(){
		return myEnemy;
	}
	
	public ScaledImage getBackground(){
		return myBackground;
	}
	
	public void setRandomEncounter(){
		randomEncounter=true;
	}
	
	public Weapon getDroppedWeapon(){
		return myEnemy.getWeaponList().get(0);
	}
	
	public WalkAroundWorld getPrevWorld(){
		return myPrevWorld;
	}

}
