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
	Set<Feature> myGridFeatures;
	AuthoringView gridAuthoringView;
	public GridObjectCreation(){
		myGridFeatures = new HashSet<Feature>();
		myGridFeatures.add(new EncounterableFeature(this));
		myGridFeatures.add(new SaveGridObjectFeature(this));
		myGridFeatures.add(new SteppableFeature(this));
		myGridFeatures.add(new GridObjectCoordinateFeature(this));
		
		myGridObjectButton = new JButton("New GridObject");
		myGridObjectButton.addActionListener(new GridObjectWindowAction());
		myComponents.put(myGridObjectButton, BorderLayout.SOUTH);
	}
	private class GridObjectWindowAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			gridAuthoringView = new AuthoringView(myGridFeatures, false);
		}	
	}
}