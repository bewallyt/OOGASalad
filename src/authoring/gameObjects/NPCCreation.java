package authoring.gameObjects;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import authoring.SpringUtilities;
import authoring.UserQueryNodeData;
import authoring.features.FeatureManager;

/**
 * Class that handles the creation of NPCs via a GUI window
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
@SuppressWarnings("ALL")
public class NPCCreation extends CommonAttributes{

	private JList myResponsesWrapper;
	private NPCResponseNodeData myRoot;
	private List<NPCResponseNodeData> myPrev;
	private NPCResponseNodeData myCurrent;
	private JOptionPane optionFrame;
	private JComboBox<String> NPCImage;
	private ButtonGroup movementCheck;

    public NPCCreation(){}

    /**
     * Creates the GUI window to create NPCs
     */
    public void creationPanel(){	
    	JTabbedPane pane = new JTabbedPane();
        String dialogueTab = "Dialogue";

        JPanel namePanel = nameImageFields();
        JPanel nameImagePanel=new JPanel();
        NPCImage=spriteField();
        nameImagePanel.add(namePanel);
        nameImagePanel.add(NPCImage);
        
        JPanel movementPanel = new JPanel();
        movementCheck = new ButtonGroup();
        one = new JCheckBox("1: Side-Side");
        two = new JCheckBox("2: Player Follow");
        three = new JCheckBox("3: Stand-Still");
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        movementCheck.add(one);
        movementCheck.add(two);
        movementCheck.add(three);
        movementPanel.add(one);
        movementPanel.add(two);
        movementPanel.add(three);
        nameImagePanel.add(movementPanel);
        

        JPanel locationPanel = locationFields();
        JPanel sizePanel = sizeFields();
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel,BoxLayout.PAGE_AXIS));
        combinedPanel.add(locationPanel);
        combinedPanel.add(sizePanel);
        
        JPanel dialoguePanel = new JPanel();
		myPrev = new ArrayList<NPCResponseNodeData>();
		myRoot = new NPCResponseNodeData("Initial NPC Response");
		myCurrent = myRoot;
		myResponsesWrapper = new JList();
		setResponses();
		myResponsesWrapper.addListSelectionListener(new DialogueClickAction());
		myResponsesWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane myTextWindow = new JScrollPane(myResponsesWrapper);
		myTextWindow.setPreferredSize(new Dimension(200,100));
		JButton newQueryOption = new JButton("New Query Option");
		newQueryOption.addActionListener(new QueryListener());
		JButton newItemResponse = new JButton("New Item Response");
		newItemResponse.addActionListener(new ItemResponseListener());
		JButton newItemGiven = new JButton("New NPC Giving User Item");
		newItemGiven.addActionListener(new ItemGiveListener());
		JButton myGoBack = new JButton("Go Up A Level");
		myGoBack.addActionListener(new GoBackListener());
		dialoguePanel.add(myTextWindow);
		dialoguePanel.add(newQueryOption);
		dialoguePanel.add(newItemResponse);
		dialoguePanel.add(newItemGiven);
		dialoguePanel.add(myGoBack);

        pane.add(nameTab,nameImagePanel);
        pane.add(locationTab,combinedPanel);
        pane.add(dialogueTab,dialoguePanel);

        frame=new JFrame("New NPC");
        frame.setLayout(new FlowLayout());
        frame.add(pane);
        JButton createNPC=new JButton("Create NPC");
        createNPC.addActionListener(new NPCActionListener());
        frame.add(createNPC);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private class NPCActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			name = itemName.getText();
            x = Integer.parseInt(xcoor.getText());
            y = Integer.parseInt(ycoor.getText());
            makeNPC();
			frame.dispose();
			editor.dispose();
		}
    	
    }

    /**
     * Makes the corresponding NPC based on user input
     */
    private void makeNPC(){
    	NPCData myNPC = new NPCData(x,y,getIntValue(widthField.getText()), getIntValue(heightField.getText()), 
    			(String) NPCImage.getSelectedItem(),myRoot, getMovementType());
    	new GridObjectPainter(x, y, getIntValue(widthField.getText()), getIntValue(heightField.getText())
    			, editor.getSelectedImage());
    	FeatureManager.getWorldData().saveNPC(myNPC);
    }
    /**
     * Sets the NPC responses based on user input
     */
	private void setResponses(){
		List<String> myResponses = new ArrayList<String>();
		if(myCurrent.getItem()!=null){
			myResponses.add(0,myCurrent.getString() + ", Item:" + myCurrent.getItem());
		}else{
			myResponses.add(0,myCurrent.getString());
		}
		for(UserQueryNodeData q: myCurrent.getChildren()){
			if(q.getString()!=null){
				myResponses.add("   "+q.getString());
			}
			else{
				myResponses.add("   Item:"+q.getItem());
			}
		}
		myResponsesWrapper.setListData(myResponses.toArray());
	}
	/**
	 * Gets the NPCResponseNodeData of the current NPC
	 * @return
	 */
	public NPCResponseNodeData getDialogue() {
		return myRoot;
	}
	/**
	 * Listener that handles user input for NPC responses
	 *
	 */
	private class QueryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(myCurrent.getChildren().size()<4){
			optionFrame = new JOptionPane("Input Dialogue");
			String s = optionFrame.showInputDialog("Enter User Response Option");
			UserQueryNodeData uqn = new UserQueryNodeData(s);
			String ss = optionFrame.showInputDialog("Enter NPC Response to User Response");
			uqn.setChild(new NPCResponseNodeData(ss));
			myCurrent.addChild(uqn);
			setResponses();
			}
		}
	}
	/**
	 * Listener that handles item responses
	 *
	 */
	private class ItemResponseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			boolean alreadyItem=false;
			for(UserQueryNodeData n: myCurrent.getChildren()){
				if(n.getItem()!=null)
					alreadyItem=true;
			}
			if(myCurrent.getChildren().size()<4&&!alreadyItem){
			List<String> myItems = new ArrayList<String>(FeatureManager.getWorldData().getMyItems().keySet());

			String myItem = (String)JOptionPane.showInputDialog(null, "Select a User Item for NPC to react to",
					"Item Selection", JOptionPane.QUESTION_MESSAGE, null, myItems.toArray(), myItems.toArray()[0]);

			UserQueryNodeData uqn = new UserQueryNodeData();
			uqn.setItem(myItem);
			String ss = optionFrame.showInputDialog("Enter NPC Response");
			uqn.setChild(new NPCResponseNodeData(ss));
			myCurrent.addChild(uqn);
			setResponses();
			}
		}
	}
	/**
	 * Listener that handles NPC item giving
	 *
	 */
	private class ItemGiveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			if(myCurrent.getItem()==null){
			List<String> myItems = new ArrayList<String>(FeatureManager.getWorldData().getMyItems().keySet());

			String myItem = (String)JOptionPane.showInputDialog(null, "Select an item for NPC to give to user",
					"Item Selection", JOptionPane.QUESTION_MESSAGE, null, myItems.toArray(), myItems.toArray()[0]);

			myCurrent.setItem(myItem);
			setResponses();
			}
		}
	}
	/**
	 * Listener that handles moving up a NPC response tree
	 *
	 */
	private class GoBackListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(myCurrent!=myRoot){
			myCurrent=myPrev.get(myPrev.size()-1);
			myPrev.remove(myPrev.size()-1);
			setResponses();
			}
		}
	}
	/**
	 * Listener that handles NPC dialogue
	 *
	 */
	private class DialogueClickAction implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent arg0) {
			if(myResponsesWrapper.getSelectedValue()!=null){
				if(myResponsesWrapper.getSelectedIndex()==0){
					optionFrame = new JOptionPane("Input Dialogue");
					myCurrent.setString(optionFrame.showInputDialog("Input NPC Dialog"));
					setResponses();
					myResponsesWrapper.removeSelectionInterval(myResponsesWrapper.getSelectedIndex(),
                            myResponsesWrapper.getSelectedIndex());
				}
				else{
					myPrev.add(myCurrent);
					myCurrent = myCurrent.getChildren().get(myResponsesWrapper.getSelectedIndex()-1).getChild();
					myResponsesWrapper.removeSelectionInterval(myResponsesWrapper.getSelectedIndex(),
                            myResponsesWrapper.getSelectedIndex());
					setResponses();
				}
			}
		}
	}
	 /**
     * Gets the movement type from the check boxes
     * @return Integer representing the movement type
     */
    private int getMovementType(){
    	if(one.isSelected()){
    		return 1;
    	}
    	else if (two.isSelected()){
    		return 2;
    	}
    	else if (three.isSelected()){
    		return 3;
    	}
    	return 3;
    }
}
