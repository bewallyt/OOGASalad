package engine;

import engine.gridobject.GridObject;
import engine.gridobject.person.Person;

public class ProximityChecker {

//	public static boolean isLeftProximity(GridObject Obj1, GridObject Obj2){
//		return isOnLeft(Obj1, Obj2)==2 && (isOnBottom(Obj1, Obj2)<-2 && isOnTop(Obj1, Obj2)>2);
//	}
//	public static boolean isRightProximity(GridObject Obj1, GridObject Obj2){
//		return isOnRight(Obj1, Obj2)==-2 && (isOnBottom(Obj1, Obj2)<-2 && isOnTop(Obj1, Obj2)>2);
//	}
//	public static boolean isBottomProximity(GridObject Obj1, GridObject Obj2){
//		return isOnBottom(Obj1, Obj2)==-2 && isOnRight(Obj1, Obj2)<-2 && isOnLeft(Obj1, Obj2)>2;
//	}
//
//	public static boolean isTopProximity(GridObject Obj1, GridObject Obj2){
//		return isOnTop(Obj1, Obj2)==2 && isOnRight(Obj1, Obj2)<-2 && isOnLeft(Obj1, Obj2)>2;
//	}
	public static int isLeftProximity(GridObject Obj1, GridObject Obj2){
		if((isOnBottom(Obj1, Obj2)<-2 && isOnTop(Obj1, Obj2)>2))
			return (int) isOnLeft(Obj1, Obj2);
		return Integer.MAX_VALUE;
		
	}
	public static int isRightProximity(GridObject Obj1, GridObject Obj2){
		if((isOnBottom(Obj1, Obj2)<-2 && isOnTop(Obj1, Obj2)>2))
			return (int) isOnRight(Obj1, Obj2);
		return Integer.MAX_VALUE;
		
	}
	public static int isBottomProximity(GridObject Obj1, GridObject Obj2){
		if(isOnRight(Obj1, Obj2)<-2 && isOnLeft(Obj1, Obj2)>2)
			return (int) isOnBottom(Obj1, Obj2);
		return Integer.MAX_VALUE;
	}

	public static int isTopProximity(GridObject Obj1, GridObject Obj2){
		if(isOnRight(Obj1, Obj2)<-2 && isOnLeft(Obj1, Obj2)>2)
			return (int) isOnTop(Obj1, Obj2);
		return Integer.MAX_VALUE;
	}

	//		if(myObj1 instanceof RuleFollower){
	//			if(isOnLeft()==2 && (isOnBottom()<-2 && isOnTop()>2)){
	//				((RuleFollower) myObj1).setMaxX((int)myObj2.getBounds().getMinX()-myObj1.getSize()[0]);
	//			}
	//			if(isOnRight()==-2 && (isOnBottom()<-2 && isOnTop()>2) ){
	//				((RuleFollower) myObj1).setMinX((int)myObj2.getBounds().getMaxX()-myObj1.getSize()[0]);
	//			}
	//			if(isOnBottom()==-2 && isOnRight()<-2 && isOnLeft()>2){
	//				((RuleFollower) myObj1).setMinY((int)myObj2.getBounds().getMaxY());
	//			}
	//			if(isOnTop()==2 && isOnRight()<-2 && isOnLeft()>2){
	//				((RuleFollower) myObj1).setMaxY((int)myObj2.getBounds().getMinY());
	//			}
	//		}


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
