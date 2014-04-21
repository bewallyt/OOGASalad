package authoring;

import java.util.List;

import util.Constants;

public class DoorData extends GridObjectData {
 
	
	private int toX;
	private int toY;
	private String toMap;
	
	public DoorData(int x, int y, String image, int toX, int toY, String toMap) {
		super(x, y, image, toX, toY, toMap, Constants.DOOR);
		this.toX=toX;
		this.toY=toY;
		this.toMap=toMap;
		init();
	}
	
	public int getToX(){
		return toX;
	}
	public int getToY(){
		return toY;
	}
	public String toMap(){
		return toMap;
	}

}
