package authoring;

import java.awt.BorderLayout;

import javax.swing.JButton;

public class SaveGridObjectFeature extends Feature{
	private JButton createButton;
	public SaveGridObjectFeature(){
		createButton = new JButton("Create GridObject!");
		myComponents.put(createButton, BorderLayout.EAST);
	}
}
