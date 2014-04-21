package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlayerEnemyCreation extends CommonAttributes implements MouseListener, ActionListener {

    private  JComboBox playerImages;
    private  String[] playerImageChoices = {"Ash","Zelda"};
    private  JList itemList;
    private int x;
    private int y;
    private String[] weaponNames;
    private String[] itemNames;
    private JTextField xcoor;
    private JTextField ycoor;
    private JCheckBox one;
    private JCheckBox two;
    private JCheckBox three;

    public PlayerEnemyCreation(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        if("mapenemy".equals(e.getActionCommand())){
            playerImages.setEnabled(false);
            imageName.setEnabled(true);
            itemList.setEnabled(false);
            xcoor.setEnabled(true);
            ycoor.setEnabled(true);
        } else if("random".equals(e.getActionCommand())){
            playerImages.setEnabled(false);
            imageName.setEnabled(true);
            itemList.setEnabled(false);
            xcoor.setEnabled(false);
            ycoor.setEnabled(false);
        } else{
            imageName.setEnabled(false);
            playerImages.setEnabled(true);
            itemList.setEnabled(true);
            xcoor.setEnabled(true);
            ycoor.setEnabled(true);
        }
    }

    public void creationPanel(){	
    	JTabbedPane pane = new JTabbedPane();
        String weaponItemTab = "Weapon/Items";
        String locationTab = "Location";

        ButtonGroup buttonChoices = new ButtonGroup();
        JRadioButton isPlayer = new JRadioButton("Player");
        isPlayer.setSelected(true);
        isPlayer.setActionCommand("player");
        isPlayer.addActionListener(this);
        JRadioButton isEnemy = new JRadioButton("Map Enemy");
        isEnemy.setActionCommand("mapenemy");
        isEnemy.addActionListener(this);
        isEnemy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    one.setEnabled(true);
                    two.setEnabled(true);
                    three.setEnabled(true);
                } else{
                    one.setEnabled(false);
                    two.setEnabled(false);
                    three.setEnabled(false);
                }
            }
        });
        JRadioButton isRandomEnemy = new JRadioButton("Random Enemy");
        isRandomEnemy.setActionCommand("random");
        isRandomEnemy.addActionListener(this);

        playerImages = new JComboBox(playerImageChoices);
        JPanel namePanel = nameImageFields();
        imageName.setEnabled(false);
        namePanel.add(playerImages);
        JPanel personPanel = new JPanel();
        buttonChoices.add(isPlayer);
        buttonChoices.add(isEnemy);
        buttonChoices.add(isRandomEnemy);
        personPanel.add(isPlayer);
        personPanel.add(isEnemy);
        personPanel.add(isRandomEnemy);
        namePanel.add(personPanel);

        JPanel movementPanel = new JPanel();
        ButtonGroup movementCheck = new ButtonGroup();
        one = new JCheckBox("1: Side-Side");
        two = new JCheckBox("2: Player Follow");
        three = new JCheckBox("3: Stand-Still");
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        movementCheck.add(one);
        movementCheck.add(two);
        movementCheck.add(three);
        movementPanel.add(one);
        movementPanel.add(two);
        movementPanel.add(three);
        namePanel.add(movementPanel);

        JPanel locationPanel = new JPanel(new SpringLayout());
        JLabel xcoordinate = new JLabel("X");
        JLabel ycoordinate = new JLabel("Y");
        xcoor = new JTextField("2",5);
        ycoor = new JTextField("2",5);
        locationPanel.add(xcoordinate);
        xcoordinate.setLabelFor(xcoor);
        locationPanel.add(xcoor);
        locationPanel.add(ycoordinate);
        ycoordinate.setLabelFor(ycoor);
        locationPanel.add(ycoor);
        SpringUtilities.makeCompactGrid(locationPanel,2,2,6,6,6,6);

        JPanel weaponItemPanel = new JPanel(new FlowLayout());
        DefaultListModel listModelWeapon = new DefaultListModel();
        JList weaponList = new JList(listModelWeapon);
        weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        weaponList.addMouseListener(this);
        weaponList.setVisibleRowCount(5);

        DefaultListModel listModelItems = new DefaultListModel();
        itemList = new JList(listModelItems);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addMouseListener(this);
        itemList.setVisibleRowCount(5);

        JScrollPane weaponScroll = new JScrollPane(weaponList);
        JScrollPane itemScroll = new JScrollPane(itemList);
        weaponItemPanel.add(weaponScroll);
        weaponItemPanel.add(itemScroll);

        pane.add(nameTab,namePanel);
        pane.add(locationTab,locationPanel);
        pane.add(attributeTab,attributeFields());
        pane.add(weaponItemTab,weaponItemPanel);

        int result = JOptionPane.showOptionDialog(null, pane, "New Player/Enemy", JOptionPane.CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION){
            name = itemName.getText();
            x = Integer.parseInt(xcoor.getText());
            y = Integer.parseInt(ycoor.getText());

            for(String s: textValues.keySet()){
                attributeValues.put(s,Integer.parseInt(textValues.get(s).getText()));
            }
            int wListSize = weaponList.getModel().getSize();
            int iListSize = itemList.getModel().getSize();
            weaponNames = new String[wListSize];
            itemNames = new String[iListSize];
            for(int i=0;i<wListSize;i++){
                Object weapon = weaponList.getModel().getElementAt(i);
                String weaponName = (String)weapon;
                weaponNames[i] = weaponName;
            }
            for(int j=0;j<iListSize;j++){
                Object item = itemList.getModel().getElementAt(j);
                String itemName = (String)item;
                itemNames[j] = itemName;
            }
            if(isEnemy.isSelected()||isRandomEnemy.isSelected()){
                image = imageName.getText();
                if(isRandomEnemy.isSelected()){
                    makeRandomEnemy();
                } else {
                    makeEnemy();
                }
            } else{
                image = (String) playerImages.getSelectedItem();
                makePlayer();
            }
        }
    }

    private void makeRandomEnemy() {
        RandomEnemy madeRandomEnemy = new RandomEnemy(Integer.parseInt(null),Integer.parseInt(null),
                image,name,attributeValues,weaponNames,itemNames);
        FeatureManager.getWorldData().saveRandomEnemy(madeRandomEnemy);
    }

    private void makePlayer() {
        PlayerData madePlayer = new PlayerData(x,y,image,name,attributeValues,weaponNames,itemNames);
        FeatureManager.getWorldData().savePlayer(madePlayer);
    }

    private void makeEnemy() {
        EnemyData madeEnemy = new EnemyData(x,y,image,name,attributeValues,weaponNames);
        madeEnemy.init();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
