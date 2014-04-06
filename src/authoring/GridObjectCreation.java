package authoring;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GridObjectCreation extends Feature{
	private JButton myGridObjectButton;
	private JFrame frame;
	public GridObjectCreation(){
		myGridObjectButton = new JButton("New GridObject");
		myGridObjectButton.addActionListener(new GridObjectWindowAction());
		myComponents.put(myGridObjectButton, BorderLayout.SOUTH);
	}
	private class GridObjectWindowAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frame = new JFrame("Create a GridObject");
    		frame.add(new GridObjectWindow());
            frame.pack();
            frame.setVisible(true);
		}	
	}
	private class GridObjectWindow extends JPanel{
		private Map<String, JComponent> myPanels;
		
		private JCheckBox steppableCheck;
		private JCheckBox encounterableCheck;
		
		private JTextArea encounterRate;
		private JScrollPane encounterRateWrapper;
		
		private JLabel coordinatesLabel;
		
		private JTextArea xTileCo;
		private JScrollPane xTileCoWrapper;
		
		private JTextArea yTileCo;
		private JScrollPane yTileCoWrapper;
		
		public GridObjectWindow(){
			this.setLayout(new BorderLayout());
			instantiatePanels();
			
			steppableCheck = new JCheckBox("Steppable?");
			myPanels.get(BorderLayout.SOUTH).add(steppableCheck);
			encounterableCheck = new JCheckBox("Encounterable?");
			myPanels.get(BorderLayout.SOUTH).add(encounterableCheck);
			
			encounterRate = new JTextArea(1,3);
			encounterRateWrapper = new JScrollPane(encounterRate);
			myPanels.get(BorderLayout.SOUTH).add(encounterRateWrapper);
			
			coordinatesLabel = new JLabel("Input X and Y coordinates of GridObject");
			myPanels.get(BorderLayout.WEST).add(coordinatesLabel);
			xTileCo = new JTextArea(1,3);
			xTileCoWrapper = new JScrollPane(xTileCo);
			myPanels.get(BorderLayout.WEST).add(xTileCoWrapper);
			yTileCo = new JTextArea(1,3);
			yTileCoWrapper = new JScrollPane(yTileCo);
			myPanels.get(BorderLayout.WEST).add(yTileCoWrapper);
			
			JButton createButton = new JButton("Create GridObject!");
			myPanels.get(BorderLayout.EAST).add(createButton);
		}
		private void instantiatePanels() {
			myPanels = new HashMap<String, JComponent>();
			myPanels.put(BorderLayout.NORTH, new JPanel());
			myPanels.put(BorderLayout.SOUTH, new JPanel());
			myPanels.put(BorderLayout.WEST, new JPanel());
			myPanels.put(BorderLayout.EAST, new JPanel());
			myPanels.put(BorderLayout.CENTER, new JPanel());
			for(String s: myPanels.keySet()){
				this.add(myPanels.get(s), s);
			}
		}
	}
}