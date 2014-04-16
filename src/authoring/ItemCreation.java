package authoring;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCreation extends Feature implements ActionListener{
    private JFrame frame;
    private String iName;
    private int sv;
    private int av;
    private int dv;
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
        String attriTab = "Item Attributes";
        JTextField itemName = new JTextField(15);
        JTextField speedval = new JTextField(5);
        JTextField attackval = new JTextField(5);
        JTextField defenseval = new JTextField(5);


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
        JLabel speed = new JLabel("Speed Boost:",JLabel.TRAILING);
        JLabel attack = new JLabel("Attack Boost:",JLabel.TRAILING);
        JLabel defense = new JLabel("Defense Boost:", JLabel.TRAILING);

        panel2.add(speed);
        speed.setLabelFor(speedval);
        panel2.add(speedval);

        panel2.add(attack);
        attack.setLabelFor(attackval);
        panel2.add(attackval);

        panel2.add(defense);
        defense.setLabelFor(defenseval);
        panel2.add(defenseval);

        SpringUtilities.makeCompactGrid(panel2,
                3, 2,
                6, 6,
                6, 6);

        itemPane.addTab(nameTab,panel1);
        itemPane.addTab(attriTab,panel2);


        result = JOptionPane.showOptionDialog(null, itemPane, "New Item", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION) {
            if (itemName.getText().equals("") || speedval.getText().equals("") ||
                    attackval.getText().equals("") ||
                    defenseval.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Missing name and/or values.", "Error Message", JOptionPane.ERROR_MESSAGE);
                itemCreationPanel();
            } else{
                iName = itemName.getText();
                sv = Integer.parseInt(speedval.getText());
                av = Integer.parseInt(attackval.getText());
                dv = Integer.parseInt(defenseval.getText());
                makeAndSaveItem();
            }
        } else{}


    }

    private void makeAndSaveItem() {
        Item madeItem = new Item(iName,sv,av,dv);
        FeatureManager.getWorldData().saveItem(madeItem);
    }


}
