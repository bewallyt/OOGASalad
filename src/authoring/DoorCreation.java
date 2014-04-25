package authoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;


public class DoorCreation {

	private JPanel namePanel;
	private JFrame frame;
	private JTextField xField;
	private JTextField yField;
	private JTextField toXField;
	private JTextField toYField;
	private JTextField imageField;
	private List<JTextField> textFieldList=new ArrayList<JTextField>();
	private JComboBox worldList;
	private TilePanel imagePanel;
	private GridObjectImageEditor editor;
	
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
		
		namePanel=new JPanel();
	    JLabel xLabel = new JLabel("X");
        JLabel yLabel = new JLabel("Y");
        JLabel toXLabel=new JLabel("New world X");
        JLabel toYLabel=new JLabel("New world Y");
        JLabel newWorld=new JLabel("Which world will this map to?");
        xField = new JTextField("",15);
        yField = new JTextField("",15);
        toXField=new JTextField("", 15);
        toYField=new JTextField("", 15);
        
        worldList=new JComboBox(getWorldArray());

        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
        namePanel.add(xLabel);
        namePanel.add(xField);
        namePanel.add(yLabel);
        namePanel.add(yField);
        namePanel.add(newWorld);
        namePanel.add(worldList);
        namePanel.add(toXLabel);
        namePanel.add(toXField);
        namePanel.add(toYLabel);
        namePanel.add(toYField);
        
        Border defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
        imagePanel = new TilePanel(1,1);
		imagePanel.setBorder(defaultBorder);
		namePanel.add(imagePanel);
		editor=new GridObjectImageEditor(imagePanel);
        
        
       

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
			DoorData myDoor=getDoor();
			if(validateText(textFieldList)){
				FeatureManager.getWorldData().saveDoor(myDoor);
				new GridObjectPainter(getIntValue(xField.getText()), getIntValue(yField.getText()), editor.getSelectedImage());
				System.out.println("X Data: "+getIntValue(xField.getText())+"    Y Data : "+ getIntValue(yField.getText()));
				frame.dispose();
				editor.dispose();
			}			
		}
		private DoorData getDoor(){
			return new DoorData(getIntValue(xField.getText()), getIntValue(yField.getText()), editor.getSelectedImage().getDescription(), 
					getIntValue(toXField.getText()), getIntValue(toYField.getText()), worldList.getSelectedItem().toString());
		}
	}

}
