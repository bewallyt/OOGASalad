package engine.gridobject;

import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;

public class Door extends GridObject{
	private WalkAroundWorld myBuildingWorld;
	public Door(String image, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
	}
	
	public boolean playerAtDoor(Player player){
		return (Math.abs(player.getX()-getX())<getWidth() && Math.abs(player.getY()-getY())<getHeight());
	}
	
	public void setBuildingWorld(WalkAroundWorld world){
		myBuildingWorld = world;
	}
	
	public WalkAroundWorld getBuildingWorld(){
		return myBuildingWorld;
	}
}
