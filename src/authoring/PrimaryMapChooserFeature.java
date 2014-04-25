package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PrimaryMapChooserFeature extends Feature{
	
	private JButton myButton;
	
	public PrimaryMapChooserFeature() {
		myButton = new JButton("Make this Primary Map");
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
