package authoring;
import java.util.*;

public class Main {
	public static void main(String[] args){
		List myFeatures = new ArrayList<Feature>();
		myFeatures.add(new GridViewerFeature());
		myFeatures.add(new ImageChooser());
		myFeatures.add(new SaveGameFeature());
		myFeatures.add(new MapCreatorFeature());
		myFeatures.add(new PrimaryMapChooserFeature());
        myFeatures.add(new AddGameComponents());
		
		AuthoringView view = new AuthoringView(myFeatures, true);
	}
}