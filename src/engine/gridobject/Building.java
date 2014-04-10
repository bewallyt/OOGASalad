package engine.gridobject;

import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Building extends Barrier{
	private Door myDoor;
	private WalkAroundWorld myBuildingWorld;
	public Building(String image, int numTilesWidth, int numTilesHeight, int doorX, int doorY) {
		super(image, numTilesWidth, numTilesHeight);
		myDoor=new Door(doorX,doorY);
//		System.out.println("doorx: " + myDoorX + "doory: " + myDoorY );
	}
	
	
	public Door getDoor(){
		return myDoor;
	}
	
	public void setBuildingWorld(WalkAroundWorld world){
		myBuildingWorld = world;
	}
	
	public WalkAroundWorld getBuildingWorld(){
		return myBuildingWorld;
		
	}
	
	
}
