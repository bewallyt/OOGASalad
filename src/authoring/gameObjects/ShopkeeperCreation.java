package authoring.gameObjects;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import authoring.UserQueryNodeData;
import authoring.features.FeatureManager;

public class ShopkeeperCreation extends CommonAttributes {
	private JList myResponsesWrapper;
	private List<String> myWares;
	private JOptionPane optionFrame;

    public ShopkeeperCreation(){}

    /**
     * Creates the GUI window to create NPCs
     */
    public void creationPanel(){	
    	myWares = new ArrayList<String>();
    	JTabbedPane pane = new JTabbedPane();
        String dialogueTab = "Items";

        JPanel namePanel = nameImageFields();

        JPanel locationPanel = locationFields();
        JPanel sizePanel = sizeFields();
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel,BoxLayout.PAGE_AXIS));
        combinedPanel.add(locationPanel);
        combinedPanel.add(sizePanel);
        
        JPanel dialoguePanel = new JPanel();
		myResponsesWrapper = new JList();
		setResponses();
		myResponsesWrapper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane myTextWindow = new JScrollPane(myResponsesWrapper);
		myTextWindow.setPreferredSize(new Dimension(200,100));
		JButton newItemResponse = new JButton("New Item To Sell");
		newItemResponse.addActionListener(new ItemListener());
		dialoguePanel.add(myTextWindow);
		dialoguePanel.add(newItemResponse);

        pane.add(nameTab,namePanel);
        pane.add(locationTab,combinedPanel);
        pane.add(dialogueTab,dialoguePanel);

        frame=new JFrame("New Shopkeeper");
        frame.setLayout(new FlowLayout());
        frame.add(pane);
        JButton createShopkeeper=new JButton("Create Shopkeeper");
        createShopkeeper.addActionListener(new ShopkeeperActionListener());
        frame.add(createShopkeeper);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private class ShopkeeperActionListener implements ActionListener{

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
     * Returns the int value within a string
     * @param s String with the contained int value
     * @return Int value contained in string
     */
    private int getIntValue(String s){
		return Integer.parseInt(s);
	}
    /**
     * Makes the corresponding NPC based on user input
     */
    private void makeNPC(){
    	NPCData myNPC = new NPCData(x,y,getIntValue(widthField.getText()), getIntValue(heightField.getText()), 
    			editor.getSelectedImage().getDescription(),myRoot);
    	new GridObjectPainter(x, y, getIntValue(widthField.getText()), getIntValue(heightField.getText())
    			, editor.getSelectedImage());
    	FeatureManager.getWorldData().saveNPC(myNPC);
    }
    /**
     * Sets the NPC responses based on user input
     */
	private void setResponses(){
		myResponsesWrapper.setListData(myWares.toArray());
	}

	private class ItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(myWares.size()<4){
			List<String> myItems = new ArrayList<String>(FeatureManager.getWorldData().getMyItems().keySet());

			String myItem = (String)JOptionPane.showInputDialog(null, "Select an Item for NPC to sell",
					"Item Selection", JOptionPane.QUESTION_MESSAGE, null, myItems.toArray(), myItems.toArray()[0]);
			myWares.add(myItem);;
			setResponses();
			}
		}
	}
}
