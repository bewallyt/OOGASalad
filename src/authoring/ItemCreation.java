package authoring;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCreation extends Feature implements ActionListener{
    private JFrame frame;

    public ItemCreation(){
        JButton createItem = new JButton("Create Item");
        createItem.addActionListener(this);
        createItem.setActionCommand("create");
    }

    public void actionPerformed(ActionEvent e) {
        if("create".equals(e.getActionCommand())){
            itemCreationPanel();
        }
    }

    private void itemCreationPanel() {
        String nameTab = "Item Name";
        String locationTab = "Item Location";
        String attriTab = "Item Attributes";
        JTextField itemName = new JTextField(15);
        JTextField xCoor = new JTextField(5);
        JTextField yCoor = new JTextField(5);

        JTabbedPane itemPane = new JTabbedPane();
        JPanel panel1 = new JPanel();
        panel1.add(itemName);
        JPanel panel2 = new JPanel();
        panel2.add(xCoor);
        panel2.add(yCoor);
        JPanel panel3 = new JPanel();

        itemPane.addTab(nameTab,panel1);
        itemPane.addTab(locationTab,panel2);
        itemPane.addTab(attriTab,panel3);

        if(itemName.getText().equals("")){
            JOptionPane.showMessageDialog(frame, "Name must be assigned.", "Error Message", JOptionPane.ERROR_MESSAGE);
            itemCreationPanel();
        }

    }
}
