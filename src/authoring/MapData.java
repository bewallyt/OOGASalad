package authoring;

import java.util.*;

public class MapData {
	
	List<List<TileData>> myTiles;
	
	public MapData(){
		myTiles = new ArrayList<List<TileData>>(144);
		for(int i = 0; i < 144; i++){
			List<TileData> temp = new ArrayList<TileData>();
			for(int j = 0; j < 12; j++){
				temp.add(new TileData(null));
			}
			myTiles.add(i, temp);
		}
		System.out.println(myTiles.size());	
	}
	public TileData getTileData(int row, int col){
		return myTiles.get(row).get(col);
	}
	
	protected void addTileData(int row, int col, TileData td){
		myTiles.get(row).set(col, td);
	}
	
}
