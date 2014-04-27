package authoring;

/**
 * @ Pritam M.
 * @ Davis Treybig
 * */

import javax.swing.*;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;


@SuppressWarnings("ALL")
public class PlayerEnemyCreation extends CommonAttributes implements ActionListener {

    private JComboBox<String> playerEnemyImages;
    private String[] playerEnemyImageChoices = {"Ash","Zelda"};
    private String[] weaponNames;
    private String[] itemNames;
    private JCheckBox one;
    private JCheckBox two;
    private JCheckBox three;
    private Object movement;
    private JRadioButton isRandomEnemy;
    private JRadioButton isEnemy;
    private ButtonGroup movementCheck;
    private JTextField mon;
    private JTextField exp;

    public PlayerEnemyCreation(){}

    @Override
    public void actionPerformed(ActionEvent e) {
        if("mapenemy".equals(e.getActionCommand())){
            playerEnemyImages.setEnabled(true);
            itemList.setEnabled(false);
            xcoor.setEnabled(true);
            ycoor.setEnabled(true);
            mon.setEnabled(true);
            exp.setEnabled(true);

        } else if("random".equals(e.getActionCommand())){
            playerEnemyImages.setEnabled(false);
            itemList.setEnabled(false);
            xcoor.setEnabled(false);
            ycoor.setEnabled(false);
            mon.setEnabled(true);
            exp.setEnabled(true);

        } else{
            playerEnemyImages.setEnabled(true);
            itemList.setEnabled(true);
            xcoor.setEnabled(true);
            ycoor.setEnabled(true);
            mon.setEnabled(false);
            exp.setEnabled(false);

        }
    }

