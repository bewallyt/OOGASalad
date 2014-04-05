package authoring;

import java.awt.BorderLayout;
import java.util.*;
import javax.swing.*;

public class AuthoringView {
	public static final String MENU_BAR = "menu bar";
	
	private static JFrame myFrame;
	private Map<String, JComponent> myPanels;
	private Set<Feature> myFeatures;
	
	public AuthoringView(Set<Feature> features){
		myFeatures = features;
		instantiateObjects();
		addAllFeatures();
		setUpFrame();
	}
	private void instantiateObjects() {
        myPanels = new HashMap<String, JComponent>();
		addWorkspaceObjects();
	}
	private void addWorkspaceObjects(){
		myFrame = new JFrame("OOGASalad Authoring");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPanels=new HashMap<String, JComponent>();
		myFrame.setJMenuBar((JMenuBar)myPanels.get(MENU_BAR));
		instantiatePanels();
	}
	private void instantiatePanels() {
		myPanels.put(BorderLayout.NORTH, new JPanel());
		myPanels.put(BorderLayout.SOUTH, new JPanel());
		myPanels.put(BorderLayout.WEST, new JPanel());
		myPanels.put(BorderLayout.EAST, new JPanel());
		myPanels.put(BorderLayout.CENTER, new JPanel());
		myPanels.put(MENU_BAR, new JMenuBar());
	}
	private void addAllFeatures() {
		for(Feature f: myFeatures){
			addFeature(f);
		}
	}
	private void addFeature(Feature f) {
		for(JComponent j: f.getFeature().keySet()){
			myPanels.get(f.getFeature().get(j)).add(j);
		}
	}
	private void setUpFrame() {
		myFrame.setJMenuBar((JMenuBar)myPanels.get(MENU_BAR));
		for(String s: myPanels.keySet()){
			if(myPanels.get(s) instanceof JPanel)
				myFrame.add(myPanels.get(s), s);
		}
		myFrame.pack();
		myFrame.setVisible(true);
	}
}
