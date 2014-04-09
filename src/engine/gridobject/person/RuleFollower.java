package engine.gridobject.person;

import java.awt.Rectangle;
import java.util.List;

import engine.gridobject.GridObject;
import engine.gridobject.item.Item;
import engine.gridobject.item.Weapon;

public abstract class RuleFollower extends GridObject {

	private List<Item> myItems;
	private double mySpeed;
	private double myDX=0;
	private double myDY=0;
	private int myMaxX;
	private int myMinX;
	private int myMaxY;
	private int myMinY;
	protected Weapon myWeapon;
	//up=0, right=1, down=2, left=3
	private int myFacing=2;
	private String currentImageFile;
	
	public RuleFollower(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
		super(animImages, numTilesWidth, numTilesHeight);
		mySpeed = speed;
		resetMax();
		myWeapon = null;
		currentImageFile=getAnimImages()[2];
	}
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
		if(!(getX()+getDX()>myMaxX) && !(getX()+getDX()-getWidth()<myMinX))
			incrementX(getDX());
		if(!(getY()+getDY()+getHeight()>myMaxY) && !(getY()+getDY()<myMinY))
			incrementY(getDY());
		resetMax();
		uniqueMove();
		
	}

	private void updateFacing() {
		String imageName=currentImageFile;
		if(getDX()>0){
			myFacing=1;
			imageName=getAnimImages()[1];
		}
		else if(getDX()<0){
			myFacing=3;
			imageName=getAnimImages()[3];
		}
		else if(getDY()>0){
			myFacing=2;
			imageName=getAnimImages()[2];
		}
		else if(getDY()<0){
			myFacing=0;
			imageName=getAnimImages()[0];
		}
		setImage(imageName);
		currentImageFile=imageName;
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
	public int getFacing(){
		return myFacing;
	}
	


}
