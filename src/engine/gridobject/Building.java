package engine.gridobject;

import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.world.World;

public class Building extends Barrier{
	private int myDoorX;
	private int myDoorY;
	private boolean buildingEntered = false;
	private WalkAroundWorld myBuildingWorld;
	public Building(String image, int numTilesWidth, int numTilesHeight, int doorX, int doorY) {
		super(image, numTilesWidth, numTilesHeight);
		myDoorX=doorX;
		myDoorY=doorY;
//		System.out.println("doorx: " + myDoorX + "doory: " + myDoorY );
	}
	
	public boolean playerAtDoor(Player player){
		return (Math.abs(player.getX()-myDoorX)<player.getWidth() && Math.abs(player.getY()-myDoorY)<player.getHeight());
	}
	
	public int getDoorY(){
		return myDoorY;
	}
	
	public void setBuildingWorld(WalkAroundWorld world){
		myBuildingWorld = world;
	}
	
	public void enterBuilding(){
		buildingEntered=true;
	}
	public boolean isBuildingEntered(){
		return buildingEntered;
	}
	public WalkAroundWorld getBuildingWorld(){
		buildingEntered=false;
		return myBuildingWorld;
		
	}
	
	
}
