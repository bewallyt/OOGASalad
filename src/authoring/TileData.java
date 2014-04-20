package authoring;

import java.util.*;

public class TileData {
	private String myImageName;
	private List<GridObject> myGridObjectDatas;
	
	public TileData(String s) {
		myImageName = s;
		myGridObjectDatas = new ArrayList<GridObject>();
	}
	
	public void setImageName(String s){
		myImageName = s;
	}

	public String getImageName(){
		return myImageName;
	}
	
	public List<GridObject> getGridObjectDatas() {
		return myGridObjectDatas;
	}
	
	protected void addGridObjectData(GridObject gd){
		myGridObjectDatas.add(gd);
		System.out.println(myGridObjectDatas);
	}
}
