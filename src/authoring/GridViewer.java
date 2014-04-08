package authoring;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridViewer extends Feature implements ActionListener{
	
	private JPanel myViewer;
	private Grid myGrid;
	private ScrollButton scrollLeft, scrollUp, scrollRight, scrollDown;
	
	public GridViewer() {
		myViewer = new JPanel(new GridBagLayout());
		myGrid = new Grid();
		buttonMaker();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		myViewer.add(myGrid, gbc);
		myComponents.put(myViewer, BorderLayout.CENTER);
	}
	
	private void buttonMaker(){
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 1;
		scrollLeft = new ScrollButton("Go Left", -1, 0);
		scrollLeft.setActionCommand("scrollleft");
		scrollLeft.addActionListener(this);
		myViewer.add(scrollLeft, gbc);
		
		gbc.gridx = 2;
		scrollRight = new ScrollButton("Go Right", 1, 0);
		scrollRight.setActionCommand("scrollright");
		scrollRight.addActionListener(this);
		myViewer.add(scrollRight, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		scrollUp = new ScrollButton("Go Up", 0, -1);
		scrollUp.setActionCommand("scrollup");
		scrollUp.addActionListener(this);
		myViewer.add(scrollUp, gbc);
		
		gbc.gridy = 2;
		scrollDown = new ScrollButton("Go Down", 0, 1);
		scrollDown.setActionCommand("scrolldown");
		scrollDown.addActionListener(this);
		myViewer.add(scrollDown, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ScrollButton buttonPushed = (ScrollButton) e.getSource();
		myGrid.redrawGrid(buttonPushed.getXChange(), buttonPushed.getYChange());		
	}

}
