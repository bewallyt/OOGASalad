package authoring.gameObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.SpriteImageChooser;

import util.Constants;

public class GridObjectData {

	protected String myImage;
	public static final int DEFAULT_DIMENSION=1;
	protected int width=DEFAULT_DIMENSION;
	protected int height=DEFAULT_DIMENSION;

	private int myX;
	private int myY;
	private String myID;
	private NPCResponseNodeData myDialogue;
	private List<Object> myArguments = new ArrayList<Object>();
	
	protected String[] createSpriteImages(String image){
	    	SpriteImageChooser sprite=new SpriteImageChooser();
			return sprite.getSpriteImages(image);
	    }
	// BarrierData
	public GridObjectData(int x, int y, int width, int height, String image, String id) {
		myID = id;
		myX = x;
		myY = y;
		
		myArguments.add(width);
		myArguments.add(height);
		myArguments.add(getGridObjectPathValue(image));
	}
	
	// DoorData
	public GridObjectData(int x, int y, int width, int height,  String image, int toX, int toY, String toMap,
                          String id) {
		myID = id;
		myX = x;
		myY = y;

		myArguments.add(width);
		myArguments.add(height);
		myArguments.add(getGridObjectPathValue(image));
		myArguments.add(toX);
		myArguments.add(toY);
		myArguments.add(toMap);
	}
	
	// EnemyData
	public GridObjectData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps,
                          int movement, String id) {
		myID = id;
		myX = x;
		myY = y;
		
		myArguments.add(width);
		myArguments.add(height);
		myArguments.add(createSpriteImages(image));
		myArguments.add(name);
		myArguments.add(startVals);
		myArguments.add(weps);
		myArguments.add(weps.length);
		myArguments.add(movement);
	}
	
	// NPCData
	public GridObjectData(int x, int y, int width, int height, String image, NPCResponseNodeData root, String id) {
		myID = id;
		myX = x;
		myY = y;
		myDialogue = root;
		
		myArguments.add(width);
		myArguments.add(height);
		
		//myArguments.add(createSpriteImages("Ash"));

		myArguments.add(createSpriteImages(image));
		myArguments.add("DEFAULT_NAME");
		myArguments.add(root);
		myArguments.add((int) 1); // default movement type until given movementType
	}

    // Base empty constructor
    public GridObjectData() {
    }
    /*
    public void init(){
		FeatureManager.getWorldData().getCurrentMap().getTileData(width,height).addGridObjectData(this);
	}
	*/
	public String getID() {
		return myID;
	}

	public String getImageName(){
		return myImage;
	}
	public int getX(){
		return myX;
	}
	public int getY(){
		return myY;
	}
	public NPCResponseNodeData getDialogue(){
		return myDialogue;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	protected void setHeight(int x){
		height=x;
	}
	protected void setWidth(int x){
		width=x;
	}
	
	public List<Object> getArguments(){
		return myArguments;
	}
	
	private boolean prependGridObjectPath(String s) {
		boolean prepend = true;
		if (s != null) {
			if (s.startsWith(Constants.GRIDOBJECTPATH)) {
				prepend = false;
			} 
		} 
		return prepend;
	}
	
	private String getGridObjectPathValue(String s) {
		if (prependGridObjectPath(s)) {
			return Constants.GRIDOBJECTPATH+s;
		} else {
			return s;
		}
	}
}
