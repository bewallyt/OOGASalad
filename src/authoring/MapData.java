package authoring;

import java.util.*;

public class MapData {
	
	List<List<TileData>> myTiles;
	
	public MapData(){
		myTiles = new ArrayList<List<TileData>>(100);
		for(int i = 0; i < 100; i++){
			List<TileData> temp = new ArrayList<TileData>();
			for(int j = 0; j < 100; j++){
				temp.add(new TileData(null));
			}
			myTiles.add(i, temp);
		}
	}
	public TileData getTileData(int row, int col){
		return myTiles.get(row).get(col);
	}
	
	protected void addTileData(int row, int col, TileData td){
		myTiles.get(row).set(col, td);
	}
	
	public int getMapWidth(){
		return myTiles.get(0).size();
	}
	
	public int getMapLength(){
		return myTiles.size();
	}
	
}
