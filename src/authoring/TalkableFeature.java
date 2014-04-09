package authoring;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;

public class TalkableFeature extends Feature {
	private JCheckBox talkableCheck;
	private GridObjectCreation mySuperFeature;
	public TalkableFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		talkableCheck = new JCheckBox("Talkable?");
		myComponents.put(talkableCheck, BorderLayout.EAST);
	}
	public boolean isTalkable() {
		return talkableCheck.isSelected();
	}
}
