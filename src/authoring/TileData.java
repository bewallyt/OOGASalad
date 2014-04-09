package authoring;

import java.util.*;

public class TileData {
	private String myImageName;
	private List<GridObjectData> myGridObjectDatas;
	
	public TileData(String s) {
		myImageName = s;
		myGridObjectDatas = new ArrayList<GridObjectData>();
	}
	
	public void setImageName(String s){
		myImageName = s;
	}

	public String getImageName(){
		return myImageName;
	}
	
	public List<GridObjectData> getGridObjectDatas() {
		return myGridObjectDatas;
	}
	
	protected void addGridObjectData(GridObjectData gd){
		myGridObjectDatas.add(gd);
	}
}
