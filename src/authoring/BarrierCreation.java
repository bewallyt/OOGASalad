package authoring;

/**
 * @ Davis T.
 * @ Pritam M.
 * */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BarrierCreation extends CommonAttributes{

	public BarrierCreation() {
		
	}
	public void creationPanel(){
		frame = new JFrame("Add Barrier:");
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
        
        JButton createBarrier=new JButton("Create barrier");
        createBarrier.addActionListener(new createBarrierListener());
        combinedPanel.add(createBarrier);
        frame.add(combinedPanel);
	}
	private int getIntValue(String s){
		return Integer.parseInt(s);
	}
	
	private boolean validateText(){
        return !xcoor.getText().equals("") && !ycoor.getText().equals("") && imagePanel.getComponents().length > 0;
    }
	private class createBarrierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			BarrierData myBarrier=getBarrier();
			if(validateText()){
				FeatureManager.getWorldData().saveBarrier(myBarrier);
				new GridObjectPainter(getIntValue(xcoor.getText()), getIntValue(ycoor.getText()),
                        getIntValue(widthField.getText()), getIntValue(heightField.getText()),
                        editor.getSelectedImage());
				frame.dispose();
				editor.dispose();
			}			
		}

		private BarrierData getBarrier(){
			System.out.println("xField"+xcoor.getText());
			System.out.println("yField"+ycoor.getText());
			return new BarrierData(getIntValue(xcoor.getText()), getIntValue(ycoor.getText()),
                    getIntValue(widthField.getText()),
					getIntValue(heightField.getText()),
					editor.getSelectedImage().getDescription());
		}
	}
}
