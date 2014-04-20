package authoring;

import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class BarrierCreation extends CommonAttributes implements ItemListener{
	JPanel namePanel;
	JTabbedPane pane;
	JTextField xField;
	JTextField yField;
	JTextField imageField;
	//JComboBox itemLister;
	
	public BarrierCreation() {
		
	}
	public void creationPanel(){
		pane = new JTabbedPane();
		assembleGUI();
		pane.setVisible(true);
	}
	public void assembleGUI(){
		namePanel=new JPanel();
		pane.add("Add Barrier", namePanel);
	    JLabel xLabel = new JLabel("X");
        JLabel yLabel = new JLabel("Y");
        xField = new JTextField("X",15);
        yField = new JTextField("Y",15);
        imageField = new JTextField("defaultIW",15);

        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
        namePanel.add(xLabel);
        namePanel.add(xField);
        namePanel.add(yLabel);
        namePanel.add(yField);
        
        JButton createBarrier=new JButton("Create barrier");
        createBarrier.addActionListener(new createBarrierListener());
        namePanel.add(createBarrier);
	}
	private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
	public void itemStateChanged(ItemEvent e){
		if(e.getStateChange()==ItemEvent.DESELECTED){
			Map<String, Item> itemMap=FeatureManager.getWorldData().getMyItems();
			List<String> itemList=new ArrayList<String>();
			for(String s: itemMap.keySet()){
				itemList.add(s);
			}
			String[] array=itemList.toArray(new String[itemList.size()]);
			//itemLister=new JComboBox(array);
			//namePanel.add
		}
		else{
			
		}
	}
	
	
	private boolean validateText(){
		if(!xField.getText().equals("") && !yField.getText().equals("")){
			return true;
		}
		return false;
	}
	private class createBarrierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			BarrierObject myBarrier=getBarrier();
			if(validateText()){
				FeatureManager.getWorldData().saveBarrier(myBarrier);
			}			
		}
		private BarrierObject getBarrier(){
			return new BarrierObject(getIntValue(xField.getText()), getIntValue(yField.getText()), imageField.getText());
		}
	}
}
