package authoring;

import java.util.ArrayList;
import java.util.List;

public class GridObjectData {

	protected String myImage;
	protected int x;
	protected int y;
	private List<Item> itemList=new ArrayList<Item>();
	private String myID;
	private List<Object> myArguments;
	
	public GridObjectData(int x, int y, String image, String id) {
		this.x=x;
		this.y=y;
		myImage=image;
		myID = id;
	}
	
	public GridObjectData(List<Object> arguments) {
		myArguments = arguments;
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
