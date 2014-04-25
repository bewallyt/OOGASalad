package authoring;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NPCCreation extends CommonAttributes implements ItemListener{

    private  JComboBox playerImages;
    private  JList itemList;
    private int x;
    private int y;
    private JTextField xcoor;
    private JTextField ycoor;
	private JScrollPane myTextWindow;
	private JList myResponsesWrapper;
	private List<String> myResponses;
	private NPCResponseNode myRoot;
	private List<NPCResponseNode> myPrev;
	private NPCResponseNode myCurrent;
	private int myModIndex;
	private JButton newQueryOption;
	private JButton myGoBack;
	private JButton newItemResponse;
	private JOptionPane optionFrame;
	private JFrame frame;
	private JTextField widthField;
	private JTextField heightField;

    public NPCCreation(){}

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            playerImages.setEnabled(false);
            imageName.setEnabled(true);
            itemList.setEnabled(false);
        } else{
            imageName.setEnabled(false);
            playerImages.setEnabled(true);
            itemList.setEnabled(true);
        }
    }

    public void creationPanel(){	
    	JTabbedPane pane = new JTabbedPane();
        String locationTab = "Location";
        String dialogueTab = "Dialogue";

        JPanel namePanel = nameImageFields();
      //  imageName.setEnabled(false);

        JPanel locationPanel = new JPanel(new SpringLayout());
        JLabel xcoordinate = new JLabel("X");
        JLabel ycoordinate = new JLabel("Y");
        JLabel widthLabel=new JLabel("Width");
        JLabel heightLabel=new JLabel("Height");
        xcoor = new JTextField("2",5);
        ycoor = new JTextField("2",5);
        widthField=new JTextField("1", 15);
        heightField=new JTextField("1", 15); 
        locationPanel.add(xcoordinate);
        xcoordinate.setLabelFor(xcoor);
        locationPanel.add(xcoor);
        locationPanel.add(ycoordinate);
        ycoordinate.setLabelFor(ycoor);
        locationPanel.add(ycoor);
        locationPanel.add(widthLabel);
        locationPanel.add(widthField);
        locationPanel.add(heightLabel);
        locationPanel.add(heightField);
        SpringUtilities.makeCompactGrid(locationPanel,4,2,6,6,6,6);
        
        JPanel dialoguePanel = new JPanel();
		myPrev = new ArrayList<NPCResponseNode>();
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
		newItemResponse = new JButton("New Item Response");
		newItemResponse.addActionListener(new ItemResponseListener());
		myGoBack = new JButton("Go Up A Level");
		myGoBack.addActionListener(new GoBackListener());
		dialoguePanel.add(myTextWindow);
		dialoguePanel.add(newQueryOption);
		dialoguePanel.add(newItemResponse);
		dialoguePanel.add(myGoBack);

        pane.add(nameTab,namePanel);
        pane.add(locationTab,locationPanel);
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
    private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
    private void makeNPC(){
    	NPCData myNPC = new NPCData(x,y,getIntValue(widthField.getText()), getIntValue(heightField.getText()), 
    			editor.getSelectedImage().getDescription(),myRoot);
    	new GridObjectPainter(x, y, getIntValue(widthField.getText()), getIntValue(heightField.getText())
    			, editor.getSelectedImage());
    	FeatureManager.getWorldData().saveNPC(myNPC);
    }
	private void setResponses(){
		myResponses = new ArrayList<String>();
		myResponses.add(0,myCurrent.getString());
		for(UserQueryNode q: myCurrent.getChildren()){
			if(q.getString()!=null){
				myResponses.add("   "+q.getString());
			}
			else{
				myResponses.add("   Item:"+q.getItem());
			}
		}
		myResponsesWrapper.setListData(myResponses.toArray());
	}
	public NPCResponseNode getDialogue() {
		return myRoot;
	}
	private class QueryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(myCurrent.getChildren().size()<4){
			optionFrame = new JOptionPane("Input Dialogue");
			String s = optionFrame.showInputDialog("Enter User Response Option");
			UserQueryNode uqn = new UserQueryNode(s);
			String ss = optionFrame.showInputDialog("Enter NPC Response to User Response");
			uqn.setChild(new NPCResponseNode(ss));
			myCurrent.addChild(uqn);
			setResponses();
			}
		}
	}
	private class ItemResponseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			boolean alreadyItem=false;
			for(UserQueryNode n: myCurrent.getChildren()){
				if(n.getItem()!=null)
					alreadyItem=true;
			}
			if(myCurrent.getChildren().size()<4&&!alreadyItem){
			optionFrame = new JOptionPane("Input Dialogue");
			List<String> myItems = new ArrayList<String>(FeatureManager.getWorldData().getMyItems().keySet());
			JComboBox myItemBox = new JComboBox(myItems.toArray());
			optionFrame.showMessageDialog(null, myItems, "Enter User Item for NPC to react to", JOptionPane.QUESTION_MESSAGE);
			UserQueryNode uqn = new UserQueryNode();
			uqn.setItem((String)myItemBox.getSelectedItem());
			String ss = optionFrame.showInputDialog("Enter NPC Response");
			uqn.setChild(new NPCResponseNode(ss));
			myCurrent.addChild(uqn);
			setResponses();
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
					optionFrame = new JOptionPane("Input Dialogue");
					myCurrent.setString(optionFrame.showInputDialog("Input NPC Dialog"));
					setResponses();
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
}
