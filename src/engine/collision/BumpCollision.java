package engine.collision;

import engine.gridobject.GridObject;

public class BumpCollision extends CollisionHandler{

	public BumpCollision(GridObject obj1, GridObject obj2) {
		super(obj1, obj2);
	}

	@Override
	public void doCollision() {
		System.out.println("bump");
		
		
	}
	
	
	
	

}
