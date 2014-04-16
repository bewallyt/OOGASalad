package engine.collision;

import engine.ProximityChecker;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.gridobject.person.Person;

public class BumpCollision extends CollisionHandler{

	public BumpCollision(GridObject obj1, GridObject obj2) {
		super(obj1, obj2);
	}

	@Override
	public void doCollision() {		
		if(myObj1 instanceof Person){
			if(ProximityChecker.isLeftProximity(myObj1, myObj2)==2){
				((Person) myObj1).setMaxX((int)myObj2.getBounds().getMinX()-myObj1.getSize()[0]);
			}
			if(ProximityChecker.isRightProximity(myObj1, myObj2)==-2 ){
				((Person) myObj1).setMinX((int)myObj2.getBounds().getMaxX()-myObj1.getSize()[0]);
			}
			if(ProximityChecker.isBottomProximity(myObj1, myObj2)==-2){
				((Person) myObj1).setMinY((int)myObj2.getBounds().getMaxY());
			}
			if(ProximityChecker.isTopProximity(myObj1, myObj2)==2){
				((Person) myObj1).setMaxY((int)myObj2.getBounds().getMinY());
			}
		}
	}
}
