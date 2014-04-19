package GameView;

import java.util.ArrayList;
import java.util.List;

import engine.gridobject.GridObject;
import authoring.GridObjectData;
import authoring.MapData;
import authoring.TileData;

public class MapDataParser {
	private List<GridObject> myGridObjectList;
	private List<String> myTileImageList;
	private MapData myMap;
	
	public MapDataParser(MapData map) {
		myMap = map;
	}
	
	public List<GridObject> getGridObjectList() {
		return myGridObjectList;
	}
	
	public List<String> getTileImageList() {
		return myTileImageList;
	}
	
	private void parseMap() {
		List<GridObjectData> currData = new ArrayList<GridObjectData>();
		for(int i = 0; i < myMap.getMapLength(); i++) {
			for(int j = 0; j < myMap.getMapWidth(); j++) {
				TileData currTile = myMap.getTileData(i, j);
				currData = currTile.getGridObjectDatas();
				
				for(GridObjectData data : currData) {
					GridObject object = null;
					
					// all those if statements
					
					myGridObjectList.add(object);					
					
				}
				
				myTileImageList.add(currTile.getImageName());
				
			}
		}
	}
}
