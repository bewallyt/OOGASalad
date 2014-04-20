package authoring;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

public class NPCCreation extends CommonAttributes implements ItemListener{

    private  JComboBox playerImages;
    private  JList itemList;
    private int x;
    private int y;
    private String[] weaponNames;
    private String[] itemNames;
    private JTextField xcoor;
    private JTextField ycoor;

    public NPCCreation(){}

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            playerImages.setEnabled(false);
            imageName.setEnabled(true);
            itemList.setEnabled(false);
        } else{
            imageName.setEnabled(false);
            playerImages.setEnabled(true);
            itemList.setEnabled(true);
        }
    }

    public void creationPanel(){	
    	JTabbedPane pane = new JTabbedPane();
        String locationTab = "Location";

        JPanel namePanel = nameImageFields();
        imageName.setEnabled(false);

        JPanel locationPanel = new JPanel(new SpringLayout());
        JLabel xcoordinate = new JLabel("X");
        JLabel ycoordinate = new JLabel("Y");
        xcoor = new JTextField("2",5);
        ycoor = new JTextField("2",5);
        locationPanel.add(xcoordinate);
        xcoordinate.setLabelFor(xcoor);
        locationPanel.add(xcoor);
        locationPanel.add(ycoordinate);
        ycoordinate.setLabelFor(ycoor);
        locationPanel.add(ycoor);
        SpringUtilities.makeCompactGrid(locationPanel,2,2,6,6,6,6);

        pane.add(nameTab,namePanel);
        pane.add(locationTab,locationPanel);

        int result = JOptionPane.showOptionDialog(null, pane, "New NPC", JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION){
            name = itemName.getText();
            x = Integer.parseInt(xcoor.getText());
            y = Integer.parseInt(ycoor.getText());

            for(String s: textValues.keySet()){
                attributeValues.put(s,Integer.parseInt(textValues.get(s).getText()));
            }
            image = (String) playerImages.getSelectedItem();
            makePlayer();
        }
    }

    private void makeRandomEnemy() {
        RandomEnemy madeRandomEnemy = new RandomEnemy(Integer.parseInt(null),Integer.parseInt(null),
                image,name,attributeValues,weaponNames,itemNames);
        FeatureManager.getWorldData().saveRandomEnemy(madeRandomEnemy);
    }

    private void makePlayer() {
        PlayerData madePlayer = new PlayerData(x,y,image,name,attributeValues,weaponNames,itemNames);
        FeatureManager.getWorldData().savePlayer(madePlayer);
    }

    private void makeEnemy() {
        EnemyData madeEnemy = new EnemyData(x,y,image,name,attributeValues,weaponNames);
    }
}
