package authoring;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;

public class SteppableFeature extends Feature{
	private JCheckBox steppableCheck;
	public SteppableFeature(GridObjectCreation gridObjectCreation){
		steppableCheck = new JCheckBox("Steppable?");
		myComponents.put(steppableCheck, BorderLayout.SOUTH);
	}
}
