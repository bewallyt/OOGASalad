package authoring.features;
import java.util.ArrayList;
import java.util.List;

import authoring.gameObjects.WeaponItemViewer;
import authoring.gameObjects.WorldData;
import authoring.view.TileImageEditor;
import Data.FileStorer;

/**
 * Class that manages the existing features in the project. 
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class FeatureManager {
	public static List<Feature> myFeatures = new ArrayList<Feature>();
	public static WorldData myWorld = new WorldData();
    public static WeaponItemViewer weaponItemViewer = new WeaponItemViewer();
	public static TileImageEditor tileEditor = new TileImageEditor();
	public static FileStorer myDataManager = new FileStorer();
	public static void addFeature(Feature f){
		myFeatures.add(f);
	}
	/**
	 * Returns the feature with the given name
	 * @param s Name of feature
	 */
	public static Feature getFeature(String s){
		Feature myFeature=null;
		for(Feature f: myFeatures){
			if(f.getClass().getSimpleName().equals(s))
				myFeature = f;
		}
		return myFeature;
	}
	/**
	 * Returns the worldData instance the game is being saved to
	 */
	public static WorldData getWorldData(){
		return myWorld;
	}
	/**
	 * Returns the FileStorer doing the game saving
	 */
	public static FileStorer getDataManager(){
		return myDataManager;
	}
    public static WeaponItemViewer getWeaponItemViewer(){return weaponItemViewer;}
}
