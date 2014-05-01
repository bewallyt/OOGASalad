package authoring.gameObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.SpriteImageChooser;

import authoring.features.FeatureManager;
import util.Constants;
/**
 * Generic class that handles information relevant to all grid objects. 
 * All grid objects extend this class
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie, Peter Yom, Brandon Chao
 *
 */
public class GridObjectData {

	protected String myImage;
	public static final int DEFAULT_DIMENSION = 1;
	protected int width = DEFAULT_DIMENSION;
	protected int height = DEFAULT_DIMENSION;

	private int myX;
	private int myY;
	private String myID;
	private NPCResponseNodeData myDialogue;
	/**
	 * List of Objects used so the Player group can utilize reflection in creating GridObjects
	 */
	private List<Object> myArguments = new ArrayList<Object>();

	protected String[] createSpriteImages(String image){
		SpriteImageChooser sprite=new SpriteImageChooser();
		return sprite.getSpriteImages(image);
	}
	
	// BarrierData and HealerData
	public GridObjectData(int x, int y, int width, int height, String image, String id) {
		myID = id;
		myX = x;
		myY = y;

		myArguments.add(width);
		myArguments.add(height);
		if (id.equals("engine.gridobject.person.Healer")) {
			myArguments.add(createSpriteImages(image));
		} else {
			myArguments.add(getGridObjectPathValue(image));
		}
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
	public GridObjectData(int x, int y, int width, int height, String image,
			NPCResponseNodeData root, int movement, String id) {
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
		myArguments.add(movement); // default movement type until given
									// movementType
	}
	
	// ShopkeeperData
	public GridObjectData(int x, int y, int width, int height, String image, List<String> items, String id) {
		myID = id;
		myX = x;
		myY = y;
		
		myArguments.add(width);
		myArguments.add(height);
		myArguments.add(tempArr(image));
		myArguments.add("DEFAULT_NAME");
		myArguments.add(items);
	}
	
	private String[] tempArr(String image){
		String[] ret = {image};
		return ret;
	}
	
	// Base empty constructor
	public GridObjectData() {
	}

	public String getID() {
		return myID;
	}

	public String getImageName() {
		return myImage;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}

	public NPCResponseNodeData getDialogue() {
		return myDialogue;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	protected void setHeight(int x) {
		height = x;
	}

	protected void setWidth(int x) {
		width = x;
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

	public String getImage(){
		return myImage;
	}
	private String getGridObjectPathValue(String s) {
		if (prependGridObjectPath(s)) {
			return Constants.GRIDOBJECTPATH + s;
		} else {
			return s;
		}
	}
}
