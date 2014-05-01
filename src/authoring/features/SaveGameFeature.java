package authoring.features;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import authoring.SaveGameWindow;

/**
 * Class that creates the Save Game Button and its action listener
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class SaveGameFeature extends Feature {
	private JButton mySaveButton;
	public SaveGameFeature(){
		mySaveButton = new JButton("Save Game");
		mySaveButton.addActionListener(new SaveListener());
		myComponents.put(mySaveButton, BorderLayout.SOUTH);
	}
	private class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new SaveGameWindow();
		}
	}
}
