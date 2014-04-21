package GameView;

import java.util.ArrayList;
import java.util.List;

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

	public MapDataParser(MapData map, Player p) {
		myMap = map;
		myGridObjectList = new ArrayList<GridObject>();
		myTileImageList = new ArrayList<String>();
		parseMap(p);
	}

	public List<GridObject> getGridObjectList() {
		return myGridObjectList;
	}

	public List<String> getTileImageList() {
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
					if (data.getID().equals("Barrier")) {
						gridobject = new Barrier(data.getImageName(),
								data.getWidth(), data.getHeight());
					} else if (data.getID().equals("Door")) {
						gridobject = new Door(data.getImageName(),
								data.getWidth(), data.getHeight());
					} else if (data.getID().equals("NPC")) {
						gridobject = new NPC(
								new String[] { data.getImageName() },
								data.getSpeed(), data.getWidth(),
								data.getHeight(), data.getMovementType(), p);
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

					gridobject = (GridObject) Reflection.createInstance(data.getID(), data.getArguments());
					
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
