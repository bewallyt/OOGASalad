package engine.collision;

import engine.gridobject.GridObject;
import engine.gridobject.Player;
import engine.gridobject.RuleFollower;

public class BumpCollision extends CollisionHandler{

	public BumpCollision(GridObject obj1, GridObject obj2) {
		super(obj1, obj2);
	}

	@Override
	public void doCollision() {		
		if(myObj1 instanceof RuleFollower){
			//obj 1 is on the left of obj2
			if(Math.abs(myObj1.getBounds().getMaxX()-myObj2.getBounds().getMinX())<5){
				((RuleFollower) myObj1).setMaxX((int)myObj2.getBounds().getMinX()-myObj1.getSize()[0]);
			}
			//obj 1 is on the right of obj2
			if(Math.abs(myObj1.getBounds().getMinX()-myObj2.getBounds().getMaxX())<5){
				((RuleFollower) myObj1).setMinX((int)myObj2.getBounds().getMaxX()-myObj1.getSize()[0]);
			}
			//obj 1 is on bottom
			if(Math.abs(myObj1.getBounds().getMinY()-myObj2.getBounds().getMaxY())<5){
				((RuleFollower) myObj1).setMinY((int)myObj2.getBounds().getMaxY());
			}
			//obj 1 is on the top
			if(Math.abs(myObj1.getBounds().getMaxY()-myObj2.getBounds().getMinY())<5){
				((RuleFollower) myObj1).setMaxY((int)myObj2.getBounds().getMinY());
			}
		}
	}
}
