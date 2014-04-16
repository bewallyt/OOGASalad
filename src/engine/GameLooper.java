package engine;

import engine.world.World;

public abstract class GameLooper {
	World myWorld;
	public GameLooper(World currentWorld){
		myWorld=currentWorld;
	}
	
	public abstract World doLoop();
	
	public World getWorld(){
		return myWorld;
	}
}
