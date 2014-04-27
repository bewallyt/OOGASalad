package authoring;

/**
 * @ Davis T.
 * @ Pritam M.
 * */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class DoorCreation extends CommonAttributes{

	private JTextField toXField;
	private JTextField toYField;
	private List<JTextField> textFieldList=new ArrayList<JTextField>();
	
	public DoorCreation() {
	}
	public void creationPanel(){
		frame = new JFrame("Add Door:");
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		assembleGUI();
		frame.pack();
		frame.setVisible(true);
	}
	public void assembleGUI(){

        JLabel toXLabel=new JLabel("New world X:");
        JLabel toYLabel=new JLabel("New world Y:");
        JLabel newWorld=new JLabel("Connected Map:");
        toXField=new JTextField("", 15);
        toYField=new JTextField("", 15);
        
        worldList=new JComboBox(getWorldArray());

        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel,BoxLayout.PAGE_AXIS));
        JPanel namePanel = nameImageFields();
        JPanel locationPanel = locationFields();
        JPanel sizePanel = sizeFields();
        combinedPanel.add(namePanel);
        combinedPanel.add(locationPanel);
        combinedPanel.add(sizePanel);
        JPanel nextWorldPanel = new JPanel();
        nextWorldPanel.setLayout(new SpringLayout());
        nextWorldPanel.add(newWorld);
        newWorld.setLabelFor(worldList);
        nextWorldPanel.add(worldList);
        nextWorldPanel.add(toXLabel);
        toXLabel.setLabelFor(toXField);
        nextWorldPanel.add(toXField);
        nextWorldPanel.add(toYLabel);
        toYLabel.setLabelFor(toYField);
        nextWorldPanel.add(toYField);
        SpringUtilities.makeCompactGrid(nextWorldPanel,3,2,5,5,5,5);
        combinedPanel.add(nextWorldPanel);

        JButton createBarrier=new JButton("Create Door");
        createBarrier.addActionListener(new createDoorListener());
        combinedPanel.add(createBarrier);
        frame.add(combinedPanel);
        createFieldList();
        
	}
	private void createFieldList(){
        textFieldList.add(xcoor);
        textFieldList.add(ycoor);
        textFieldList.add(widthField);
        textFieldList.add(heightField);
		textFieldList.add(toXField);
		textFieldList.add(toYField);

	}
	private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
	private boolean validateText(List<JTextField> fieldList){
		for(JTextField field: fieldList){
			if(field.getText().equals("")){
				return false;
			}
		}
		return true;
	}
	private class createDoorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(validateText(textFieldList)){
				new GridObjectPainter(getIntValue(xcoor.getText()), getIntValue(ycoor.getText()),
                        getIntValue(widthField.getText()), getIntValue(heightField.getText()),
						editor.getSelectedImage());
				DoorData myDoor=getDoor();
				FeatureManager.getWorldData().saveDoor(myDoor);
				System.out.println("X Data: "+getIntValue(xcoor.getText())+"    Y Data : "+
                        getIntValue(ycoor.getText()));
				frame.dispose();
				editor.dispose();
			}			
		}

		private DoorData getDoor(){
			return new DoorData(getIntValue(xcoor.getText()), getIntValue(ycoor.getText()),
                    getIntValue(widthField.getText()), getIntValue(heightField.getText()),
					editor.getSelectedImage().getDescription(), getIntValue(toXField.getText()),
                    getIntValue(toYField.getText()), worldList.getSelectedItem().toString());
		}
	}

}
