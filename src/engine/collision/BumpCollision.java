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
		//		System.out.println("xpos " + myObj1.getBounds().getMaxX());
		//		System.out.println("xpos2 " + myObj2.getBounds().x);
		//		if((myObj1.getBounds().getMaxX()-myObj2.getBounds().x)>-1){
		//			System.out.println(myObj1);
		//			((RuleFollower) myObj1).setMaxX((int)myObj2.getBounds().x-myObj1.getSize()[0]);
		//		}
		//		if((myObj1.getPosition()[1]-myObj2.getPosition()[1])<.1){
		//			((RuleFollower) myObj1).setMaxY((int)myObj2.getBounds().y-myObj1.getSize()[1]);
		//		}
		//		if((myObj2.getPosition()[0]-myObj1.getPosition()[0])<.1){
		//			((RuleFollower) myObj1).setMinX((int)myObj2.getBounds().getMaxX());
		//		}
		//		if((myObj2.getPosition()[1]-myObj1.getPosition()[1])<.1){
		//			((RuleFollower) myObj1).setMinY((int)myObj2.getBounds().getMaxY());
		//		}
		
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
