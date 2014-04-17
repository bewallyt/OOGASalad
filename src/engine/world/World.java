package engine.world;

import java.util.ArrayList;
import java.util.List;

import engine.collision.CollisionHandler;
import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;

public abstract class World {


	private Player myPlayer;
	private int myPlayWidth;
	private int myPlayHeight;
	private int[] mySavedPlayerPosition;




	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public World(int playWidth, int playHeight, Player p) {
		myPlayer = p;
		myPlayWidth=playWidth;
		myPlayHeight=playHeight;
	}

	public int getPlayWidth() {
		return myPlayWidth;
	}


	public int getPlayHeight() {
		return myPlayHeight;

	}
	
	public Player getPlayer(){
		return myPlayer;
	}
	public void savePlayerPosition(){
		mySavedPlayerPosition = new int[] {getPlayer().getX(),getPlayer().getY(),reverseFacing()};
	}
	
	public int[] getSavedPlayerPosition(){
		return mySavedPlayerPosition;
	}
	private int reverseFacing(){
		if(getPlayer().getFacing()==1)return 3;
		else{
			return Math.abs(getPlayer().getFacing()-2);
		}
	}

}
