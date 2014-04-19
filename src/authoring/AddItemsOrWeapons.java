package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Prit on 4/18/14.
 */
public class AddItemsOrWeapons extends Feature implements ActionListener {

    private String[] buttonNames = {"Item/Weapon","Player","Map Enemy","Random Enemy"};
    private ItemCreation itemCreation;

    public AddItemsOrWeapons(){
        JButton add = new JButton("+");
        add.setActionCommand("add");
        add.addActionListener(this);
        myComponents.put(add, BorderLayout.SOUTH);
        itemCreation = new ItemCreation();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("add".equals(e.getActionCommand())){
            showCreationOptions();
        } else if("item/weapon".equals(e.getActionCommand())){
            itemCreation.creationPanel();
        } else{}
    }


    private void showCreationOptions() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
        for(int i = 0; i<buttonNames.length; i++){
            JButton b = new JButton(buttonNames[i]);
            String command = buttonNames[i].replaceAll("\\s+","").toLowerCase();
            b.setActionCommand(command);
            b.addActionListener(this);
            buttonPanel.add(b);
        }
        JOptionPane.showMessageDialog(null,buttonPanel,"Choose One",JOptionPane.PLAIN_MESSAGE);

    }
}
