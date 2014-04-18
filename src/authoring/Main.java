package authoring;
import java.util.*;

public class Main {
	public static void main(String[] args){
		Set myFeatures = new HashSet<Feature>();
		myFeatures.add(new GridViewerFeature());
     	myFeatures.add(new GridObjectCreation());
		myFeatures.add(new ImageChooser());
        myFeatures.add(new ItemCreation());
        myFeatures.add(new PlayerCreation());
		myFeatures.add(new SaveGameFeature());
		myFeatures.add(new MapCreatorFeature());
		myFeatures.add(new ObjectiveFeature());
		
		AuthoringView view = new AuthoringView(myFeatures, true);
	}
}