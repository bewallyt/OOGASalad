import java.util.ArrayList;
import java.util.List;


public class FeatureManager {
	public static List<Feature> myFeatures = new ArrayList<Feature>();
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
}
