package authoring;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			steppableCheck = new JCheckBox("Steppable?");
			add(steppableCheck, BorderLayout.SOUTH);
			encounterableCheck = new JCheckBox("Encounterable?");
			add(encounterableCheck, BorderLayout.SOUTH);
			
			encounterRate = new JTextArea(1,3);
			encounterRateWrapper = new JScrollPane(encounterRate);
			add(encounterRateWrapper, BorderLayout.SOUTH);
			
			coordinatesLabel = new JLabel("Input X and Y coordinates of GridObject");
			add(coordinatesLabel, BorderLayout.NORTH);
			xTileCo = new JTextArea(1,3);
			xTileCoWrapper = new JScrollPane(xTileCo);
			add(xTileCoWrapper, BorderLayout.NORTH);
			yTileCo = new JTextArea(1,3);
			yTileCoWrapper = new JScrollPane(yTileCo);
			add(yTileCoWrapper, BorderLayout.NORTH);
		}
	}
}