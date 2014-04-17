package authoring;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public class ItemCreation extends Feature implements ActionListener, ItemListener{
    private JFrame frame;
    private String iName;
    private int sv;
    private int av;
    private int dv;
    private int result;
    private String[] labels = {"Speed Boost:","Damage Boost:","Defense Boost:","Name:","Speed:","Damage:","Name2:",
            "Speed2:","Damage2:","On/Amount:"};
    private String[] playerAtt = {"Nothing","Damage","Defense","Health","Level","Speed"};
    private JComboBox attChoices;
    private JTextField amount;
    private Map<String,JTextField> textvals;

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

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            for(int i=0; i<3; i++){
                textvals.get(labels[i]).setEnabled(false);
            }
            for(int i=3; i<9; i++){
                textvals.get(labels[i]).setEnabled(true);
            }
            attChoices.setEnabled(true);
            amount.setEnabled(true);
        } else{
            for(int i=0; i<3; i++){
                textvals.get(labels[i]).setEnabled(true);
            }
            for(int i=3; i<9; i++){
                textvals.get(labels[i]).setEnabled(false);
            }
            attChoices.setEnabled(false);
            amount.setEnabled(false);
        }

    }

    private void itemCreationPanel() {
        textvals = new HashMap<String, JTextField>();
        attChoices = new JComboBox(playerAtt);
        attChoices.setEnabled(false);
        amount = new JTextField(5);
        amount.setEnabled(false);

        String nameTab = "Item Name";
        String attriTab = "Item Attributes";
        String weaponTab = "Weapon Attacks";
        String weaponOPt = "Weapon Effect (Optional)";

        JTextField itemName = new JTextField(15);
        JCheckBox isWeapon = new JCheckBox("Is A Weapon?");
        isWeapon.addItemListener(this);


        JTabbedPane itemPane = new JTabbedPane();
        JPanel panel1 = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                return size;
            }
        };

        panel1.setLayout(new FlowLayout());
        panel1.add(itemName);
        panel1.add(isWeapon);
        panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JPanel panel2 = new JPanel(new SpringLayout());
        for(int i=0; i<3; i++){
            JLabel l = new JLabel(labels[i],JLabel.TRAILING);
            JTextField n = new JTextField(10);
            panel2.add(l);
            l.setLabelFor(n);
            panel2.add(n);
            textvals.put(labels[i],n);
        }

        SpringUtilities.makeCompactGrid(panel2,
                3, 2,
                6, 6,
                6, 6);

        JPanel panel3 = new JPanel(new SpringLayout());
        for (int i = 3; i < 9; i++) {
                JLabel l = new JLabel(labels[i],JLabel.TRAILING);
                JTextField n = new JTextField(10);
                panel3.add(l);
                l.setLabelFor(n);
                panel3.add(n);
                textvals.put(labels[i], n);
                n.setEnabled(false);
        }


        SpringUtilities.makeCompactGrid(panel3,
                6, 2,
                6, 6,
                6, 6);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        JLabel opt = new JLabel(labels[9]);
        panel4.add(opt);
        opt.setLabelFor(attChoices);
        panel4.add(attChoices);
        panel4.add(amount);

        itemPane.addTab(nameTab,panel1);
        itemPane.addTab(attriTab,panel2);
        itemPane.addTab(weaponTab,panel3);
        itemPane.addTab(weaponOPt,panel4);

        result = JOptionPane.showOptionDialog(null, itemPane, "New Item/Weapon", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION) {
//
//            if (itemName.getText().equals("") || speedval.getText().equals("") ||
//                    attackval.getText().equals("") ||
//                    defenseval.getText().equals("")) {
//                JOptionPane.showMessageDialog(frame, "Missing name and/or values.", "Error Message", JOptionPane.ERROR_MESSAGE);
//                itemCreationPanel();
//            } else{
//                iName = itemName.getText();
//                sv = Integer.parseInt(speedval.getText());
//                av = Integer.parseInt(attackval.getText());
//                dv = Integer.parseInt(defenseval.getText());
//                makeAndSaveItem();
//            }
        } else{}


    }

    private void makeAndSaveItem() {
//        Item madeItem = new Item(iName,sv,av,dv);
//        FeatureManager.getWorldData().saveItem(madeItem);
    }



}
