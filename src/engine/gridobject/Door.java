package engine.gridobject;

import java.util.List;

import util.Constants;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Door extends GridObject{
	private World myBuildingWorld;
	/**
	 * @param image file
	 * @param numTilesWidth the width in tiles
	 * @param numTilesHeight the height in tiles
	 */
	public Door(String image, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
	}
	
	public Door(List<Object> list){
		super((String) list.get(Constants.IMAGE_CONST), (int) ((Double) list.get(Constants.WIDTH_CONST)).intValue(), (int) ((Double) list.get(Constants.HEIGHT_CONST)).intValue());
	}
	
	/**
	 * is the player at the door
	 * @param player
	 * @return true if the player is at the door
	 */
	public boolean playerAtDoor(Player player){
		return (Math.abs(player.getX()-getX())<getWidth() && Math.abs(player.getY()-getY())<getHeight());
	}
	
	/**
	 * Sets the world.
	 *
	 * @param world the new world
	 */
	public void setWorld(World world){
		myBuildingWorld = world;
	}
	
	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public World getWorld(){
		return myBuildingWorld;
	}
}
