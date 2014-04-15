package engine.collision;

import engine.gridobject.GridObject;

public class NullCollision extends CollisionHandler{

	public NullCollision(GridObject obj1, GridObject obj2) {
		super(obj1, obj2);

	}

	@Override
	public void doCollision() {
		
	}

}
