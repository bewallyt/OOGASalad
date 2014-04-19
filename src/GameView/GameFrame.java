package GameView;

import java.io.IOException;
import java.util.List;
import engine.gridobject.GridObject;
import engine.gridobject.person.Player;
import engine.world.WalkAroundWorld;
import engine.main.RPGEngine;
import authoring.MapData;
//import authoring.PlayerData;
import authoring.WorldData;
//import Data.DataManager;
import Data.FileStorer;
import util.Constants;

public class GameFrame extends RPGEngine {
	private WorldData myWorldData;
	private FileStorer myData;
	private Player myPlayer;

	public GameFrame() {
		myData = new FileStorer();
	}

	public void initialize(String fileName) {
		try {
			myWorldData = myData.getWorldData(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initializeGame();
	}

	@Override
	public void initializeGame() {
		setInit(true);
		initializeCanvas(Constants.CANVASWIDTH, Constants.CANVASHEIGHT);
		createWorlds();
	}


	/**
	 * Communication between Data and Engine
	 */

	private void createWorlds() {
		createPlayer();

		for(MapData map : myWorldData.getMaps().values()) {

			MapDataParser parser = new MapDataParser(map, myPlayer);
			List<GridObject> gridObjectList = parser.getGridObjectList();
			gridObjectList.add(myPlayer);						
			List<String> TileImageList = parser.getTileImageList();

			// tile size is default. ask engine to take it out of constructor
			WalkAroundWorld currWorld = new WalkAroundWorld(map.getMapLength()*40, map.getMapWidth()*40, myPlayer, Constants.TILE_SIZE, gridObjectList);
			setWorld(currWorld);

			setTileImages(currWorld, TileImageList);
			setGridObjects(currWorld, gridObjectList);
		}
	}

	private void createPlayer() {
//		PlayerData myPlayerData = myWorldData.getPlayData();

		String[] anim = new String[] { "PlayerUp0.png", "PlayerUp1.png",
				"PlayerUp2.png", "PlayerRight0.png", "PlayerRight1.png",
				"PlayerRight2.png", "PlayerDown0.png", "PlayerDown1.png",
				"PlayerDown2.png", "PlayerLeft0.png", "PlayerLeft1.png",
		"PlayerLeft2.png" };

		myPlayer = new Player(anim, 2);
	}

	private void setGridObjects(WalkAroundWorld world, List<GridObject> list) {
		for (GridObject g : list) {
			world.setTileObject(g, g.getX(), g.getY());
			System.out.println("x: "+g.getX()+" y: "+g.getY());
		}
	}

	private void setTileImages(WalkAroundWorld world, List<String> list) {
		int n = 0;
		for(int i = 0; i < world.getTileGridHeight(); i++) {
			for(int j = 0; j < world.getTileGridWidth(); j++) {
				world.setTileImage(j, i, list.get(n));
				n++;
			}
		}
	}

}