package engine.gridobject;

import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Door extends GridObject{
	private World myBuildingWorld;
	public Door(String image, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
	}
	
	public boolean playerAtDoor(Player player){
		return (Math.abs(player.getX()-getX())<getWidth() && Math.abs(player.getY()-getY())<getHeight());
	}
	
	public void setBuildingWorld(World world){
		myBuildingWorld = world;
	}
	
	public World getBuildingWorld(){
		return myBuildingWorld;
	}
}