    public void creationPanel(){
    	JTabbedPane pane = new JTabbedPane();
        String weaponItemTab = "Weapon/Items";
        ButtonGroup buttonChoices = new ButtonGroup();
        JRadioButton isPlayer = new JRadioButton("Player");
        isPlayer.setSelected(true);
        isPlayer.setActionCommand("player");
        isPlayer.addActionListener(this);
        isEnemy = new JRadioButton("Map Enemy");
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
        isRandomEnemy = new JRadioButton("Random Enemy");
        isRandomEnemy.setActionCommand("random");
        isRandomEnemy.addActionListener(this);

        playerEnemyImages = new JComboBox<String>(playerEnemyImageChoices);
        JPanel namePanel = nameImageFields();
        JPanel superNamePanel = new JPanel();
        superNamePanel.setLayout(new BoxLayout(superNamePanel,BoxLayout.PAGE_AXIS));

        superNamePanel.add(namePanel);
        superNamePanel.add(playerEnemyImages);
        JPanel personPanel = new JPanel();
        buttonChoices.add(isPlayer);
        buttonChoices.add(isEnemy);
        buttonChoices.add(isRandomEnemy);
        personPanel.add(isPlayer);
        personPanel.add(isEnemy);
        personPanel.add(isRandomEnemy);
        superNamePanel.add(personPanel);

        JPanel movementPanel = new JPanel();
        movementCheck = new ButtonGroup();
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
        superNamePanel.add(movementPanel);

        JPanel locationPanel = locationFields();

        JPanel obtainPanel = new JPanel(new SpringLayout());
        JLabel money = new JLabel("Money:");
        JLabel experience = new JLabel("Experience:");
        mon = new JTextField("100",5);
        exp = new JTextField("50",5);
        mon.setEnabled(false);
        exp.setEnabled(false);
        obtainPanel.add(money);
        money.setLabelFor(mon);
        obtainPanel.add(mon);
        obtainPanel.add(experience);
        experience.setLabelFor(exp);
        obtainPanel.add(exp);
        SpringUtilities.makeCompactGrid(obtainPanel,2,2,6,10,6,10);

        JPanel combinePanel = new JPanel();
        combinePanel.setLayout(new BoxLayout(combinePanel,BoxLayout.PAGE_AXIS));
        combinePanel.add(locationPanel);
        combinePanel.add(obtainPanel);

        JLabel playInfo = new JLabel("Player: max - 4 weapons & 4 items",JLabel.CENTER);
        JLabel meneInfo = new JLabel("Map Enemy: max - 4 weapons & no items",JLabel.CENTER);
        JLabel randInfo = new JLabel("Random Enemy: max - 1 weapon & no items",JLabel.CENTER);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.PAGE_AXIS));
        infoPanel.add(playInfo);
        infoPanel.add(meneInfo);
        infoPanel.add(randInfo);

        JPanel overPanel = new JPanel();
        overPanel.setLayout(new BoxLayout(overPanel,BoxLayout.PAGE_AXIS));

        JPanel weaponItemPanel = new JPanel();
        weaponItemPanel.setLayout(new BoxLayout(weaponItemPanel,BoxLayout.LINE_AXIS));
        weaponList = new JList(weaponListModel);
        weaponList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        weaponList.setVisibleRowCount(5);
        weaponList.setTransferHandler(new TransferHandler(){
            public boolean canImport(TransferSupport info){
                if(!info.isDataFlavorSupported(DataFlavor.stringFlavor)){
                    return false;
                }
                JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
                if(dl.getIndex()==-1){
                    return false;
                }
                return true;
            }

            public boolean importData(TransferSupport info){
                if(!info.isDrop()){
                    return false;
                }
                if(!info.isDataFlavorSupported(DataFlavor.stringFlavor)){
                    return false;
                }

                JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
                DefaultListModel listModel = (DefaultListModel) weaponList.getModel();
                int index = dl.getIndex();
                boolean insert = dl.isInsert();
                Transferable t = info.getTransferable();
                String data;
                try{
                    data = (String)t.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e){
                    return false;
                }
                if(insert){
                    listModel.add(index,data);
                } else{
                    listModel.set(index,data);
                }
                return true;
            }
        });

        itemList = new JList(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        itemList.setVisibleRowCount(5);

        JScrollPane weaponScroll = new JScrollPane(weaponList);
        JScrollPane itemScroll = new JScrollPane(itemList);
        weaponItemPanel.add(weaponScroll);
        weaponItemPanel.add(itemScroll);

        overPanel.add(infoPanel);
        overPanel.add(weaponItemPanel);

        pane.add(nameTab,superNamePanel);
        pane.add(locationTab,combinePanel);
        pane.add(attributeTab,attributeFields());
        pane.add(weaponItemTab,overPanel);

        frame=new JFrame("Player/Enemy Creation");
        frame.setLayout(new FlowLayout());
        frame.add(pane);
        JButton createNPC=new JButton("Create Player/Enemy");
        createNPC.addActionListener(new PlayerEnemyActionListener());
        frame.add(createNPC);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        iterateWeaponsAndItems();
        
    }

    private class PlayerEnemyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			 name = itemName.getText();
	            x = Integer.parseInt(xcoor.getText());
	            y = Integer.parseInt(ycoor.getText());
	            image = (String) playerEnemyImages.getSelectedItem();

	            for(String s: textValues.keySet()){
	                attributeValues.put(s,Integer.parseInt(textValues.get(s).getText()));
	            }
	            int wListSize = weaponList.getModel().getSize();
	            int iListSize = itemList.getModel().getSize();
                int wCounter = 0;
                int iCounter = 0;
	            weaponNames = new String[wListSize];
	            itemNames = new String[iListSize];
                for(Object o: weaponList.getSelectedValuesList()){
                    String weaponName = (String)o;
                    weaponNames[wCounter] = weaponName;
                    wCounter++;
                }

                for(Object p: itemList.getSelectedValuesList()){
                    String item = (String)p;
                    itemNames[iCounter] = item;
                    iCounter++;
                }

	            if(isEnemy.isSelected()||isRandomEnemy.isSelected()){
	                if(isRandomEnemy.isSelected()){
	                    makeRandomEnemy();
	                } else {
	                    movement = movementCheck.getSelection();
	                    makeEnemy();
	                }
	            } else{
	                makePlayer();
	            }
		        frame.dispose();
		}
    	
    }
    private void makeRandomEnemy() {
        RandomEnemy madeRandomEnemy = new RandomEnemy(Integer.parseInt(null),Integer.parseInt(null),
                editor.getSelectedImage().getDescription(),name,attributeValues,weaponNames,Integer.parseInt(null),
                Integer.parseInt(mon.getText()),Integer.parseInt(exp.getText()));
        FeatureManager.getWorldData().saveRandomEnemy(madeRandomEnemy);
    }

    private void makePlayer() {
        PlayerData madePlayer = new PlayerData(x,y,image,name,attributeValues,weaponNames,itemNames);
        FeatureManager.getWorldData().setPrimaryMap(FeatureManager.getWorldData().getCurrentMapName());	
        FeatureManager.getWorldData().savePlayer(madePlayer);
    }

    private void makeEnemy() {
        int move = 1;
        if(movement.equals(one)){
            move=1;
        } else if(movement.equals(two)){
            move=2;
        } else{
            move=3;
        }
        EnemyData madeEnemy = new EnemyData(x,y,image,name,attributeValues,weaponNames,move,
                Integer.parseInt(mon.getText()),Integer.parseInt(exp.getText()));
        madeEnemy.init();
    }


}
