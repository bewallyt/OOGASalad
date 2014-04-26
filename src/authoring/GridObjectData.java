package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Constants;

public class GridObjectData {

	protected String myImage;
	public static final int DEFAULT_DIMENSION=1;
	protected int width=DEFAULT_DIMENSION;
	protected int height=DEFAULT_DIMENSION;

	private int myX;
	private int myY;
	private List<Item> itemList=new ArrayList<Item>();
	private String myID;
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
		myArguments.add(Constants.GRIDOBJECTPATH+image);
	}
	
	// DoorData
	public GridObjectData(int x, int y, int width, int height,  String image, int toX, int toY, String toMap,
                          String id) {
		myID = id;
		myX = x;
		myY = y;

		myArguments.add(width);
		myArguments.add(height);
		myArguments.add(Constants.GRIDOBJECTPATH+image);
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
		
		myArguments.add(1);
		myArguments.add(1);
		myArguments.add(Constants.GRIDOBJECTPATH+image);
		myArguments.add(name);
		myArguments.add(startVals);
		myArguments.add(weps);
		myArguments.add(movement);
	}
	
	// NPCData
	public GridObjectData(int x, int y, int width, int height, String image, NPCResponseNode root, String id) {
		myID = id;
		myX = x;
		myY = y;
		
		myArguments.add(width);
		myArguments.add(height);
<<<<<<< HEAD
		myArguments.add(createSpriteImages());
		// myArguments.add(new String[] {Constants.GRIDOBJECTPATH+image, Constants.GRIDOBJECTPATH+image,
		// Constants.GRIDOBJECTPATH+image, Constants.GRIDOBJECTPATH+image});
//		myArguments.add(Constants.GRIDOBJECTPATH+image);
=======
		myArguments.add(Constants.SPRITEPATH+"/"+image+"/");
>>>>>>> e06565bf7dc95268582a2bbd030355d08a6e5db6
		myArguments.add(root);
	}

    // Base empty constructor
    public GridObjectData() {
    }

    public void init(){
		FeatureManager.getWorldData().getCurrentMap().getTileData(width,height).addGridObjectData(this);
	}
	
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
}
