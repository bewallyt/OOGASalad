package authoring;

import java.util.*;

public class MapData {
	
	List<List<TileData>> myTiles;
	
	public MapData(){
		myTiles = new ArrayList<List<TileData>>();
	}
	public TileData getTileData(int row, int col){
		return myTiles.get(row).get(col);
	}
	
	protected void addTileData(int row, int col, TileData td){
		myTiles.get(row).set(col, td);
	}
	
}
