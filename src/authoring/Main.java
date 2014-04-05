package authoring;
import java.util.*;

public class Main {
	public static void main(String[] args){
		Set myFeatures = new HashSet<Feature>();
		// add features here! HelpPageFeature included as an example.
		//myFeatures.add(new HelpPageFeature());
		//myFeatures.add(new GridEditor());
		myFeatures.add(new Grid());
		myFeatures.add(new GridObjectCreation());

		AuthoringView view = new AuthoringView(myFeatures);
	}
}