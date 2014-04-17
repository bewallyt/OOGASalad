package engine.gridobject.person;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import engine.battle.Weapon;
import engine.gridobject.GridObject;
import engine.gridobject.item.Item;
import engine.images.ScaledImage;

public abstract class Person extends GridObject {

	private List<Item> myItems;
	private double mySpeed;
	private double myDX=0;
	private double myDY=0;
	private int myMaxX;
	private int myMinX;
	private int myMaxY;
	private int myMinY;
	private List<Weapon> myWeapons;
	//up=0, right=1, down=2, left=3
	private int myFacing=2;
	private int count = 0;
	private String currentImageFile;
	private Image myBattleImage;
	
	public Person(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
		super(animImages, numTilesWidth, numTilesHeight);
		mySpeed = speed;
		resetMax();
		myWeapons = new ArrayList<Weapon>();
		currentImageFile=getAnimImages()[myFacing];
	}
	
	private boolean isAnim(String[] animImages) {
		return animImages.length == 12;
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
		count++;
		if(count == 50)
			count = 0;
		if(getDX()>0){
			myFacing=1;
			imageName = switchAnim(3, 4, 5, getAnimImages());
		}
		else if(getDX()<0){
			myFacing=3;
			imageName = switchAnim(9, 10, 11, getAnimImages());
		}
		else if(getDY()>0){
			myFacing=2;
			imageName = switchAnim(6, 7, 8, getAnimImages());

		}
		else if(getDY()<0){
			myFacing=0;
			imageName = switchAnim(0, 1, 2, getAnimImages());
		}
		setImage(imageName);
		currentImageFile=imageName;
	}
	
	private String switchAnim(int still, int start, int end, String[] anim){
		if(isAnim(anim)) {
			if(count < 25)
				return anim[start];
			else
				return anim[end];
		}
		else {
			return anim[still];
		}
	}
	
	public void addWeapon(Weapon weapon){
		myWeapons.add(weapon);
	}
	
	public List<Weapon> getWeaponList(){
		return myWeapons;
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
	public void setFacing (int facing){
		myFacing = facing;
		currentImageFile=getAnimImages()[myFacing];
	}
	
	public void setBattleImage(String file){
		Image bimg = new ScaledImage(150,150,file).scaleImage();
		myBattleImage = bimg;
	}
	
	public Image getBattleImage(){
		return myBattleImage;
	}
}
