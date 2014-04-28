package engine.collision;

import util.Constants;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;

public class HealCollision extends CollisionHandler {

	public HealCollision(GridObject obj1, GridObject obj2) {
		super(obj1, obj2);
	}

	@Override
	public void doCollision() {
		if(myObj1 instanceof Player){
			myObj1.getStatsMap().get(Constants.HEALTH).setToMax();
		}

	}

}
