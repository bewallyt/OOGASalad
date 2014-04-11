package authoring;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

public class GridObjectCreation extends Feature{
	private JButton myGridObjectButton;
	private Set<Feature> myGridFeatures;
	private GridObjectData myData;
	private AuthoringView gridObjectView;
	public GridObjectCreation(){
		myData = new GridObjectData();
		
		myGridFeatures = new HashSet<Feature>();
		//myGridFeatures.add(new EncounterableFeature(this));
		myGridFeatures.add(new SaveGridObjectFeature(this));
		myGridFeatures.add(new SteppableFeature(this));
		myGridFeatures.add(new GridObjectCoordinateFeature(this));
		myGridFeatures.add(new GridObjectImageFeature(this));
		myGridFeatures.add(new TalkableFeature(this));
		myGridFeatures.add(new WidthHeightFeature(this));
		
		myGridObjectButton = new JButton("New GridObject");
		myGridObjectButton.addActionListener(new GridObjectWindowAction());
		myComponents.put(myGridObjectButton, BorderLayout.SOUTH);
	}
	public GridObjectData getData(){
		return myData;
	}
	public AuthoringView getView(){
		return gridObjectView;
	}
	private class GridObjectWindowAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gridObjectView = new AuthoringView(myGridFeatures, false);
		}	
	}
	public Feature getFeature(String s){
		Feature myFeature=null;
		for(Feature f: myGridFeatures){
			if(f.getClass().getSimpleName().equals(s))
				myFeature = f;
		}
		return myFeature;
	}
}