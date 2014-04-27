package authoring.gameObjects;

import java.util.*;

import util.Constants;

public class TileData {
	private String myImageName;
	private List<GridObjectData> myGridObjectDatas;
	
	public TileData(String s) {
		if (prependTileImagePath(s)) {
			myImageName = Constants.TILEIMAGEPATH+s;
		} else {
			myImageName = s;
		}
		myGridObjectDatas = new ArrayList<GridObjectData>();
	}
	
	public void setImageName(String s){
		if (prependTileImagePath(s)) {
			myImageName = Constants.TILEIMAGEPATH+s;
		} else {
			myImageName = s;
		}
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
	
	private boolean prependTileImagePath(String s) {
		boolean prepend = true;
		if (s != null) {
			if (s.startsWith(Constants.TILEIMAGEPATH)) {
				prepend = false;
			} 
		} 
		return prepend;
	}
}
