package engine.collision;

import engine.gridobject.GridObject;

public class NullCollision extends CollisionHandler{

	/**
	 * Instantiates a new null collision.
	 *
	 * @param obj1 the obj1
	 * @param obj2 the obj2
	 */
	public NullCollision(GridObject obj1, GridObject obj2) {
		super(obj1, obj2);

	}

	@Override
	public void doCollision() {
		
	}

}
