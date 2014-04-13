package authoring;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
	private JFrame frame;
	public DialogueFeature(GridObjectCreation gridObjectCreation) {
		mySuperFeature = gridObjectCreation;
		myRoot = new NPCResponseNode("Enter NPCResponse Here");
		myCurrent = myRoot;
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
	public NPCResponseNode getDialogue() {
		return myRoot;
	}
	private class DialogueClickAction implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			if(myResponsesWrapper.getSelectedValue()!=null){
				frame = new JFrame("Input Dialogue");
				frame.add(new DialogueInputPanel());
	            frame.pack();
	            frame.setVisible(true);
				myResponsesWrapper.removeSelectionInterval(myResponsesWrapper.getSelectedIndex(), myResponsesWrapper.getSelectedIndex());
			}
		}
		
		private class DialogueInputPanel extends JPanel{
			private JLabel dialogueLabel;
			
			private JTextArea text;
			private JScrollPane textWrapper;
			public DialogueInputPanel(){
				dialogueLabel = new JLabel("Enter the desired dialogue.");
				text = new JTextArea(1,40);
				textWrapper = new JScrollPane(text);
				this.add(dialogueLabel);
				this.add(textWrapper);
			}
		}
	}
}
