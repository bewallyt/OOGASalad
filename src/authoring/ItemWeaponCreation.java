package authoring;


import javax.swing.*;


import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.*;

public class ItemWeaponCreation extends CommonAttributes implements ActionListener, MouseListener {

    private int speed;
    private int damage;
    private List<Attacks> weaponAttacks;
    private DefaultListModel attackListModel;
    private JList attackList;
    private JButton addAttack;
    private JCheckBox isWeapon;
    private JCheckBox isObjectiveItem;
    private JCheckBox isItem;
    private JFrame frame;

    public ItemWeaponCreation(){
    }

    public void actionPerformed(ActionEvent e) {
        if("add".equals(e.getActionCommand())){
            attackCreation();
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
                    addAttack.setEnabled(true);
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
                } else{
                }
            }
        });

        JPanel namePanel = nameImageFields();
        buttonGroup.add(isObjectiveItem);
        buttonGroup.add(isWeapon);
        buttonGroup.add(isItem);
        JPanel miniPanel = new JPanel();
        miniPanel.add(isItem);
        miniPanel.add(isObjectiveItem);
        miniPanel.add(isWeapon);
        namePanel.add(miniPanel);

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

        pane.addTab(nameTab, namePanel);
        pane.addTab(attributeTab, attributePanel);
        pane.addTab(attackTab, attackPanel);

        
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
             if(weaponAttacks.size()<4) {
                 weaponAttacks.add(newAttack);
                 attackListModel.addElement(nf.getText());
             } else{
                 JOptionPane.showMessageDialog(null,"Weapon cannot have more than 4 attacks");
             }
        }
    }

    private void makeWeapon() {
        Weapon madeWeapon = new Weapon(name,editor.getSelectedImage().getDescription(),speed,damage,weaponAttacks);
        FeatureManager.getWorldData().saveWeapons(name,madeWeapon);
        FeatureManager.getWeaponItemViewer().iterateWeaponsAndItems();

    }

    private void makeAndSaveItem() {
        Item madeItem;
        if(!isObjectiveItem.isSelected()){
            madeItem = new Item(name,editor.getSelectedImage().getDescription(),attributeValues);
        } else{
            madeItem = new Item(name);
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
                        editPanel.add(name);
                        editPanel.add(speed);
                        editPanel.add(damage);
                        int edit = JOptionPane.showOptionDialog(null, editPanel, "Edit Attack",
                                JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if(edit==JOptionPane.OK_OPTION){
                            Attacks editedAttack = new Attacks(name.getText(),Integer.parseInt(speed.getText()),
                                    Integer.parseInt(damage.getText()));
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
