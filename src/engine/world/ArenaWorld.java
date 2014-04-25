package engine.world;

import java.awt.Image;

import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;
import engine.item.Weapon;

public class ArenaWorld extends World {
	ScaledImage myBackground;
	Enemy myEnemy;
	WalkAroundWorld myPrevWorld;
	boolean randomEncounter=false;
	
	public ArenaWorld(String backgroundImage, int playWidth, int playHeight, Player p, Enemy enemy, WalkAroundWorld prevWorld) {
		super(playWidth, playHeight, p);
		myEnemy = enemy;
		myBackground = new ScaledImage(Canvas.CANVAS_SIZE,(int) (Canvas.CANVAS_SIZE*.65),backgroundImage);
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
