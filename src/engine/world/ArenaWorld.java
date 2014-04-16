package engine.world;

import engine.gridobject.person.Enemy;
import engine.gridobject.person.Player;

public class ArenaWorld extends World {
	
	Enemy myEnemy;
	public ArenaWorld(int playWidth, int playHeight, Player p, Enemy enemy) {
		super(playWidth, playHeight, p);
		myEnemy = enemy;
		//		Image myBackground = new ScaledImage(width, height,myBackground).scaleImage();

	}
	
	public Enemy getEnemy(){
		return myEnemy;
	}

}
