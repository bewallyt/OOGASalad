package GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.item.Weapon;
import authoring.gameObjects.GridObjectData;
import authoring.gameObjects.MapData;
import authoring.gameObjects.NPCData;
import authoring.gameObjects.NPCResponseNodeData;
import authoring.gameObjects.TileData;
import authoring.gameObjects.ItemData;

/**
 * Class used to parse an instance of MapData into the GridObjects and Tile
 * images that MapData contains.
 * 
 * @author Brandon
 * 
 */
public class MapDataParser {

	private List<GridObject> myGridObjectList;
	private List<String> myTileImageList;
	private MapData myMap;

	protected MapDataParser(MapData map, Player p, Map<String, Weapon> weapons, Map<String, ItemData> items) {
		myMap = map;
		myGridObjectList = new ArrayList<GridObject>();
		myTileImageList = new ArrayList<String>();
		parseMap(p, weapons, items);
	}

	protected List<GridObject> getGridObjectList() {
		return myGridObjectList;
	}

	protected List<String> getTileImageList() {
		return myTileImageList;
	}

	/**
	 * Parses myMap into a list of GridObjects and sets position of GridObjects,
	 * GridObjects are created using their String ID using Reflection. Also,
	 * adds images for each tile to a list.
	 * 
	 * @param p
	 *            Player
	 */
	private void parseMap(Player p, Map<String, Weapon> weapons, Map<String, ItemData> items) {
		List<GridObjectData> currData = new ArrayList<GridObjectData>();
		for (int i = 0; i < myMap.getMapLength(); i++) {
			for (int j = 0; j < myMap.getMapWidth(); j++) {
				TileData currTile = myMap.getTileData(i, j);
				currData = currTile.getGridObjectDatas();

				for (GridObjectData data : currData) {
					GridObject gridobject = null;
					
//					if (data instanceof NPCData){
					if(data.getID().equals("engine.gridobject.person.NPC")){
						NPCResponseNodeData node = data.getDialogue();
						data.getArguments().add(node);
					}
					
					
					data.getArguments().add(p);
					data.getArguments().add(items);
					data.getArguments().add(weapons);
					
//					System.out.println("data size "+ data.getArguments().size());

					try {
						gridobject = (GridObject) Class.forName(data.getID())
								.getConstructor(List.class)
								.newInstance(data.getArguments());
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
}
