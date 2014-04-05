package authoring;
/*
 * This is a test TileData class
 */

public class TileData {
	private String myImageName;
	private GridObjectData[] myGridObjectDatas;
	public TileData() {
		myImageName = "";
		myGridObjectDatas = null;
	}
	
	public String getImageName(){
		return myImageName;
	}
	
	public GridObjectData[] getGridObjectDatas() {
		return myGridObjectDatas;
	}

}
