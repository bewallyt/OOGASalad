package authoring.gameObjects;

/**
 * Class that handles the GUI window for the creation of barriers
 * @ Davis T.
 * @ Pritam M.
 * */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import authoring.features.FeatureManager;


public class BarrierCreation extends CommonAttributes{

	public BarrierCreation() {
		
	}
	/**
	 * Creates the overall GUI panel
	 */
	public void creationPanel(){
		frame = new JFrame("Add Barrier:");
		frame.setAlwaysOnTop(true);
		frame.setLocationRelativeTo(null);
		assembleGUI();
		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * Puts the GUI panel together
	 */
	private void assembleGUI(){

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
	private List<Integer> getIntegerList(String s){
		List<Integer> list=new ArrayList<Integer>();
		String[] options=s.split(":");
		int b;
		if(options.length>1){
			b=getIntValue(options[1]);
			int a=getIntValue(options[0]);
			while(a<=b){
				list.add(a);
				a++;
			}
			
		}
		else{
			list.add(getIntValue(options[0]));
		}
		return list;
	}
	/**
	 * Validates user input
	 * @return boolean stating whether user input is valid or not
	 */
	private boolean validateText(){
        return !xcoor.getText().equals("") && !ycoor.getText().equals("") && imagePanel.getComponents().length > 0;
    }
	private class createBarrierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int x: getIntegerList(xcoor.getText())){
				for(int y: getIntegerList(ycoor.getText())){
					BarrierData myBarrier=getBarrier(x, y);
					if(validateText()){
						FeatureManager.getWorldData().saveBarrier(myBarrier);
						new GridObjectPainter(x, y,
		                        getIntValue(widthField.getText()), getIntValue(heightField.getText()),
		                        editor.getSelectedImage());
						frame.dispose();
						editor.dispose();
					}			
				}
			}
			
		}

		private BarrierData getBarrier(int x, int y){
//			System.out.println("xField"+xcoor.getText());
//			System.out.println("yField"+ycoor.getText());
			return new BarrierData(x, y,
                    getIntValue(widthField.getText()),
					getIntValue(heightField.getText()),
					editor.getSelectedImage().getDescription());
		}
	}
}
