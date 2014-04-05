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
		public GridObjectWindow(){
			steppableCheck = new JCheckBox("Steppable?");
			add(steppableCheck);
			encounterableCheck = new JCheckBox("Encounterable?");
			add(encounterableCheck);
			encounterRate = new JTextArea(1,3);
			encounterRateWrapper = new JScrollPane(encounterRate);
			add(encounterRateWrapper);
		}
	}
}