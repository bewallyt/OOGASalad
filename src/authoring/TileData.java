package authoring;

import java.util.*;

import util.Constants;

public class TileData {
	private String myImageName;
	private List<GridObjectData> myGridObjectDatas;
	
	public TileData(String s) {
		myImageName = Constants.TILEIMAGEPATH+s;
		myGridObjectDatas = new ArrayList<GridObjectData>();
	}
	
	public void setImageName(String s){
		myImageName = Constants.TILEIMAGEPATH+s;
	}

	public String getImageName(){
		return myImageName;
	}
	
	public List<GridObjectData> getGridObjectDatas() {
		return myGridObjectDatas;
	}
	
	protected void addGridObjectData(GridObjectData gd){
		myGridObjectDatas.add(gd);
		System.out.println(myGridObjectDatas);
	}
}
