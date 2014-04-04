package engine.gridobject.person;

import java.awt.Rectangle;
import java.util.List;

import engine.gridobject.GridObject;
import engine.gridobject.item.Item;
import engine.gridobject.item.Weapon;
import engine.world.Tile;

public class RuleFollower extends GridObject {

	protected List<Item> myItems;
	protected double mySpeed;
	protected double myDX=0;
	protected double myDY=0;
	protected int myMaxX;
	protected int myMinX;
	protected int myMaxY;
	protected int myMinY;

	protected Weapon myWeapon;
	
	public RuleFollower(String image, double speed, int numTilesWidth, int numTilesHeight) {
		super(image, numTilesWidth, numTilesHeight);
		mySpeed = speed;
		resetMax();
		myWeapon = null;
	}
	
//	public RuleFollower(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
//		super(animImages, numTilesWidth, numTilesHeight);
//		mySpeed = speed;
//		resetMax();
//		myWeapon = null;
//	}
	
	public void setMaxX(int maxX){
		myMaxX=maxX;
	}
	public void setMinX(int minX){
		myMinX=minX;
	}
	public void setMaxY(int maxY){
		myMaxY=maxY;
	}
	public void setMinY(int minY){
		myMinY=minY;
	}
	public void resetMax(){
		myMaxX = myMaxY = Integer.MAX_VALUE;
		myMinX = myMinY = Integer.MIN_VALUE;
	}
	@Override
	public void move() {
		updateFacing();
		if(!(myX+myDX>myMaxX) && !(myX+myDX-myWidth<myMinX))
			myX+=myDX;
		if(!(myY+myDY+myHeight>myMaxY) && !(myY+myDY<myMinY))
			myY+=myDY;
		resetMax();
		uniqueMove();
		
	}

	private void updateFacing() {
		if(myDX>0)setFacing("right");
		else if(myDX<0)setFacing("left");
		else if(myDY>0)setFacing("down");
		else if(myDY<0)setFacing("up");
	}
	
	public Rectangle getNextBounds(){
		
		return new Rectangle((int)(myX+myDX), (int)(myY+myDY), myWidth, myHeight);
	}
	
	public void addWeapon(Weapon weapon){
		
	}

	public void addItem(Item it) {
		myItems.add(it);
	}
	
	public void removeItem(Item it) {
		for (Item current : myItems) {
			if (current.getName().equals(it.getName())) {
				myItems.remove(current);
			}
		}
	}
	


}
