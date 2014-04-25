package authoring;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class BarrierCreation extends CommonAttributes{
	private JPanel namePanel;
	private JFrame frame;
	private JTextField xField;
	private JTextField yField;
	private JTextField widthField;
	private JTextField heightField;
	private TilePanel imagePanel;
	private GridObjectImageEditor editor;
	
	public BarrierCreation() {
		
	}
	public void creationPanel(){
		frame = new JFrame("Add Barrier:");
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
        JLabel widthLabel=new JLabel("Width");
        JLabel heightLabel=new JLabel("Height");
        JLabel imageLabel=new JLabel("Image");
        xField = new JTextField("",15);
        yField = new JTextField("",15);
        widthField=new JTextField("1", 15);
        heightField=new JTextField("1", 15); 

        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
        namePanel.add(xLabel);
        namePanel.add(xField);
        namePanel.add(yLabel);
        namePanel.add(yField);
        namePanel.add(widthLabel);
        namePanel.add(widthField);
        namePanel.add(heightLabel);
        namePanel.add(heightField);
        
        
        Border defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
        imagePanel = new TilePanel(1,1);
		imagePanel.setBorder(defaultBorder);
		namePanel.add(imagePanel);
		editor=new GridObjectImageEditor(imagePanel);
        
        
        JButton createBarrier=new JButton("Create barrier");
        createBarrier.addActionListener(new createBarrierListener());
        namePanel.add(createBarrier);
        frame.add(namePanel);
        
	}
	private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
	private boolean validateText(){
		if(!xField.getText().equals("") && !yField.getText().equals("") && imagePanel.getComponents().length>0){
			return true;
		}
		return false;
	}
	private class createBarrierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			BarrierData myBarrier=getBarrier();
			if(validateText()){
				FeatureManager.getWorldData().saveBarrier(myBarrier);
				new GridObjectPainter(getIntValue(xField.getText()), getIntValue(yField.getText()), getIntValue(widthField.getText()), getIntValue(heightField.getText()), editor.getSelectedImage());
				frame.dispose();
				editor.dispose();
			}			
		}
		private BarrierData getBarrier(){
			System.out.println("xField"+xField.getText());
			System.out.println("yField"+yField.getText());
			return new BarrierData(getIntValue(xField.getText()), getIntValue(yField.getText()), editor.getSelectedImage().getDescription());
		}
	}
}
