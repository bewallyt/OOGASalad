package engine.gridobject.person;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import engine.battle.Weapon;
import engine.dialogue.DialogueDisplayControl;
import engine.dialogue.InteractionBox;
import engine.gridobject.GridObject;
import engine.images.ScaledImage;
import engine.item.Item;

public abstract class Person extends GridObject {

	private List<Item> myItems;
	private double mySpeed;
	private double myDX=0;
	private double myDY=0;
	private int myMaxX;
	private int myMinX;
	private int myMaxY;
	private int myMinY;
	private int myStartX;
	private int myStartY;
	private List<Weapon> myWeapons;
	private Weapon myCurrentWeapon;
	private int myMoney;
	//up=0, right=1, down=2, left=3
	private int myFacing=2;
	private int count = 0;
	private String currentImageFile;
	private Image myBattleImage;
	private DialogueDisplayControl myDialogueDisplayControl;


	public Person(String[] animImages, double speed, int numTilesWidth, int numTilesHeight) {
		super(animImages, numTilesWidth, numTilesHeight);
		mySpeed = speed;
		resetMax();
		myWeapons = new ArrayList<Weapon>();
		myItems = new ArrayList<Item>();
		currentImageFile=getAnimImages()[myFacing];
		myMoney=0;
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

	/**
	 * Adds a weapon to the person's weapon list.
	 *
	 * @param weapon the weapon
	 */
	public void addWeapon(Weapon weapon){
		myWeapons.add(weapon);
		myCurrentWeapon=myWeapons.get(0);
		System.out.println(myWeapons.size());
	}

	public List<Weapon> getWeaponList(){
		return myWeapons;
	}

	/**
	 * Adds the item.
	 *
	 * @param an item to the person's item list
	 */
	public void addItem(Item it) {
		myItems.add(it);
	}

	public List<Item> getItems() {
		return myItems;
	}

	/**
	 * Sets a list of items as the person's item list
	 * @param items
	 */
	public void setMyItems(List<Item> items) {
		myItems = items;
	}

	/**
	 * Removes the item.
	 *
	 * @param it the item to be removed from the item list
	 */
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

	public void setDY(double DY) {
		myDY = DY;
	}

	public double getSpeed() {
		return mySpeed;
	}

	public void setSpeed(double Speed) {
		mySpeed = Speed;
	}

	public double getDX() {
		return myDX;
	}

	public void setDX(double DX) {
		myDX = DX;
	}
	public int getFacing(){
		return myFacing;
	}
	public void setFacing (int facing){
		myFacing = facing;
		currentImageFile=getAnimImages()[myFacing];
	}

	/**
	 * Sets the battle image.
	 *
	 * @param file the new battle image
	 */
	public void setBattleImage(String file){
		Image bimg = new ScaledImage(150,150,file).scaleImage();
		myBattleImage = bimg;
	}

	public Image getBattleImage(){
		return myBattleImage;
	}
	public int getStartX(){
		return myStartX;
	}
	public int getStartY(){
		return myStartY;
	}

	@Override
	public void setPosition(int x, int y){
		super.setPosition(x, y);
		myStartX=x; myStartY=y;
	}
	public void incrementY(double myDY) {
		super.setPosition(getX(), (int) (getY()+myDY));
	}

	public void incrementX(double myDX) {
		super.setPosition((int) (getX()+myDX), getY());
	}
	
	/**
	 * Allows for the DialogueDisplayContorl to be updated when a World is changed.
	 * 
	 * @param ddc the DialogueDisplayControl
	 */
	public void setDialogueDisplayControl(DialogueDisplayControl ddc) {
		myDialogueDisplayControl = ddc;
	}
	
	public DialogueDisplayControl getDialogueDisplayControl() {
		return myDialogueDisplayControl;
	}
	
	public void setInteractionBox(InteractionBox box) {
		myDialogueDisplayControl.setInteractionBox(box);
	}
	public void setCurrentWeapon(Weapon weapon){
		myCurrentWeapon=weapon;
	}
	public Weapon getCurrentWeapon(){
		return myCurrentWeapon;
	}
	
	public int getMoney(){
		return myMoney;
	}
	public void changeMoney (int amountToChange){
		myMoney+=amountToChange;
	}
	
}
