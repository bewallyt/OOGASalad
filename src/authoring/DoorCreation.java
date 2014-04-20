package authoring;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DoorCreation {

	JPanel namePanel;
	JFrame frame;
	JTextField xField;
	JTextField yField;
	JTextField toXField;
	JTextField toYField;
	JTextField imageField;
	List<JTextField> textFieldList=new ArrayList<JTextField>();
	JComboBox worldList;
	
	public DoorCreation() {
	}
	public void creationPanel(){
		frame = new JFrame("Add Door:");
		frame.setLocationRelativeTo(null);

		assembleGUI();
		frame.pack();
		frame.setVisible(true);
	}
	public void assembleGUI(){
		namePanel=new JPanel();
	    JLabel xLabel = new JLabel("X");
        JLabel yLabel = new JLabel("Y");
        JLabel toXLabel=new JLabel("New world X");
        JLabel toYLabel=new JLabel("New world Y");
        xField = new JTextField("",15);
        yField = new JTextField("",15);
        toXField=new JTextField("", 15);
        toYField=new JTextField("", 15);
        imageField = new JTextField("defaultIW",15);
        
        worldList=new JComboBox(getWorldArray());

        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
        namePanel.add(xLabel);
        namePanel.add(xField);
        namePanel.add(yLabel);
        namePanel.add(yField);
        namePanel.add(toXLabel);
        namePanel.add(toXField);
        namePanel.add(toYLabel);
        namePanel.add(toYField);
        namePanel.add(worldList);
        
       
        
        JButton createBarrier=new JButton("Create Door");
        createBarrier.addActionListener(new createDoorListener());
        namePanel.add(createBarrier);
        frame.add(namePanel);
        createFieldList();
        
	}
	private void createFieldList(){
		textFieldList.add(xField);
		textFieldList.add(yField);
		textFieldList.add(toXField);
		textFieldList.add(toYField);
	}
	private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	private String[] getWorldArray(){
		Map<String, MapData> maps=FeatureManager.getWorldData().getMaps();
		String[] mapArray=new String[maps.keySet().size()];
		int i=0;
		for(String s : maps.keySet()){
			mapArray[i]=s;
			i++;
		}
		return mapArray;
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
			DoorObject myDoor=getDoor();
			if(validateText(textFieldList)){
				FeatureManager.getWorldData().saveDoor(myDoor);;
			}			
		}
		private DoorObject getDoor(){
			return new DoorObject(getIntValue(xField.getText()), getIntValue(yField.getText()), imageField.getText(), 
					getIntValue(toXField.getText()), getIntValue(toYField.getText()), worldList.getSelectedItem().toString());
		}
	}

}
