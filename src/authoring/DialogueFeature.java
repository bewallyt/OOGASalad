package authoring;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class DialogueFeature extends Feature{
	private JScrollPane myTextWindow;
	private JList myResponsesWrapper;
	private List<String> myResponses;
	private NPCResponseNode myRoot;
	private NPCResponseNode myCurrent;
	private GridObjectCreation mySuperFeature;
	public DialogueFeature(GridObjectCreation gridObjectCreation) {
		mySuperFeature = gridObjectCreation;
		setResponses();
		myResponses.add(0,myCurrent.getString());
		myResponsesWrapper = new JList(myResponses.toArray());
		myResponsesWrapper.addListSelectionListener(new DialogueClickAction());
		myResponsesWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myTextWindow = new JScrollPane(myResponsesWrapper);
		myTextWindow.setPreferredSize(new Dimension(200,100));
		myComponents.put(myTextWindow, BorderLayout.CENTER);
	}
	private void setResponses(){
		myResponses = new ArrayList<String>();
		for(UserQueryNode q: myCurrent.getChildren()){
			myResponses.add(q.getString());
		}
	}
	private class DialogueClickAction implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public NPCResponseNode getDialogue() {
		return myRoot;
	}
}
