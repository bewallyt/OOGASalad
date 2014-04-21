package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class AddGameComponents extends Feature implements ActionListener {

    private String[] buttonNames = {"Item/Weapon","Player/Enemy", "Door", "Barrier", "NPC"};
    private ItemWeaponCreation itemWeaponCreation;
    private PlayerEnemyCreation playerEnemyCreation;
    private DoorCreation doorCreation;
    private BarrierCreation barrierCreation;
    private NPCCreation npcCreation;
    private JFrame frame;


    public AddGameComponents(){
        JButton add = new JButton("+");
        add.setActionCommand("add");
        add.addActionListener(this);
        myComponents.put(add, BorderLayout.SOUTH);
        itemWeaponCreation = new ItemWeaponCreation();
        playerEnemyCreation = new PlayerEnemyCreation();
        barrierCreation=new BarrierCreation();
        doorCreation=new DoorCreation();
        npcCreation=new NPCCreation();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("add".equals(e.getActionCommand())){
            showCreationOptions();
        } else if("item/weapon".equals(e.getActionCommand())){
            itemWeaponCreation.creationPanel();
            frame.dispose();
        } else if("player/enemy".equals(e.getActionCommand())){
            playerEnemyCreation.creationPanel();
            frame.dispose();
        } else if("barrier".equals(e.getActionCommand())){
        	barrierCreation.creationPanel();
        	frame.dispose();
        } else if ("door".equals(e.getActionCommand())){
        	doorCreation.creationPanel();
        	frame.dispose();
        } else if ("npc".equals(e.getActionCommand())){
        	npcCreation.creationPanel();
        	frame.dispose();
        }
        
    }


    private void showCreationOptions() {

        //some default weapons and items
        ArrayList<Attacks> fSwordAttack = new ArrayList<Attacks>();
        ArrayList<Attacks> iSwordAttack = new ArrayList<Attacks>();
        Attacks fireStab = new Attacks("Fire Stab",10,10);
        Attacks iceSlash = new Attacks("Ice Slash",10,10);
        fSwordAttack.add(fireStab);
        iSwordAttack.add(iceSlash);
        Weapon fireSword = new Weapon("Fire Sword","FSword",10,10,fSwordAttack);
        Weapon iceSword = new Weapon("Ice Sword","ISword",15,15,iSwordAttack);
        FeatureManager.getWorldData().saveWeapons("Fire Sword",fireSword);
        FeatureManager.getWorldData().saveWeapons("Ice Sword",iceSword);

        HashMap<String,Integer> potionValues = new HashMap<String, Integer>();
        String[] valueLabels = {"Speed","Damage","Defense","Health","Level"};
        for(int j=0; j<valueLabels.length; j++){
            potionValues.put(valueLabels[j],j+10);
        }

        Item potion = new Item("Super Potion","Jar",potionValues);
        FeatureManager.getWorldData().saveItem("Super Potion",potion);

        FeatureManager.getWeaponItemViewer().iterateWeaponsAndItems();
        frame=new JFrame();
        frame.setLocationRelativeTo(null);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.PAGE_AXIS));
        for(int i = 0; i<buttonNames.length; i++){
            JButton b = new JButton(buttonNames[i]);
            String command = buttonNames[i].replaceAll("\\s+","").toLowerCase();
            b.setActionCommand(command);
            b.addActionListener(this);
            buttonPanel.add(b);
        }
        frame.add(buttonPanel);
        frame.pack();
        frame.setVisible(true);

    }
}
