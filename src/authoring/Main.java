package authoring;
import java.util.*;

public class Main {
	public static void main(String[] args){
		Set myFeatures = new HashSet<Feature>();
		//myFeatures.add(new GridEditor());
		myFeatures.add(new Grid());
		myFeatures.add(new GridObjectCreation());
		myFeatures.add(new ImageChooser());
		myFeatures.add(new SaveGameFeature());

		AuthoringView view = new AuthoringView(myFeatures, true);
	}
}