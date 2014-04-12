package engine.gridobject;

import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;

public class Door extends GridObject{
	private WalkAroundWorld myBuildingWorld;
	public Door(String image, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
	}

	
//	public Door (int xpos, int ypos){
//		myX = xpos;
//		myY = ypos;
//	}
	
	
	public boolean playerAtDoor(Player player){
		System.out.println(Math.abs(player.getX()-getX())<player.getWidth() && Math.abs(player.getY()-getY())<player.getHeight());
		return (Math.abs(player.getX()-getX())<player.getWidth() && Math.abs(player.getY()-getY())<player.getHeight());
	}
	
	public void setBuildingWorld(WalkAroundWorld world){
		myBuildingWorld = world;
	}
	
	public WalkAroundWorld getBuildingWorld(){
		return myBuildingWorld;
	}
}
