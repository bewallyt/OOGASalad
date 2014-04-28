package authoring.gameObjects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import authoring.features.FeatureManager;

public class HealerCreation extends CommonAttributes{
	public HealerCreation() {
		
	}
	public void creationPanel(){
		frame = new JFrame("Add Healer:");
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		assembleGUI();
		frame.pack();
		frame.setVisible(true);
	}
	public void assembleGUI(){

        JPanel namePanel = nameImageFields();
        JPanel locationPanel = locationFields();
        JPanel sizePanel = sizeFields();
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel,BoxLayout.PAGE_AXIS));
        combinedPanel.add(namePanel);
        combinedPanel.add(locationPanel);
        combinedPanel.add(sizePanel);
        
        JButton createBarrier=new JButton("Create Healer");
        createBarrier.addActionListener(new createHealerListener());
        combinedPanel.add(createBarrier);
        frame.add(combinedPanel);
	}
	private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
	private boolean validateText(){
        return !xcoor.getText().equals("") && !ycoor.getText().equals("") && imagePanel.getComponents().length > 0;
    }
	private class createHealerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			HealerData myHealer=getHealer();
			if(validateText()){
				FeatureManager.getWorldData().saveHealer(myHealer);
				new GridObjectPainter(getIntValue(xcoor.getText()), getIntValue(ycoor.getText()),
                        getIntValue(widthField.getText()), getIntValue(heightField.getText()),
                        editor.getSelectedImage());
				frame.dispose();
				editor.dispose();
			}			
		}

		private HealerData getHealer(){
			return new HealerData(getIntValue(xcoor.getText()), getIntValue(ycoor.getText()),
                    getIntValue(widthField.getText()),
					getIntValue(heightField.getText()),
					editor.getSelectedImage().getDescription());
		}
	}
}
