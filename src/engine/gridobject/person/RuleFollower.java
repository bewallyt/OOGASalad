package engine.gridobject.person;

import java.awt.Rectangle;
import java.util.List;

import engine.gridobject.GridObject;
import engine.gridobject.item.Item;
import engine.gridobject.item.Weapon;

public class RuleFollower extends GridObject {

	private List<Item> myItems;
	private double mySpeed;
	private double myDX=0;
	private double myDY=0;
	private int myMaxX;
	private int myMinX;
	private int myMaxY;
	private int myMinY;
	protected Weapon myWeapon;
	
	
	
	public RuleFollower(String image, double speed, int numTilesWidth, int numTilesHeight ) {
		super(image, numTilesWidth, numTilesHeight);
		
		
		
		setSpeed(speed);
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
		if(!(super.getX()+getDX()>myMaxX) && !(super.getX()+getDX()-super.getWidth()<myMinX))
			super.incrementX(getDX());
		if(!(super.getY()+getDY()+super.getHeight()>myMaxY) && !(super.getY()+getDY()<myMinY))
			super.incrementY(getDY());
		resetMax();
		uniqueMove();
		
	}

	private void updateFacing() {
		if(getDX()>0)setFacing("right");
		else if(getDX()<0)setFacing("left");
		else if(getDY()>0)setFacing("down");
		else if(getDY()<0)setFacing("up");
	}
	
	public Rectangle getNextBounds(){
		
		return new Rectangle((int)(super.getX()+getDX()), (int)(super.getY()+getDY()), 
										super.getWidth(), super.getHeight());
	}
	
	public void addWeapon(Weapon weapon){
		
	}

	public void addItem(Item it) {
		getItems().add(it);
	}
	
	public List<Item> getItems() {
		return myItems;
	}

	public void setMyItems(List<Item> items) {
		myItems = items;
	}

	public void removeItem(Item it) {
		for (Item current : getItems()) {
			if (current.getName().equals(it.getName())) {
				getItems().remove(current);
			}
		}
	}

	public double getDY() {
		return myDY;
	}

	public void setDY(double myDY) {
		this.myDY = myDY;
	}

	public double getSpeed() {
		return mySpeed;
	}

	public void setSpeed(double mySpeed) {
		this.mySpeed = mySpeed;
	}

	public double getDX() {
		return myDX;
	}

	public void setDX(double myDX) {
		this.myDX = myDX;
	}
	


}
