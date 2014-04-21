package authoring;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

public class Feature {
	List<JComponent> myComps = new ArrayList<JComponent>();
	Map<JComponent, String> myComponents = new LinkedHashMap<JComponent, String>();
	public Feature(){
		FeatureManager.addFeature(this);
	}
	public Map<JComponent, String> getFeature(){
		return myComponents;
	}
}
