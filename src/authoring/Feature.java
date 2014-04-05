package authoring;


import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

public class Feature {
	Map<JComponent, String> myComponents = new HashMap<JComponent, String>();
	public Feature(){
		FeatureManager.addFeature(this);
	}
	public Map<JComponent, String> getFeature(){
		return myComponents;
	}
}
