package authoring;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addCharacterWindow extends JFrame{
	private JTextField healthField;
	private JTextField damageField;
	private JButton addCharacter;
	
	public addCharacterWindow() {
		super("Add a character");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(5,1));
		buildPanel();
		pack();
		setVisible(true);
	}

	private void buildPanel(){
		healthField=new JTextField(10);
		addCharacter=new JButton("Add Character");
		addCharacter.addActionListener(new addCharacterListener());
		
		JLabel healthLabel=new JLabel("Health:");
		JLabel damageLabel=new JLabel("Damage:");
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		JPanel panel5=new JPanel();
		
		panel1.add(healthLabel);
		panel2.add(healthField);
		panel3.add(damageLabel);
		panel4.add(damageField);
		panel5.add(addCharacter);
	}
	
	private class addCharacterListener implements ActionListener{
		public void actionperformed(ActionEvent e){
			
		}
	}
}



public class SaveGameWindow extends JFrame{

    private JTextField saveGameField;
    private JButton saveButton;

    public SaveGameWindow(){
        super("Saving Game:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,1));
        buildPanel();
        pack();
        setVisible(true);
    }

    private void buildPanel(){
        saveGameField = new JTextField(10);
        saveButton=new JButton("Save");
        saveButton.addActionListener(new SaveButton());
        
        JLabel NameLabel =  new JLabel("What would you like to name your saved game?");

        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        add(panel1);
        add(panel2);
        add(panel3);
        panel1.add(NameLabel);
        panel2.add(saveGameField);
        panel3.add(saveButton);
 }
    private boolean validateText(){
    	return !saveGameField.getText().equals("");
    }
    private class SaveButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
        		if(validateText()){
        			FeatureManager.getDataManager().storeWorldData(saveGameField.getText(), FeatureManager.getWorldData());
        			dispose();
        		}
        		else{
        			JOptionPane pane=new JOptionPane();
        			pane.showMessageDialog(new JFrame(), "Please enter a valid file name", "Invalid File Name", JOptionPane.WARNING_MESSAGE);
        		}
        }
    }
}
