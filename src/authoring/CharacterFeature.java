package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CharacterFeature extends Feature{
	private JButton addCharacterButton;
	public CharacterFeature(){
		addCharacterButton = new JButton("Add Character");
		addCharacterButton.addActionListener(new CharacterButtonListener());
		myComponents.put(addCharacterButton, BorderLayout.SOUTH);
	}

	private class CharacterButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new addCharacterWindow();
		}
	}

}
