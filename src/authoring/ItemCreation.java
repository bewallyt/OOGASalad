package authoring;


import engine.battle.Attack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ItemCreation extends CommonAttributes implements ActionListener, ItemListener, MouseListener{

    private String name;
    private String image;
    private int speed;
    private int damage;
    //private String[] attributes = {"Speed","Damage","Defense","Health","Level"};
    //private Map<String,JTextField> textValues;
    private Map<String,Integer> attributeValues;
    private List<Attacks> weaponAttacks;
    private DefaultListModel attackListModel;

    public ItemCreation(){
    }

    public void actionPerformed(ActionEvent e) {
        if("add".equals(e.getActionCommand())){
            attackCreation();
        } else{}
    }



    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            for(int j=2; j<5; j++){
                textValues.get(attributes[j]).setEnabled(false);
            }
        } else{
            for(int j=2; j<5; j++){
                textValues.get(attributes[j]).setEnabled(true);
            }
        }
    }

    protected void creationPanel() {
       //textValues = new HashMap<String, JTextField>();
       attributeValues = new HashMap<String, Integer>();
       weaponAttacks = new ArrayList<Attacks>();
       Attacks basicAttack = new Attacks("basic",2,2);
       weaponAttacks.add(basicAttack);

        String nameTab = "Name/Image";
        String attributeTab = "Attributes";
        String attackTab = "Weapon Attacks";

//        JLabel nameLabel = new JLabel("Name");
//        JLabel imageLabel = new JLabel("Image");
//        JTextField itemName = new JTextField("newItem",15);
//        JTextField imageName = new JTextField("defaultIW",15);
        JCheckBox isWeapon = new JCheckBox("Is A Weapon?");
        isWeapon.addItemListener(this);


        JTabbedPane itemPane = new JTabbedPane();
//        JPanel namePanel = new JPanel(){
//            public Dimension getPreferredSize() {
//                Dimension size = super.getPreferredSize();
//                size.width += 200;
//                return size;
//            }
//        };
//
//        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
//        namePanel.add(nameLabel);
//        namePanel.add(itemName);
//        namePanel.add(imageLabel);
//        namePanel.add(imageName);
        JPanel namePanel = nameImageFields();
        namePanel.add(isWeapon);

        JPanel attributePanel = attributeFields();

        JPanel attackPanel = new JPanel(new FlowLayout());
        JButton addattack = new JButton("+ Attack");
        addattack.setActionCommand("add");
        addattack.addActionListener(this);

        attackListModel = new DefaultListModel();
        JList attackList = new JList(attackListModel);
        attackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attackList.addMouseListener(this);
        attackList.setVisibleRowCount(4);
        JScrollPane aScroll = new JScrollPane(attackList);
        attackPanel.add(addattack);
        attackPanel.add(aScroll);

        itemPane.addTab(nameTab,namePanel);
        itemPane.addTab(attributeTab,attributePanel);
        itemPane.addTab(attackTab,attackPanel);

        int result = JOptionPane.showOptionDialog(null, itemPane, "New Item/Weapon", JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION) {

            name = itemName.getText();
            image = imageName.getText();

            if(isWeapon.isSelected()){
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
    }

    private void makeAndSaveItem() {
        Item madeItem = new Item(name,image,attributeValues);
        FeatureManager.getWorldData().saveItem(name,madeItem);
    }


    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
