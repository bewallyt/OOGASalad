package authoring;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ItemWeaponCreation extends CommonAttributes implements ActionListener{

    private int speed;
    private int damage;
    private List<Attacks> weaponAttacks;
    private DefaultListModel attackListModel;
    private JList attackList;
    private JButton addattack;
    protected JCheckBox isWeapon;
    protected JCheckBox isSpecialItem;

    public ItemWeaponCreation(){
    }

    public void actionPerformed(ActionEvent e) {
        if("add".equals(e.getActionCommand())){
            attackCreation();
        } else if("weapon".equals(e.getActionCommand())){
            for(int j=2; j<5; j++){
                textValues.get(attributes[j]).setEnabled(false);
            }
            attackList.setEnabled(true);
            addattack.setEnabled(true);
        } else if("simple".equals(e.getActionCommand())){
            for(int j=0; j<5; j++){
                textValues.get(attributes[j]).setEnabled(false);
            }
            attackList.setEnabled(false);
            addattack.setEnabled(false);
        } else{
            for(int j=0; j<5; j++){
                textValues.get(attributes[j]).setEnabled(true);
            }
            attackList.setEnabled(false);
            addattack.setEnabled(false);
        }
    }

    protected void creationPanel() {
        JTabbedPane pane = new JTabbedPane();
        weaponAttacks = new ArrayList<Attacks>();
        Attacks basicAttack = new Attacks("Basic",2,2);
        weaponAttacks.add(basicAttack);

        String attackTab = "Weapon Attacks";

        ButtonGroup buttonGroup = new ButtonGroup();
        isWeapon = new JCheckBox("Is Weapon");
        isWeapon.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    for(int j=2; j<5; j++){
                        textValues.get(attributes[j]).setEnabled(false);
                    }
                    attackList.setEnabled(true);
                    addattack.setEnabled(true);
                } else{
                    for(int j=2; j<5; j++){
                        textValues.get(attributes[j]).setEnabled(true);
                    }
                    attackList.setEnabled(false);
                    addattack.setEnabled(false);
                }
            }
        });

        isSpecialItem = new JCheckBox("Is Objective Item");
        isSpecialItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    for(int j=0; j<5; j++){
                        textValues.get(attributes[j]).setEnabled(false);
                    }
                    attackList.setEnabled(false);
                    addattack.setEnabled(false);
                } else{
                    for(int j=0; j<5; j++){
                        textValues.get(attributes[j]).setEnabled(true);
                    }
                    attackList.setEnabled(false);
                    addattack.setEnabled(false);
                }
            }
        });

        JPanel namePanel = nameImageFields();
        buttonGroup.add(isSpecialItem);
        buttonGroup.add(isWeapon);
        namePanel.add(isSpecialItem);
        namePanel.add(isWeapon);


        JPanel attributePanel = attributeFields();

        JPanel attackPanel = new JPanel();
        attackPanel.setLayout(new BoxLayout(attackPanel,BoxLayout.PAGE_AXIS));
        addattack = new JButton("+ Attack");
        addattack.setActionCommand("add");
        addattack.addActionListener(this);
        addattack.setEnabled(false);

        attackListModel = new DefaultListModel();
        attackList = new JList(attackListModel);
        attackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attackList.setVisibleRowCount(4);
        attackList.setEnabled(false);
        attackListModel.addElement(basicAttack.getMyName());
        JScrollPane aScroll = new JScrollPane(attackList);
        attackPanel.add(addattack);
        attackPanel.add(aScroll);

        pane.addTab(nameTab, namePanel);
        pane.addTab(attributeTab, attributePanel);
        pane.addTab(attackTab, attackPanel);

        int result = JOptionPane.showOptionDialog(null, pane, "New Item/Weapon", JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION) {

            name = itemName.getText();
            image = imageName.getText();

            if (isWeapon.isSelected()) {
                speed = Integer.parseInt(textValues.get(attributes[0]).getText());
                damage = Integer.parseInt(textValues.get(attributes[1]).getText());
                makeWeapon();
            } else {
                for (String s : textValues.keySet()) {
                    attributeValues.put(s, Integer.parseInt(textValues.get(s).getText()));
                }
                makeAndSaveItem();
            }

        }

    }

    private void attackCreation(){
        JPanel attackPanel = new JPanel(new SpringLayout());
        JLabel n = new JLabel("Name");
        JLabel s = new JLabel("Speed");
        JLabel d = new JLabel("Damage");
        JTextField nf = new JTextField("newAttack",15);
        JTextField sf = new JTextField("5",15);
        JTextField df = new JTextField("5",15);
        attackPanel.add(n);
        n.setLabelFor(nf);
        attackPanel.add(nf);
        attackPanel.add(s);
        s.setLabelFor(sf);
        attackPanel.add(sf);
        attackPanel.add(d);
        d.setLabelFor(df);
        attackPanel.add(df);

        SpringUtilities.makeCompactGrid(attackPanel,
                     3,2,
                     6,6,
                     6,6);

        int result = JOptionPane.showOptionDialog(null, attackPanel, "New Attack", JOptionPane.CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(result==JOptionPane.OK_OPTION){
             Attacks newAttack = new Attacks(nf.getText(),Integer.parseInt(sf.getText()),
                     Integer.parseInt(df.getText()));
             weaponAttacks.add(newAttack);
             attackListModel.addElement(nf.getText());
        }
    }

    private void makeWeapon() {
        Weapon madeWeapon = new Weapon(name,image,speed,damage,weaponAttacks);
        FeatureManager.getWorldData().saveWeapons(name,madeWeapon);
        FeatureManager.getWeaponItemViewer().iterateWeaponsAndItems();

    }

    private void makeAndSaveItem() {
        Item madeItem;
        if(isSpecialItem.isSelected()){
            madeItem = new Item(name,image,attributeValues);
        } else{
            madeItem = new Item(name);
        }
        FeatureManager.getWorldData().saveItem(name,madeItem);
        FeatureManager.getWeaponItemViewer().iterateWeaponsAndItems();
    }

}
