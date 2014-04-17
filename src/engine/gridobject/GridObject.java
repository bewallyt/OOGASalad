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

import engine.Dialogue;
import engine.Statistic;
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
	private String[] myAnimImages;
	private Map<String,Statistic> myStatsMap = new HashMap<String,Statistic>();
	private int myNumTilesWidth;
	private int myNumTilesHeight;
	private List<String> myDialogueList;
	private Dialogue myDialogue;
	private boolean initiateBattle=false;

	public GridObject(String image, int numTilesWidth, int numTilesHeight) {
		myStatsMap = null;
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myImageName=image;
		myDialogue=null;
		myDialogueList=new ArrayList<String>();
	}
	
	public GridObject(String[] animImages, int numTilesWidth, int numTilesHeight) {
		myNumTilesWidth=numTilesWidth;
		myNumTilesHeight = numTilesHeight;
		myAnimImages=animImages;
		myDialogue=null;
		myDialogueList=new ArrayList<String>();
		myImageName=animImages[2];
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
		// can move myStartX/y to just NPC class
		myX=myStartX=x;
		myY=myStartY=y;
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
		//System.out.println("paint");
	}
	

	public void paintDialoge(Graphics2D g, int xSize, int ySize, int xOffset, int yOffset) {
		if(myDialogue!=null){
			myDialogue.setSize((int) (xSize*.9), ySize/4);
			g.drawImage(myDialogue.getImage(),(int) (xSize*.05),(int) (ySize-ySize/4-ySize*.1),null);
			InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
			Font font=null;
			try {
				try {
					font = Font.createFont(Font.TRUETYPE_FONT, is);
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Font sizedFont = font.deriveFont(16f);
				g.setFont(sizedFont);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			g.drawString(myDialogue.getDialogue(), (int) (xSize*.1), (int) (ySize-ySize/4));
		}
	}


	public void addStatistic(Statistic stat) {
		System.out.println(myStatsMap);
		myStatsMap.put(stat.getName(), stat);
	}

	public void addStatistic(String name, int value,int maxValue){
		myStatsMap.put(name,new Statistic(name,value,maxValue));
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

	public Dialogue doDialogue(){
		Dialogue d = null;
		for(String str : myDialogueList){
			d = new Dialogue("Dialogue.png",str);
			System.out.println(str);
		}

		this.myDialogue=d;
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


	public int getStartX(){
		return myStartX;
	}
	public int getStartY(){
		return myStartY;
	}
	public String[] getAnimImages(){
		return myAnimImages;
	}

	public int getNumTilesHeight(){
		return myNumTilesHeight;
	}
	public int getNumTilesWidth(){
		return myNumTilesWidth;
	}
	public void initiateBattle(){
		initiateBattle=true;
	}
	public boolean battleInitiated(){
		if(initiateBattle){
			initiateBattle=false;
			return true;
		}
		return false;
	}
	
	


}
