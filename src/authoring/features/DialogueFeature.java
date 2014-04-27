package authoring.features;

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

import authoring.UserQueryNodeData;
import authoring.gameObjects.NPCResponseNodeData;

public class DialogueFeature extends Feature{
	private JScrollPane myTextWindow;
	private JList myResponsesWrapper;
	private List<String> myResponses;
	private NPCResponseNodeData myRoot;
	private List<NPCResponseNodeData> myPrev;
	private NPCResponseNodeData myCurrent;
	private int myModIndex;
	private JButton newQueryOption;
	private JButton myGoBack;
	private JFrame frame;
	public DialogueFeature() {
		myPrev = new ArrayList<NPCResponseNodeData>();
		myRoot = new NPCResponseNodeData("Initial NPC Response");
		myCurrent = myRoot;
		myResponsesWrapper = new JList();
		setResponses();
		myResponsesWrapper.addListSelectionListener(new DialogueClickAction());
		myResponsesWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myTextWindow = new JScrollPane(myResponsesWrapper);
		myTextWindow.setPreferredSize(new Dimension(200,100));
		newQueryOption = new JButton("New Query Option");
		newQueryOption.addActionListener(new QueryListener());
		myGoBack = new JButton("Go Up A Level");
		myGoBack.addActionListener(new GoBackListener());
		myComponents.put(myTextWindow, BorderLayout.EAST);
		myComponents.put(newQueryOption, BorderLayout.EAST);
		myComponents.put(myGoBack, BorderLayout.EAST);
	}
	private void setResponses(){
		myResponses = new ArrayList<String>();
		myResponses.add(0,myCurrent.getString());
		for(UserQueryNodeData q: myCurrent.getChildren()){
			myResponses.add("   "+q.getString());
		}
		myResponsesWrapper.setListData(myResponses.toArray());
	}
	public NPCResponseNodeData getDialogue() {
		return myRoot;
	}
	private class QueryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(myCurrent.getChildren().size()<4){
			frame = new JFrame("Input Dialogue");
			frame.add(new DialogueInputPanel(1));
			frame.pack();
			frame.setVisible(true);
			}
		}
	}
	private class GoBackListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(myCurrent!=myRoot){
			myCurrent=myPrev.get(myPrev.size()-1);
			myPrev.remove(myPrev.size()-1);
			setResponses();
			}
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
					myPrev.add(myCurrent);
					myCurrent = myCurrent.getChildren().get(myResponsesWrapper.getSelectedIndex()-1).getChild();
					myResponsesWrapper.removeSelectionInterval(myResponsesWrapper.getSelectedIndex(), myResponsesWrapper.getSelectedIndex());
					setResponses();
				}
			}
		}
	}
	private class DialogueInputPanel extends JPanel{
		private JLabel dialogueLabel;
		
		private JTextArea text;
		private JScrollPane textWrapper;

		private JTextArea secondText;
		private JScrollPane secondTextWrapper;
		
		private JButton confirm;
		private int myNodeType;
		public DialogueInputPanel(int nodeType){
			myNodeType = nodeType;
			dialogueLabel = new JLabel("Enter the desired dialogue.");
			text = new JTextArea(1,40);
			textWrapper = new JScrollPane(text);
			this.add(dialogueLabel);
			this.add(textWrapper);
			if(myNodeType==1){
				secondText = new JTextArea(1,40);
				secondTextWrapper = new JScrollPane(secondText);
				this.add(secondTextWrapper);
			}
			confirm = new JButton("Done");
			confirm.addActionListener(new DoneListener());
			this.add(confirm);
		}
		private class DoneListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				if(myNodeType==0){
				myCurrent.setString(text.getText());
				}
				else{
					UserQueryNodeData uqn = new UserQueryNodeData(text.getText());
					uqn.setChild(new NPCResponseNodeData(secondText.getText()));
					myCurrent.addChild(uqn);
				}
				setResponses();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}
	}
}
