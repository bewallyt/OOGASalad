package authoring;
import java.awt.Image;
import java.io.File;
import java.util.*;

public class WorldData {

	private Map<String, MapData> myLevels;
	private Map<String, File> myImages;
    private List<Item> myItems;
    private List<Weapon> myWeapons;
    private PlayerData playData;
	protected static final String DEFAULT_MAP = "defaultworldkey";
	protected static final int DEFAULT_MAP_WIDTH = 30;
	protected static final int DEFAULT_MAP_HEIGHT = 30;
	
	public WorldData(){
		myLevels = new HashMap<String, MapData>();
		myLevels.put(DEFAULT_MAP, new MapData());
		myImages = new HashMap<String, File>();
        myItems = new ArrayList<Item>();
        myWeapons = new ArrayList<Weapon>();
	}
	
	public File getImage(String fileName){
		return myImages.get(fileName);
	}
	
	public void saveImage(String s, File f){
		myImages.put(s, f);
	}

    public List<Item> getMyItems(){ return myItems;}

    public void saveItem(Item it){ myItems.add(it);}

    public PlayerData getPlayData(){
        return playData;
    }

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

    public void saveWeapons(Weapon wep){ myWeapons.add(wep);}

    public List<Weapon> getMyWeapons(){ return myWeapons;}





}
