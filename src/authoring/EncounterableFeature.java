package authoring;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EncounterableFeature extends Feature{
	private JCheckBox encounterableCheck;
	
	private JTextArea encounterRate;
	private JScrollPane encounterRateWrapper;
<<<<<<< HEAD
	public EncounterableFeature(GridObjectCreation gridObjectCreation){
=======
	
	private GridObjectCreation mySuperFeature;
	public EncounterableFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
>>>>>>> authoring
		encounterableCheck = new JCheckBox("Encounterable?");
		myComponents.put(encounterableCheck, BorderLayout.SOUTH);
		
		encounterRate = new JTextArea(1,3);
		encounterRateWrapper = new JScrollPane(encounterRate);
		myComponents.put(encounterRateWrapper,BorderLayout.SOUTH);
	}
}
