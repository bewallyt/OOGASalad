package authoring;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SaveGridObjectFeature extends Feature{
	private JButton createButton;
	private GridObjectCreation mySuperFeature;
	public SaveGridObjectFeature(GridObjectCreation gridObjectCreation){
		mySuperFeature = gridObjectCreation;
		createButton = new JButton("Create GridObject!");
		createButton.addActionListener(new CreateGridObjectListener());
		myComponents.put(createButton, BorderLayout.SOUTH);
	}
	private class CreateGridObjectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			mySuperFeature.getData().setX(((GridObjectCoordinateFeature)(mySuperFeature.getFeature("GridObjectCoordinateFeature"))).getX());
			mySuperFeature.getData().setY(((GridObjectCoordinateFeature)(mySuperFeature.getFeature("GridObjectCoordinateFeature"))).getY());
			mySuperFeature.getData().setSteppable(((SteppableFeature)(mySuperFeature.getFeature("SteppableFeature"))).isSteppable());
			mySuperFeature.getData().setImageName(((GridObjectImageFeature)(mySuperFeature.getFeature("GridObjectImageFeature"))).getImageName());
			mySuperFeature.getData().setTalkable(((TalkableFeature)(mySuperFeature.getFeature("TalkableFeature"))).isTalkable());
			mySuperFeature.getData().setWidth(((WidthHeightFeature)(mySuperFeature.getFeature("WidthHeightFeature"))).getWidth());
			mySuperFeature.getData().setHeight(((WidthHeightFeature)(mySuperFeature.getFeature("WidthHeightFeature"))).getHeight());
			if(mySuperFeature.getData().isDefined()){
				mySuperFeature.getData().init();
				mySuperFeature.getView().close();
			}
		}
	}
}
