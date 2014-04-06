package authoring;
import java.awt.Image;
import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, Image> myImages;
	private static final String DEFAULT_MAP = "defaultworldkey";
	
	public WorldData(){
		myLevels = new HashMap<String, MapData>();
		myLevels.put(DEFAULT_MAP, new MapData());
		myImages = new HashMap<String, Image>();
		//More general stuff about player, game engine, etc will go here
	}
	
	public Image getImage(String fileName){
		return myImages.get(fileName);
	}
	
	public void saveImage(String s, Image i){
		myImages.put(s, i);
	}
	
	public MapData getMap(String s){
		return myLevels.get(s);
	}
	
	protected Map<String, Image> getImages(){
		return myImages;
	}
	
	/*private TileData[][] myTileDatas;
	private int myWorldWidth;
	private int myWorldHeight;
	private PlayerData myPlayerData;
	
	public WorldData(int width, int height) {
		myWorldWidth = width;
		myWorldHeight = height;
		myPlayerData = new PlayerData();
		myTileDatas = new TileData[myWorldWidth][myWorldHeight];
	}
	
	public TileData getTileData(int x, int y) {
		return myTileDatas[x][y];
	}
	
	public int[] getWorldSize(){
		return new int[]{myWorldWidth, myWorldHeight};
	}
	
	public PlayerData getPlayerData(){		
		String[] animImages = new String[12];
		animImages[0] = "fs.png";
		animImages[3] = "ls.png";
		animImages[6] = "bs.png";
		animImages[9] = "rs.png";
		
		myPlayerData.setMyImage("blank");
		myPlayerData.setMyAnimImages(animImages);
		System.out.println(myPlayerData.getMyImage());
		return myPlayerData;
	}*/

}
