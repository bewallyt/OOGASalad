package authoring;

import java.util.*;

import Data.ImageFile;

public class TileData {
	private ImageFile myImage;
	private List<GridObjectData> myGridObjectDatas;
	
	public TileData(ImageFile image) {
		myImage = image;
		myGridObjectDatas = new ArrayList<GridObjectData>();
	}
	
	public void setImageName(String s){
		myImage.changeName(s);;
	}

	public String getImageName(){
		return myImage.getName();
	}
	
	public List<GridObjectData> getGridObjectDatas() {
		return myGridObjectDatas;
	}
	
	protected void addGridObjectData(GridObjectData gd){
		myGridObjectDatas.add(gd);
	}
}
