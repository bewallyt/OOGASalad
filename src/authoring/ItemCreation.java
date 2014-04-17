package authoring;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;

public class ItemCreation extends Feature implements ActionListener, ItemListener{
    private JFrame frame;
    private String iName;
    private int sv;
    private int av;
    private int dv;

    private String[] labels = {"Speed Boost:","Damage Boost:","Defense Boost:","Name:","Speed:","Damage:","Name2:",
            "Speed2:","Damage2:","On/Amount:"};
    private String[] playerAtt = {"Nothing","Damage","Defense","Health","Level","Speed"};

    private JComboBox attChoices;
    private JTextField amount;
    private Map<String,JTextField> textvals;
    private Attacks attack1;
    private Attacks attack2;
    private String optionalEffect;
    private int oeAmount;

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

        int result = JOptionPane.showOptionDialog(null, itemPane, "New Item/Weapon", JOptionPane.CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        int count = 0;

        if(result == JOptionPane.OK_OPTION) {
            iName = itemName.getText();
            if(itemName.getText().equals("")){
                showError();
            } else{
            if(!(isWeapon.isSelected())){
                for(int i=0; i<3; i++){
                    if(textvals.get(labels[i]).getText().equals("")){
                        count++;
                    }
            }
                if(count>0){
                   showError();
                } else{
                    sv = Integer.parseInt(textvals.get(labels[0]).getText());
                    av = Integer.parseInt(textvals.get(labels[1]).getText());
                    dv = Integer.parseInt(textvals.get(labels[2]).getText());
                    makeAndSaveItem();
                }
            } else{
                for(int i=3; i<9; i++){
                    if(textvals.get(labels[i]).getText().equals("")){
                        count++;
                    }
                }
                if(count>0){
                   showError();
                } else{
                    attack1 = new Attacks(textvals.get(labels[3]).getText(),
                            Integer.parseInt(textvals.get(labels[4]).getText()),
                            Integer.parseInt(textvals.get(labels[5]).getText()));
                    attack2 = new Attacks(textvals.get(labels[6]).getText(),
                            Integer.parseInt(textvals.get(labels[7]).getText()),
                            Integer.parseInt(textvals.get(labels[8]).getText()));
                    optionalEffect = (String)attChoices.getSelectedItem();
                    oeAmount = Integer.parseInt(amount.getText());
                    makeWeapon();

                }
            }
            }
        }


    }

    private void makeWeapon() {
        List<Attacks> wepAttacks = new ArrayList<Attacks>();
        wepAttacks.add(attack1);
        wepAttacks.add(attack2);
        Weapon madeWeapon = new Weapon(iName,wepAttacks,optionalEffect,oeAmount);
        FeatureManager.getWorldData().saveWeapons(madeWeapon);
    }


    private void makeAndSaveItem() {
        Item madeItem = new Item(iName,sv,av,dv);
        FeatureManager.getWorldData().saveItem(madeItem);
    }

    private void showError(){
        JOptionPane.showMessageDialog(frame, "Missing name and/or values.", "Error Message",
                JOptionPane.ERROR_MESSAGE);
        itemCreationPanel();
    }



}
