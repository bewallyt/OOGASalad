package engine.gridobject;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.Dialogue;
import engine.Statistic;
import engine.gridobject.person.Player;
import engine.images.ScaledImage;

public abstract class GridObject{

	private int myWidth;
	private int myHeight;
	private int myX;
	private int myY;
	private int myStartX;
	private int myStartY;
	private Image myImage;
	private String myImageName;
	//	private String[] myAnimImages;
	private Map<String,Statistic> myStatsMap;
	private boolean doesHarm = false;
	private int xFacing = 0;
	private int yFacing = 0;
	private int myNumTilesWidth;
	private int myNumTilesHeight;
	private List<String> myDialogue;
	private Dialogue d;

	public GridObject(String image, int numTilesWidth, int numTilesHeight) {
		myStatsMap = null;
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myImageName=image;
		d=null;
		myDialogue=new ArrayList<String>();
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

	public void paint(Graphics2D g, int xOff, int yOff) {
		g.drawImage(myImage, myX-xOff, myY-yOff, null);

		drawDialoge(g);
	}

	private void drawDialoge(Graphics2D g) {
		if(d!=null){
			g.drawImage(d.getImage(),50,500,null);
			InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
			Font font=null;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
				Font sizedFont = font.deriveFont(16f);
				g.setFont(sizedFont);
			} catch (FontFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.drawString(d.getDialogue(), 80, 550);
		}
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

	public void addDialogue(String dialogue){
		myDialogue.add(dialogue);
	}
	public List<String> getDialogueList(){
		return myDialogue;

	}

	public Dialogue doDialogue(){
		System.out.println("hi");
		Dialogue d = null;
		for(String str : myDialogue){
			d = new Dialogue("Dialogue.png",str);
		}

		this.d=d;
		return d;
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

	public void incrementY(double myDY) {
		myY += myDY;
	}

	public void incrementX(double myDX) {
		myX += myDX;
	}

	public int getXFacing() {
		return xFacing;
	}
	public int getYFacing() {
		return yFacing;
	}

	public void setXFacing(int facing) {
		xFacing = facing;
	}
	public void setYFacing(int facing){
		yFacing=facing;
	}
	public int getStartX(){
		return myStartX;
	}
	public int getStartY(){
		return myStartY;
	}



}
