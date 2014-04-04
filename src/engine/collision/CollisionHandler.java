package engine.collision;

import java.util.List;

import engine.gridobject.GridObject;
import engine.gridobject.item.Weapon;

public abstract class CollisionHandler {
	
	protected boolean harm=false;
	protected GridObject myObj1;
	protected GridObject myObj2;
	
	public CollisionHandler(GridObject obj1, GridObject obj2) {
		myObj1=obj1;
		myObj2=obj2;
	}
	
	
	
	public abstract void doCollision();
}
