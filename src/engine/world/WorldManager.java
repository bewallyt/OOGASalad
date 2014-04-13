package engine.world;

import java.util.List;


public class WorldManager {
	private List<World> myWorldList;
	private WalkAroundWorld myOutsideWorld;
	
	public void setOutsideWorld(WalkAroundWorld world){
		myOutsideWorld = world;
	}
	
	public WalkAroundWorld getOutsideWorld(){
		return myOutsideWorld;
	}
	
	

}
