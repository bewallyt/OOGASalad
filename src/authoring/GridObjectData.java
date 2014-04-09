package authoring;

import java.io.File;

public class GridObjectData {
	
	private TileData myTile;
	private String myImageName;
	private Boolean isSteppable;
	private Boolean isTalkable;
	
	public GridObjectData(TileData td, boolean step, boolean talk, String s) {
		myTile = td;
		td.addGridObjectData(this);
		isSteppable = step;
		isTalkable = talk;
		myImageName = s;
	}
	
	public Boolean isSteppable(){
		return isSteppable;
	}
	
	public Boolean isTalkable(){
		return isTalkable;
	}
	
	public TileData getTileData(){
		return myTile;
	}
	
	public String getImageName(){
		return myImageName;
	}

}
