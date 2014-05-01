package authoring.features;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JComponent;
/**
 * Generic Feature class. Can be extended to add multiple distinct features to the GUI
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class Feature {
	Map<JComponent, String> myComponents = new LinkedHashMap<JComponent, String>();
	public Feature(){
		FeatureManager.addFeature(this);
	}
	public Map<JComponent, String> getFeature(){
		return myComponents;
	}
}
