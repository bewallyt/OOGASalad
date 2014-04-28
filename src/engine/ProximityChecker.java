package engine;

import util.Constants;
import engine.gridobject.GridObject;
import engine.gridobject.person.Person;

public class ProximityChecker {

	public static int isLeftProximity(GridObject Obj1, GridObject Obj2){
		if((isOnBottom(Obj1, Obj2)<-Constants.COLLISION_OFFSET && isOnTop(Obj1, Obj2)>2))
			return (int) isOnLeft(Obj1, Obj2);
		return Integer.MAX_VALUE;
		
	}
	public static int isRightProximity(GridObject Obj1, GridObject Obj2){
		if((isOnBottom(Obj1, Obj2)<-Constants.COLLISION_OFFSET && isOnTop(Obj1, Obj2)>2))
			return (int) isOnRight(Obj1, Obj2);
		return Integer.MAX_VALUE;
		
	}
	public static int isBottomProximity(GridObject Obj1, GridObject Obj2){
		if(isOnRight(Obj1, Obj2)<-Constants.COLLISION_OFFSET && isOnLeft(Obj1, Obj2)>2)
			return (int) isOnBottom(Obj1, Obj2);
		return Integer.MAX_VALUE;
	}

	public static int isTopProximity(GridObject Obj1, GridObject Obj2){
		if(isOnRight(Obj1, Obj2)<-Constants.COLLISION_OFFSET && isOnLeft(Obj1, Obj2)>2)
			return (int) isOnTop(Obj1, Obj2);
		return Integer.MAX_VALUE;
	}

	private static double isOnLeft(GridObject Obj1, GridObject Obj2){
		return Obj1.getBounds().getMaxX()-Obj2.getBounds().getMinX();
	}

	private static double isOnRight(GridObject Obj1, GridObject Obj2){
		return Obj1.getBounds().getMinX()-Obj2.getBounds().getMaxX();
	}

	private static double isOnBottom(GridObject Obj1, GridObject Obj2){
		return Obj1.getBounds().getMinY()-Obj2.getBounds().getMaxY();
	}

	private static double isOnTop(GridObject Obj1, GridObject Obj2){
		return Obj1.getBounds().getMaxY()-Obj2.getBounds().getMinY();
	}
}
