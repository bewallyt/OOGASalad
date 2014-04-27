package authoring;
import java.util.*;

import authoring.features.AddGameComponents;
import authoring.features.AddMusicFeature;
import authoring.features.Feature;
import authoring.features.GridViewerFeature;
import authoring.features.ImageChooser;
import authoring.features.MapCreatorFeature;
import authoring.features.PrimaryMapChooserFeature;
import authoring.features.RunGameFeature;
import authoring.features.SaveGameFeature;


public class Main {
	public static void main(String[] args){
		List<Feature> myFeatures = new ArrayList<Feature>();
		myFeatures.add(new GridViewerFeature());
        myFeatures.add(new SaveGameFeature());
		myFeatures.add(new MapCreatorFeature());
		myFeatures.add(new PrimaryMapChooserFeature());
		myFeatures.add(new AddMusicFeature());
        myFeatures.add(new ImageChooser());
        myFeatures.add(new AddGameComponents());
        myFeatures.add(new RunGameFeature());
		
		AuthoringView view = new AuthoringView(myFeatures, true);
	}
}