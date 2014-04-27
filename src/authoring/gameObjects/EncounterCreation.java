package authoring.gameObjects;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import authoring.SpringUtilities;
import authoring.features.FeatureManager;


public class EncounterCreation extends CommonAttributes{

    private  JComboBox playerImages;
    private  JList itemList;
    private int x;
    private int y;
    private JTextField xcoor;
    private JTextField ycoor;
	private int myModIndex;
	private JOptionPane optionFrame;
	private JFrame frame;
	private JTextField widthField;
	private JTextField heightField;

    public EncounterCreation(){}

    public void creationPanel(){	
    	JTabbedPane pane = new JTabbedPane();
        String locationTab = "Location";

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

        pane.add(nameTab,namePanel);
        pane.add(locationTab,locationPanel);

        frame=new JFrame("New Encounterable");
        frame.setLayout(new FlowLayout());
        frame.add(pane);
        JButton createNPC=new JButton("Create Encounterable");
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
            makeEncounterable();
			frame.dispose();
			editor.dispose();
		}
    }
    private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
    private void makeEncounterable(){
    	EncounterData myEncounter = new EncounterData(x,y,getIntValue(widthField.getText()), getIntValue(heightField.getText()), 
    			editor.getSelectedImage().getDescription());
    	new GridObjectPainter(x, y, getIntValue(widthField.getText()), getIntValue(heightField.getText())
    			, editor.getSelectedImage());
    	FeatureManager.getWorldData().saveEncounter(myEncounter);
    }
}
