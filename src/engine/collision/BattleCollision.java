package engine.collision;

import java.util.Random;

import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.ArenaWorld;

public class BattleCollision extends CollisionHandler{
	private static final int BATTLE_POSSIBILITY = 30;
	private static final int BATTLE_CHANCE = 10;
	Random myRandom = new Random();
	int chance=0;
	public BattleCollision(GridObject obj1, GridObject obj2){
		super(obj1, obj2);
	}
	
	@Override
	public void doCollision() {
		if(myObj1 instanceof Player){
			if(chance==BATTLE_CHANCE){
				((Player) myObj1).enterDoor((Door) myObj2);
				((ArenaWorld) ((Door) myObj2).getWorld()).setRandomEncounter();
				chance=0;
			}
			
			else{
				chance = myRandom.nextInt(BATTLE_POSSIBILITY);
			}
		}
			
	
	}
}
