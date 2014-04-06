package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SaveGameFeature extends Feature {
	private JButton mySaveButton;
	public SaveGameFeature(){
		mySaveButton = new JButton("Save Game");
		mySaveButton.addActionListener(new SaveListener());
		myComponents.put(mySaveButton, BorderLayout.SOUTH);
	}
	private class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			FeatureManager.getDataManager().setWorldData("default", FeatureManager.getWorldData());
		}
	}
}
