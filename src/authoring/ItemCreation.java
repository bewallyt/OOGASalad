package authoring;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCreation extends Feature implements ActionListener{
    private JFrame frame;
    private String iName;
    private int xc;
    private int yc;
    private int result;

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
//        JPanel panel2 = new JPanel(new SpringLayout());
//        JLabel x = new JLabel("X",JLabel.TRAILING);
//        JLabel y = new JLabel("Y",JLabel.TRAILING);
//        panel2.add(x);
//        x.setLabelFor(xCoor);
//        panel2.add(xCoor);
//        panel2.add(y);
//        y.setLabelFor(yCoor);
//        panel2.add(yCoor);
//
//        SpringUtilities.makeCompactGrid(panel2,
//                2, 2,
//                6, 6,
//                6, 6);

        JPanel panel3 = new JPanel();

        itemPane.addTab(nameTab,panel1);
        //itemPane.addTab(locationTab,panel2);
        itemPane.addTab(attriTab,panel3);

        result = JOptionPane.showOptionDialog(null, itemPane, "New Item", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION) {
            if (itemName.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Missing name or coordinates.", "Error Message", JOptionPane.ERROR_MESSAGE);
                itemCreationPanel();
            } else{
                iName = itemName.getText();
                //xc = Integer.parseInt(xCoor.getText());
                //yc = Integer.parseInt(yCoor.getText());
                makeAndSaveItem();
            }
        } else{}


    }

    private void makeAndSaveItem() {
        Item madeItem = new Item(iName);
        FeatureManager.getWorldData().saveItem(madeItem);
    }


}
