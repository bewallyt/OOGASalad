package authoring.gameObjects;

/**
 * @ Pritam M.
 * @ Davis Treybig
 * */

import javax.swing.*;

import authoring.SpringUtilities;
import authoring.features.FeatureManager;


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ItemWeaponCreation extends CommonAttributes implements ActionListener, MouseListener {

    private int speed;
    private int damage;
    private List<AttacksData> weaponAttacks;
    private DefaultListModel attackListModel;
    private JList attackList;
    private JButton addAttack;
    private JCheckBox isWeapon;
    private JCheckBox isObjectiveItem;
    private JCheckBox isItem;
    private JFrame frame;
    private JTextField priceField;

    public ItemWeaponCreation(){
    }

    public void actionPerformed(ActionEvent e) {
        if("add".equals(e.getActionCommand())){
            attackCreation();
        }
    }

    public void creationPanel() {
        JTabbedPane pane = new JTabbedPane();
        weaponAttacks = new ArrayList<AttacksData>();
        AttacksData basicAttack = new AttacksData("Basic",2,2,"Health",5,false);
        weaponAttacks.add(basicAttack);

        String attackTab = "Weapon Attacks";
        String priceTab = "Price";

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
                    addAttack.setEnabled(true);
                    priceField.setEnabled(false);
                } else{
                    for(int j=2; j<5; j++){
                        textValues.get(attributes[j]).setEnabled(true);
                    }
                    attackList.setEnabled(false);
                    addAttack.setEnabled(false);
                }
            }
        });

        isObjectiveItem = new JCheckBox("Is Objective Item");
        isObjectiveItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    for (int j = 0; j < 5; j++) {
                        textValues.get(attributes[j]).setEnabled(false);
                    }
                    attackList.setEnabled(false);
                    addAttack.setEnabled(false);
                    priceField.setEnabled(true);
                } else {
                    for (int j = 0; j < 5; j++) {
                        textValues.get(attributes[j]).setEnabled(true);
                    }
                    attackList.setEnabled(false);
                    addAttack.setEnabled(false);

                }
            }
        });

        isItem = new JCheckBox("Is Item");
        isItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    for(int j=0; j<5; j++){
                        textValues.get(attributes[j]).setEnabled(true);
                    }
                    attackList.setEnabled(false);
                    addAttack.setEnabled(false);
                    priceField.setEnabled(true);
                } else{
                }
            }
        });

        JPanel namePanel = nameImageFields();
        JPanel combinedPanel = new JPanel();
        combinedPanel.setLayout(new BoxLayout(combinedPanel,BoxLayout.PAGE_AXIS));
        buttonGroup.add(isObjectiveItem);
        buttonGroup.add(isWeapon);
        buttonGroup.add(isItem);
        JPanel miniPanel = new JPanel();
        miniPanel.add(isItem);
        miniPanel.add(isObjectiveItem);
        miniPanel.add(isWeapon);
        combinedPanel.add(namePanel);
        combinedPanel.add(miniPanel);

        JPanel attributePanel = attributeFields();

        JPanel attackPanel = new JPanel();
        attackPanel.setLayout(new BoxLayout(attackPanel,BoxLayout.PAGE_AXIS));
        addAttack = new JButton("+ Attack");
        addAttack.setActionCommand("add");
        addAttack.addActionListener(this);
        addAttack.setEnabled(false);

        attackListModel = new DefaultListModel();
        attackList = new JList(attackListModel);
        attackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attackList.setVisibleRowCount(4);
        attackList.setEnabled(false);
        attackList.addMouseListener(this);
        attackListModel.addElement(basicAttack.getMyName());
        JScrollPane aScroll = new JScrollPane(attackList);
        attackPanel.add(addAttack);
        attackPanel.add(aScroll);

        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new SpringLayout());
        priceField = new JTextField("10",15);
        priceField.setToolTipText("Buying price from a shopkeeper.");
        JLabel priceLabel = new JLabel("Price");
        pricePanel.add(priceLabel);
        priceLabel.setLabelFor(priceField);
        pricePanel.add(priceField);
        SpringUtilities.makeCompactGrid(pricePanel,1,2,6,6,6,6);

        pane.addTab(nameTab, combinedPanel);
        pane.addTab(attributeTab, attributePanel);
        pane.addTab(attackTab, attackPanel);
        pane.add(priceTab,pricePanel);

        
        frame=new JFrame("New Item/Weapon");
        frame.setLayout(new FlowLayout());
        frame.add(pane);
        JButton createNPC=new JButton("Create Item/Weapon");
        createNPC.addActionListener(new ItemWeaponActionListener());
        frame.add(createNPC);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
  

    }
    private class ItemWeaponActionListener implements ActionListener{
    	 
		@Override
		public void actionPerformed(ActionEvent e) {
			  name = itemName.getText();

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
			
	           editor.dispose();
	           frame.dispose();
		}
    }
    private void attackCreation(){
        JPanel attackPanel = new JPanel(new SpringLayout());
        JLabel n = new JLabel("Name");
        JLabel s = new JLabel("Speed");
        JLabel d = new JLabel("Damage");
        JLabel amount = new JLabel("Amount");
        JTextField nf = new JTextField("newAttack",15);
        JTextField sf = new JTextField("5",15);
        JTextField df = new JTextField("5",15);
        JTextField ef = new JTextField("5",15);
        JComboBox aAt = new JComboBox(attributes);
        JCheckBox affectsWho = new JCheckBox("Affects Player? (Default - Affects Enemy)");
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

        JPanel effectPanel = new JPanel(new FlowLayout());
        effectPanel.add(aAt);
        effectPanel.add(amount);
        effectPanel.add(ef);

        JPanel overPanel = new JPanel();
        overPanel.setLayout(new BoxLayout(overPanel,BoxLayout.PAGE_AXIS));
        overPanel.add(attackPanel);
        overPanel.add(effectPanel);
        overPanel.add(affectsWho);
        
        int result = JOptionPane.showOptionDialog(null, overPanel, "New Attack", JOptionPane.CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(result==JOptionPane.OK_OPTION){
             AttacksData newAttack = new AttacksData(nf.getText(),Integer.parseInt(sf.getText()),
                     Integer.parseInt(df.getText()),(String)aAt.getSelectedItem(),Integer.parseInt(ef.getText()),
                     affectsWho.isSelected());
             if(weaponAttacks.size()<4) {
                 weaponAttacks.add(newAttack);
                 attackListModel.addElement(nf.getText());
             } else{
                 JOptionPane.showMessageDialog(null,"Weapon cannot have more than 4 attacks");
             }
        }
    }

    private void makeWeapon() {
        WeaponData madeWeapon = new WeaponData(name,editor.getSelectedImage().getDescription(),speed,damage,
                weaponAttacks);
        FeatureManager.getWorldData().saveWeapons(name,madeWeapon);
        FeatureManager.getWeaponItemViewer().iterateWeaponsAndItems();

    }

    private void makeAndSaveItem() {
        ItemData madeItem;
        if(!isObjectiveItem.isSelected()){
            madeItem = new ItemData(name,editor.getSelectedImage().getDescription(),attributeValues,
                    Integer.parseInt(priceField.getText()));
        } else{
            madeItem = new ItemData(name,Integer.parseInt(priceField.getText()));
        }
        FeatureManager.getWorldData().saveItem(name,madeItem);
        FeatureManager.getWeaponItemViewer().iterateWeaponsAndItems();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
            if (attackList.getSelectedIndex() != -1) {
                String clicked = (String)attackList.getSelectedValue();
                for(int i=0; i<weaponAttacks.size(); i++){
                    if(weaponAttacks.get(i).getMyName().equals(clicked)){
                        JPanel editPanel = new JPanel();
                        JTextField name = new JTextField(clicked,10);
                        JTextField speed = new JTextField(String.valueOf(weaponAttacks.get(i).getMySpeed()),5);
                        JTextField damage = new JTextField(String.valueOf(weaponAttacks.get(i).getMyDamage()),5);
                        JComboBox affects = new JComboBox(attributes);
                        JTextField amount = new JTextField(String.valueOf(weaponAttacks.get(i).getAffectValue()),5);
                        JCheckBox affectsWho = new JCheckBox("Player");
                        editPanel.add(name);
                        editPanel.add(speed);
                        editPanel.add(damage);
                        editPanel.add(affects);
                        editPanel.add(amount);
                        editPanel.add(affectsWho);
                        int edit = JOptionPane.showOptionDialog(null, editPanel, "Edit Attack",
                                JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if(edit==JOptionPane.OK_OPTION){
                            AttacksData editedAttack = new AttacksData(name.getText(),Integer.parseInt(speed.getText()),
                                    Integer.parseInt(damage.getText()),(String)affects.getSelectedItem(),
                                    Integer.parseInt(amount.getText()),
                                    affectsWho.isSelected());
                            weaponAttacks.remove(i);
                            weaponAttacks.add(editedAttack);
                            attackListModel.remove(attackList.getSelectedIndex());
                            attackListModel.addElement(name.getText());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
