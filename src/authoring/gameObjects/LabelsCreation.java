package authoring.gameObjects;

import javax.swing.*;

import authoring.SpringUtilities;
import authoring.features.FeatureManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;




/**
 * Class that handles the creation and GUI window of arena labels
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class LabelsCreation extends CommonAttributes implements ActionListener {

    private String[] fieldLabels = {"Action","Armament","Container","Reaction"};
    private String[] tootipText = {
            "e.g. Attack, Skill","e.g. Weapon, Pokemon","e.g. Bag, Satchel",
            "e.g. Run, Hide"
    };
    private String[] defaultLabels = {"Attack","Weapon","Bag","Run"};


    public LabelsCreation(){}

    public void creationPanel(){
        textValues = new HashMap<String, JTextField>();
        frame = new JFrame("Assign Labels");
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new SpringLayout());
        for(int i = 0; i < fieldLabels.length; i++){
            JLabel l = new JLabel(fieldLabels[i]);
            JTextField t = new JTextField(defaultLabels[i],15);
            t.setToolTipText(tootipText[i]);
            labelPanel.add(l);
            l.setLabelFor(t);
            labelPanel.add(t);
            textValues.put(fieldLabels[i],t);
        }

        SpringUtilities.makeCompactGrid(labelPanel,4,2,6,6,6,6);
        frame.setLayout(new FlowLayout());
        frame.add(labelPanel);
        JButton assignLabels = new JButton("Assign");
        assignLabels.setActionCommand("assign");
        assignLabels.addActionListener(this);
        frame.add(assignLabels);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("assign".equals(e.getActionCommand())){
            String[] loadLabels = new String[4];
            int count = 0;
            for(String s: textValues.keySet()){
                String label = textValues.get(s).getText();
                loadLabels[count]=label;
                count++;
            }
            FeatureManager.getWorldData().setArenaLabels(loadLabels);
        }
    }
}
