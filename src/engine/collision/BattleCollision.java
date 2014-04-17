package engine.collision;

import java.util.Random;

import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.ArenaWorld;

public class BattleCollision extends CollisionHandler{
	Random myRandom = new Random();
	int chance=0;
	public BattleCollision(GridObject obj1, GridObject obj2){
		super(obj1, obj2);
	}
	
	@Override
	public void doCollision() {
		if(myObj1 instanceof Player){
			if(chance==10){
				((Player) myObj1).enterDoor((Door) myObj2);
				((ArenaWorld) ((Door) myObj2).getBuildingWorld()).setRandomEncounter();
			}
			
			else{
				chance = myRandom.nextInt(300);
			}
		}
			
	
	}
}
