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
			if(isOnLeft()==2 && (isOnBottom()<-2 && isOnTop()>2)){
				((RuleFollower) myObj1).setMaxX((int)myObj2.getBounds().getMinX()-myObj1.getSize()[0]);
			}
			if(isOnRight()==-2 && (isOnBottom()<-2 && isOnTop()>2) ){
				((RuleFollower) myObj1).setMinX((int)myObj2.getBounds().getMaxX()-myObj1.getSize()[0]);
			}
			if(isOnBottom()==-2 && isOnRight()<-2 && isOnLeft()>2){
				((RuleFollower) myObj1).setMinY((int)myObj2.getBounds().getMaxY());
			}
			if(isOnTop()==2 && isOnRight()<-2 && isOnLeft()>2){
				((RuleFollower) myObj1).setMaxY((int)myObj2.getBounds().getMinY());
			}
		}
	}

	public double isOnLeft(){
		return myObj1.getBounds().getMaxX()-myObj2.getBounds().getMinX();
	}

	public double isOnRight(){
		return myObj1.getBounds().getMinX()-myObj2.getBounds().getMaxX();
	}

	public double isOnBottom(){
		return myObj1.getBounds().getMinY()-myObj2.getBounds().getMaxY();
	}

	public double isOnTop(){
		return myObj1.getBounds().getMaxY()-myObj2.getBounds().getMinY();
	}
}
