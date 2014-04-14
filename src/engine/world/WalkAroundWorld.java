package engine.world;

import java.util.List;

import engine.collision.CollisionHandler;
import engine.collision.CollisionMatrix;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;

public class WalkAroundWorld extends World {

	
	private int myNumTileWidth;
	private int myNumTileHeight;
	private int myTileSize;
	private Tile[][] myTileMatrix;
	private List<GridObject> myGridObjectList;
	private Player myPlayer;
	private CollisionMatrix myCollisionMatrix;
	
	
	/**
	 * Instantiates a new World.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public WalkAroundWorld(int tileSize, int playWidth, int playHeight, Player p, 
													List<GridObject> gridObjects) {
		super(tileSize, playWidth, playHeight, p, gridObjects);
		
	}
	


}
