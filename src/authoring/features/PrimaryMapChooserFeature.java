package authoring.features;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * 
 * @author Richard Cao
 * Allows the user the set the map that is loaded on game startup
 */
public class PrimaryMapChooserFeature extends Feature{
	
	private JButton myButton;
	
	public PrimaryMapChooserFeature() {
		myButton = new JButton("Set As Primary Map");
		myButton.addActionListener(new MapChooserListener());
		myComponents.put(myButton, BorderLayout.SOUTH);
	}
	
	public class MapChooserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			FeatureManager.getWorldData().setPrimaryMap(FeatureManager.getWorldData().getCurrentMapName());		
		}
		
	}
}
