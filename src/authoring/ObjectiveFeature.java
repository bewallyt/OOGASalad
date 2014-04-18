package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ObjectiveFeature extends Feature{
	private JButton createObjectiveButton;
	public ObjectiveFeature(){
		createObjectiveButton = new JButton("Add Objectives");
		createObjectiveButton.addActionListener(new ObjectiveListener());
		myComponents.put(createObjectiveButton, BorderLayout.NORTH);
	}
	private class ObjectiveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new ObjectiveWindow();
		}
	}
}
