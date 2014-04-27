package authoring.features;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComponent;

public class Feature {
	Map<JComponent, String> myComponents = new LinkedHashMap<JComponent, String>();
	public Feature(){
		FeatureManager.addFeature(this);
	}
	public Map<JComponent, String> getFeature(){
		return myComponents;
	}
}
