package authoring;
import java.util.ArrayList;
import java.util.List;

import Data.DataManager;
import Data.FileStorer;


public class FeatureManager {
	public static List<Feature> myFeatures = new ArrayList<Feature>();
	public static WorldData myWorld = new WorldData();
	public static TileImageEditor imageEditor = new TileImageEditor();
	public static FileStorer myDataManager = new FileStorer();
	public static void addFeature(Feature f){
		myFeatures.add(f);
	}
	public static Feature getFeature(String s){
		Feature myFeature=null;
		for(Feature f: myFeatures){
			if(f.getClass().getName()==s)
				myFeature = f;
		}
		return myFeature;
	}
	public static WorldData getWorldData(){
		return myWorld;
	}
	public static FileStorer getDataManager(){
		return myDataManager;
	}
}
