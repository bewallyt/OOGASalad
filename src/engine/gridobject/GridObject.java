package engine.gridobject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;

import engine.Statistic;
import engine.images.ScaledImage;

public abstract class GridObject{

	protected int myWidth;
	protected int myHeight;
	protected int myX;
	protected int myY;
	protected int myStartX;
	protected int myStartY;
	protected Image myImage;
	private String myImageName;
	private Map<String,Statistic> myStatsMap;
	private boolean doesHarm = false;
	private String facing = "down";
	private int myNumTilesWidth;
	private int myNumTilesHeight;
	
	public GridObject(String image, int numTilesWidth, int numTilesHeight) {
		myStatsMap = null;
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myImageName=image;
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
		myX=myStartX=x;
		myY=myStartY=y;
		
	}
	
	public int[] getPosition(){
		return new int[] {myX,myY};
	}
	public String getImageFile(){
		return myImageName;
	}
	public void setImage(String file) {
		Image img = new ScaledImage(myWidth,myHeight,file).scaleImage();
		myImage = img;
		
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(myImage, myX, myY, null);
	}
	

	public void addStatistic(Statistic stat) {
		myStatsMap.put(stat.getName(), stat);
	}
	
	public void addStatistic(String name, int value, int maxValue){
		myStatsMap.put(name,new Statistic(name,value,maxValue));
	}
	
	public Map<String,Statistic> getStatsMap(){
		return myStatsMap;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(myX, myY, myWidth, myHeight);	
	}
	
	public boolean getDoesHarm(){
		return doesHarm;
	}
	public void setDoesHarm(boolean harm){
		doesHarm=harm;
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
	
	public String getFacing() {
		return facing;
	}
	public void setFacing(String facing) {
		this.facing = facing;
	}
	
}
