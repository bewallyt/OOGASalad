package authoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Constants;

public class GridObjectData {

	protected String myImage;
	protected int x;
	protected int y;
	private List<Item> itemList=new ArrayList<Item>();
	private String myID;
	private List<Object> myArguments = new ArrayList<Object>();
	
	// BarrierData
	public GridObjectData(int x, int y, String image, String id) {

		myArguments.add(id);
		myArguments.add(x);
		myArguments.add(y);
		myArguments.add(image);
	}
	
	// DoorData
	public GridObjectData(int x, int y, String image, int toX, int toY, String toMap, String id) {

		myArguments.add(id);
		myArguments.add(x);
		myArguments.add(y);
		myArguments.add(image);
		myArguments.add(toX);
		myArguments.add(toY);
		myArguments.add(toMap);
	}
	
	// EnemyData
	public GridObjectData(int x, int y, String image, String name, Map<String,Integer> startVals, String[] weps, int movement, String id) {
		
	}
	
	// NPCData
	public GridObjectData(int x, int y, String image, NPCResponseNode root, String id) {
		
	}

    // Base empty constructor
    public GridObjectData() {
    }

    public void init(){
		FeatureManager.getWorldData().getCurrentMap().getTileData(x,y).addGridObjectData(this);
	}
	
	public String getID() {
		return myID;
	}

	public String getImageName(){
		return myImage;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void addItem(Item i){
		
	}
	
	public List<Object> getArguments(){
		return myArguments;
	}
}
