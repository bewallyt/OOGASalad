package engine.world;

import java.awt.Image;

import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public class ArenaWorld extends World {
	ScaledImage myBackground;
	Enemy myEnemy;
	
	public ArenaWorld(String backgroundImage, int playWidth, int playHeight, Player p, Enemy enemy) {
		super(playWidth, playHeight, p);
		myEnemy = enemy;
		myBackground = new ScaledImage(playWidth,playHeight,backgroundImage);

	}
	
	public Enemy getEnemy(){
		return myEnemy;
	}
	
	public ScaledImage getBackground(){
		return myBackground;
	}

}
