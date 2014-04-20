package authoring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GridObjectData {

	private String myImageName;
	private String myID;
	private String[] myImageList;
	private Boolean isSteppable;
	private Boolean isTalkable;
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private int movementType;
	private NPCResponseNode myRoot;
	private List<Object> myArguments = new ArrayList<Object>();

	public GridObjectData() {
		x = -1;
		y = -1;
		
		myArguments.add(myImageName);
		myArguments.add(myImageList);
		myArguments.add(width);
		myArguments.add(height);
		myArguments.add(speed);
		myArguments.add(movementType);
		
	}

	public void init(){
		FeatureManager.getWorldData().getCurrentMap().getTileData(x,y).addGridObjectData(this);
	}
//	public GridObjectData(TileData td, boolean step, boolean talk, String s) {
//		myTile = td;
//		td.addGridObjectData(this);
//		isSteppable = step;
//		isTalkable = talk;
//		myImageName = s;
//	}
	public Boolean isSteppable(){

		return isSteppable;
	}

	public Boolean isTalkable() {
		return isTalkable;
	}

	public String getImageName() {
		return myImageName;
	}

	public String getID() {
		if (!isSteppable)
			return "Barrier";
		else if (isTalkable)
			return "Rule Follower";
		return null;
	}

	public void setX(int xx) {
		x = xx;
	}

	public void setY(int yy) {
		y = yy;
	}

	public void setDialogue(NPCResponseNode root) {
		myRoot = root;
	}

	public void setSteppable(boolean b) {
		isSteppable = b;
	}

	public void setTalkable(boolean b) {
		isTalkable = b;
	}

	public void setImageName(String s) {
		myImageName = s;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public NPCResponseNode getDialogue() {
		return myRoot;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isDefined() {
		return !(x == -1 || y == -1);
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public int getMovementType(){
		return movementType;
	}
	
	public List<Object> getArguments(){
		return myArguments;
	}
}
