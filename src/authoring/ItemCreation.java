package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCreation extends Feature implements ActionListener{
    private JFrame frame;

    public ItemCreation(){
        JButton createItem = new JButton("Create Item");
        createItem.addActionListener(this);
        createItem.setActionCommand("create");
        myComponents.put(createItem, BorderLayout.SOUTH);
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
        JPanel panel1 = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                return size;
            }
        };
        panel1.add(itemName);
        JPanel panel2 = new JPanel(new SpringLayout());
        JLabel x = new JLabel("X");
        JLabel y = new JLabel("Y");
        panel2.add(x);
        panel2.add(y);
        x.setLabelFor(xCoor);
        panel2.add(xCoor);
        y.setLabelFor(yCoor);
        panel2.add(yCoor);

        SpringUtilities.makeCompactGrid(panel2,
                2, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JPanel panel3 = new JPanel();

        itemPane.addTab(nameTab,panel1);
        itemPane.addTab(locationTab,panel2);
        itemPane.addTab(attriTab,panel3);

        JOptionPane.showOptionDialog(null, itemPane, "New Item", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(itemName.getText().equals("")){
            JOptionPane.showMessageDialog(frame, "Name must be assigned.", "Error Message", JOptionPane.ERROR_MESSAGE);
            itemCreationPanel();
        }

        String iName = itemName.getText();
        int xc = Integer.parseInt(xCoor.getText());
        int yc = Integer.parseInt(yCoor.getText());



    }
}
