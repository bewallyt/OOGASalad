package authoring;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
	private int myModIndex;
	private JButton newQueryOption;
	private JFrame frame;
	private GridObjectCreation mySuperFeature;
	public DialogueFeature(GridObjectCreation gridObjectCreation) {
		mySuperFeature = gridObjectCreation;
		myRoot = new NPCResponseNode("Initial NPC Response");
		myCurrent = myRoot;
		myResponsesWrapper = new JList();
		setResponses();
		myResponsesWrapper.addListSelectionListener(new DialogueClickAction());
		myResponsesWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myTextWindow = new JScrollPane(myResponsesWrapper);
		myTextWindow.setPreferredSize(new Dimension(200,100));
		newQueryOption = new JButton("New Query Option");
		newQueryOption.addActionListener(new QueryListener());
		myComponents.put(myTextWindow, BorderLayout.CENTER);
		myComponents.put(newQueryOption, BorderLayout.CENTER);
	}
	private void setResponses(){
		myResponses = new ArrayList<String>();
		myResponses.add(0,myCurrent.getString());
		for(UserQueryNode q: myCurrent.getChildren()){
			myResponses.add("   "+q.getString());
		}
		myResponsesWrapper.setListData(myResponses.toArray());
	}
	public NPCResponseNode getDialogue() {
		return myRoot;
	}
	private class QueryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frame = new JFrame("Input Dialogue");
			frame.add(new DialogueInputPanel(1));
			frame.pack();
			frame.setVisible(true);
		}
	}
	private class DialogueClickAction implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			if(myResponsesWrapper.getSelectedValue()!=null){
				if(myResponsesWrapper.getSelectedIndex()==0){
					frame = new JFrame("Input Dialogue");
					frame.add(new DialogueInputPanel(0));
					frame.pack();
					frame.setVisible(true);
					myResponsesWrapper.removeSelectionInterval(myResponsesWrapper.getSelectedIndex(), myResponsesWrapper.getSelectedIndex());
				}
				else{
					frame = new JFrame("Input Dialogue");
					frame.add(new DialogueInputPanel(0));
					frame.pack();
					frame.setVisible(true);
					myResponsesWrapper.removeSelectionInterval(myResponsesWrapper.getSelectedIndex(), myResponsesWrapper.getSelectedIndex());
				}
			}
		}
	}
	private class DialogueInputPanel extends JPanel{
		private JLabel dialogueLabel;
		
		private JTextArea text;
		private JScrollPane textWrapper;
		
		private JButton confirm;
		private int myNodeType;
		public DialogueInputPanel(int nodeType){
			myNodeType = nodeType;
			dialogueLabel = new JLabel("Enter the desired dialogue.");
			text = new JTextArea(1,40);
			textWrapper = new JScrollPane(text);
			confirm = new JButton("Done");
			confirm.addActionListener(new DoneListener());
			this.add(dialogueLabel);
			this.add(textWrapper);
			this.add(confirm);
		}
		private class DoneListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				if(myNodeType==0){
				myCurrent.setString(text.getText());
				}
				else{
					myCurrent.addChild(new UserQueryNode(text.getText(), myCurrent));
				}
				setResponses();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
}
