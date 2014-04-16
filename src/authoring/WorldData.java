package authoring;
import java.awt.Image;
import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, File> myImages;
    private List<Item> myItems;
    private PlayerData playData;
	protected static final String DEFAULT_MAP = "defaultworldkey";
	protected static final int DEFAULT_MAP_WIDTH = 100;
	protected static final int DEFAULT_MAP_HEIGHT = 100;
	
	public WorldData(){
		myLevels = new HashMap<String, MapData>();
		myLevels.put(DEFAULT_MAP, new MapData());
		myImages = new HashMap<String, File>();
        myItems = new ArrayList<Item>();
		//More general stuff about player, game engine, etc will go here
	}
	
	public File getImage(String fileName){
		return myImages.get(fileName);
	}
	
	public void saveImage(String s, File f){
		myImages.put(s, f);
	}

    public void saveItem(Item it){ myItems.add(it);}

    public void savePlayer(PlayerData player){ playData = player; }
	
	public void addLevel(String s, MapData md){
		myLevels.put(s, md);
	}
	
	public MapData getMap(String s){
		return myLevels.get(s);
	}
	
	protected Map<String, File> getImages(){
		return myImages;
	}

    protected List<Item> getMyItems(){ return myItems;}

    public PlayerData getPlayData(){
        return playData;
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
