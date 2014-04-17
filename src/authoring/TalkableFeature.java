package authoring;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JList;

public class TalkableFeature extends Feature {
	private JCheckBox talkableCheck;
	private JList myHistory;
	private NPCResponseNode myRoot;
	private NPCResponseNode myCurrent;
	private GridObjectCreation mySuperFeature;
	public TalkableFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		talkableCheck = new JCheckBox("Talkable?");
		myComponents.put(talkableCheck, BorderLayout.WEST);
	}
	public boolean isTalkable() {
		return talkableCheck.isSelected();
	}
}
