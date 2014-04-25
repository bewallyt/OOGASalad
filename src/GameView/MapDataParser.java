package GameView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import engine.gridobject.Barrier;
import engine.gridobject.Door;
import engine.gridobject.GridObject;
import engine.gridobject.person.NPC;
import engine.gridobject.person.Player;
import authoring.GridObjectData;
import authoring.MapData;
import authoring.TileData;

public class MapDataParser {

	private List<GridObject> myGridObjectList;
	private List<String> myTileImageList;
	private MapData myMap;

	protected MapDataParser(MapData map, Player p) {
		myMap = map;
		myGridObjectList = new ArrayList<GridObject>();
		myTileImageList = new ArrayList<String>();
		parseMap(p);
	}

	protected List<GridObject> getGridObjectList() {
		return myGridObjectList;
	}

	protected List<String> getTileImageList() {
		return myTileImageList;
	}

	private void parseMap(Player p) {
		List<GridObjectData> currData = new ArrayList<GridObjectData>();
		for (int i = 0; i < myMap.getMapLength(); i++) {
			for (int j = 0; j < myMap.getMapWidth(); j++) {
				TileData currTile = myMap.getTileData(i, j);
				currData = currTile.getGridObjectDatas();

				for (GridObjectData data : currData) {
					GridObject gridobject = null;
					
					List<Object> myList = data.getArguments();
					System.out.println(myList);

					try {
						String classname = data.getID();
						
						gridobject = (GridObject) Class.forName(classname)
								.getConstructor(List.class).newInstance(data.getArguments());
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (gridobject != null) {
						gridobject.setPosition(i, j);
						myGridObjectList.add(gridobject);
					}
				}
				
				myTileImageList.add(currTile.getImageName());
			}
		}
	}

	private void parseMap2(Player p) {
		List<GridObjectData> currData = new ArrayList<GridObjectData>();
		for (int i = 0; i < myMap.getMapLength(); i++) {
			for (int j = 0; j < myMap.getMapWidth(); j++) {
				TileData currTile = myMap.getTileData(i, j);
				currData = currTile.getGridObjectDatas();

				for (GridObjectData data : currData) {
					GridObject gridobject = null;
					if (data.getID().equals(Constants.BARRIER)) {
						gridobject = new Barrier((String) data.getArguments().get(Constants.IMAGE_CONST), 1, 1);
					}
					if (gridobject != null) {
						gridobject.setPosition(i, j);
						myGridObjectList.add(gridobject);
					}
				}

				myTileImageList.add(currTile.getImageName());
			}
		}
	}


}
