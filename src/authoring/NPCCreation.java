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
	private JOptionPane frame;

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
        imageName.setEnabled(false);

        JPanel locationPanel = new JPanel(new SpringLayout());
        JLabel xcoordinate = new JLabel("X");
        JLabel ycoordinate = new JLabel("Y");
        xcoor = new JTextField("2",5);
        ycoor = new JTextField("2",5);
        locationPanel.add(xcoordinate);
        xcoordinate.setLabelFor(xcoor);
        locationPanel.add(xcoor);
        locationPanel.add(ycoordinate);
        ycoordinate.setLabelFor(ycoor);
        locationPanel.add(ycoor);
        SpringUtilities.makeCompactGrid(locationPanel,2,2,6,6,6,6);
        
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
		myGoBack = new JButton("Go Up A Level");
		myGoBack.addActionListener(new GoBackListener());
		dialoguePanel.add(myTextWindow);
		dialoguePanel.add(newQueryOption);
		dialoguePanel.add(myGoBack);

        pane.add(nameTab,namePanel);
        pane.add(locationTab,locationPanel);
        pane.add(dialogueTab,dialoguePanel);

        int result = JOptionPane.showOptionDialog(null, pane, "New NPC", JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION){
            name = itemName.getText();
            x = Integer.parseInt(xcoor.getText());
            y = Integer.parseInt(ycoor.getText());
            makeNPC();
        }
    }

    private void makeNPC(){
    	NPCData myNPC = new NPCData(x,y,image,myRoot);
    	FeatureManager.getWorldData().saveNPC(myNPC);
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
			if(myCurrent.getChildren().size()<4){
			frame = new JOptionPane("Input Dialogue");
			String s = frame.showInputDialog("Enter User Response Option");
			UserQueryNode uqn = new UserQueryNode(s);
			String ss = frame.showInputDialog("Enter NPC Response to User Response");
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
					frame = new JOptionPane("Input Dialogue");
					myCurrent.setString(frame.showInputDialog("Input NPC Dialog"));
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
