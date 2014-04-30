package engine.gridobject;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import util.Constants;
import engine.Statistic;
import engine.dialogue.DialogueDisplayControl;
import engine.dialogue.InteractionBox;
import engine.images.ScaledImage;
import engine.item.Item;
import engine.item.Pickupable;
import engine.item.Weapon;

public abstract class GridObject{

	private int myWidth;
	private int myHeight;
	private int myX;
	private int myY;

	private Image myImage;
	private String myImageName;
	private String myAnimImagesPath;
	private String[] myAnimImages;
	private Map<String,Statistic> myStatsMap = new HashMap<String,Statistic>();
	private int myNumTilesWidth;
	private int myNumTilesHeight;
	private List<String> myDialogueList;
	private boolean initiateBattle=false;
	private Pickupable myPickupable;
	
	private DialogueDisplayControl myDialogueDisplayControl;


	/**
	 * Instantiates a new grid object.
	 *
	 * @param image the image file
	 * @param numTilesWidth the width of the object in tiles
	 * @param numTilesHeight the height of the object in tiles
	 */
	public GridObject(String image, int numTilesWidth, int numTilesHeight) {
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myImageName=image;
		myDialogueList=new ArrayList<String>();
	}
	
	/**
	 * Instantiates a new grid object.
	 *
	 * @param animImages the anim images (12 are needed)
	 * @param numTilesWidth the width of the object in tiles
	 * @param numTilesHeight the height of the object in tiles
	 */
	public GridObject(String[] animImages, int numTilesWidth, int numTilesHeight) {
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myAnimImages=animImages;
		myDialogueList=new ArrayList<String>();
		myImageName=animImages[2];
	}
	
	public GridObject(String animImagesPath, Boolean isAnim, int numTilesWidth, int numTilesHeight) {
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myAnimImagesPath = animImagesPath;
		myAnimImages=getAnimImages();
		myDialogueList=new ArrayList<String>();
		myImageName=myAnimImages[2];	
	}
	
	public GridObject(){
		
	}

	public int[] getNumTiles(){
		return new int[] {myNumTilesWidth, myNumTilesHeight};
	}

	public void setSize(int width, int height){
		myWidth=width;
		myHeight=height;
	}
	public int[] getSize(){
		return new int[] {myWidth,myHeight};
	}
	public void setPosition(int x, int y){
		myX=x;
		myY=y;
	}

	public int[] getPosition(){
		return new int[] {myX,myY};
	}
	public Image getImage(){
		return myImage;
	}
	
	public String getImageFile(){
		return myImageName;
	}
	
	public void setImage(String file) {
		Image img = new ScaledImage(myWidth,myHeight,file).scaleImage();
		myImage = img;	
	}

	public void paint(Graphics2D g, int xOff, int yOff) {
		g.drawImage(myImage, myX-xOff, myY-yOff, null);
	}

	/**
	 * Adds a statistic to the player's statsmap.
	 *
	 * @param stat the statistic
	 */
	public void addStatistic(Statistic stat) {
		System.out.println(myStatsMap);
		myStatsMap.put(stat.getName(), stat);
	}

	/**
	 * Adds a statistic to the player's statsmap.
	 *
	 * @param name the name
	 * @param value the value
	 * @param maxValue the max value
	 */
	public void addStatistic(String name, int value,int maxValue){
		myStatsMap.put(name,new Statistic(name,value,maxValue));
	}
	
	public void addAllStatistics(Map<String, Double> startVals){
		for(String stat : startVals.keySet()){
			myStatsMap.put(stat, new Statistic(stat, startVals.get(stat).intValue(), Constants.DEF_MAX_STAT));
		}
	}

	public Map<String,Statistic> getStatsMap(){
		return myStatsMap;
	}

	public Rectangle getBounds() {
		return new Rectangle(myX, myY, myWidth, myHeight);	
	}

	public void addDialogue(String dialogue){
		myDialogueList.add(dialogue);
	}
	public List<String> getDialogueList(){
		return myDialogueList;

	}
	
	public void doAction(){
		doDialogue();
	}

	public void doDialogue() {
		
	}

	public void move() {}; // default is to do nothing
	public void doCollision(GridObject o){};
	public void uniqueMove(){}

	public int getX() {
		return myX;
	}
	public int getY() {
		return myY;
	}

	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}
	
	public void setPickupable(Pickupable pickupable){
		myPickupable=pickupable;
	}
	
	public Pickupable getPickupable(){
		return myPickupable;
	}


	public String[] getAnimImages(){
		return myAnimImages;
	}
	
	private String[] makeAnimImages(){
		String[] animTemp = new String[Constants.ANIMIMAGES.length];
		
		for(int i = 0; i < animTemp.length; i++){
			animTemp[i]=myAnimImagesPath+Constants.ANIMIMAGES[i];
		}
		
		return myAnimImages;
	}

	public int getNumTilesHeight(){
		return myNumTilesHeight;
	}
	public int getNumTilesWidth(){
		return myNumTilesWidth;
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
	
}
