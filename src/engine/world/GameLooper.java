package engine.world;

import engine.dialogue.DialogueDisplayControl;
import engine.gridobject.GridObject;
import engine.gridobject.person.Person;


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
