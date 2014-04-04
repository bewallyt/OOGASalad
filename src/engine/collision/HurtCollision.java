package engine.collision;

import engine.gridobject.GridObject;

public class HurtCollision extends CollisionHandler{

	public HurtCollision(GridObject obj1, GridObject obj2){
		super(obj1,obj2);
		super.harm=true;
	}
	@Override
	public void doCollision() {
		
		
	}

}
