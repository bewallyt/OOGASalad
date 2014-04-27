package authoring.features;

/**
 * @ Pritam M.
 * */

import javax.swing.*;

import authoring.gameObjects.AttacksData;
import authoring.gameObjects.BarrierCreation;
import authoring.gameObjects.DoorCreation;
import authoring.gameObjects.EncounterCreation;
import authoring.gameObjects.ItemData;
import authoring.gameObjects.ItemWeaponCreation;
import authoring.gameObjects.LabelsCreation;
import authoring.gameObjects.NPCCreation;
import authoring.gameObjects.PlayerEnemyCreation;
import authoring.gameObjects.WeaponData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class AddGameComponents extends Feature implements ActionListener {

    private String[] buttonNames = {"Item/Weapon","Player/Enemy", "Door", "Barrier", "NPC", "EncounterTile",
            "Arena Labels"};
    private ItemWeaponCreation itemWeaponCreation;
    private PlayerEnemyCreation playerEnemyCreation;
    private DoorCreation doorCreation;
    private BarrierCreation barrierCreation;
    private NPCCreation npcCreation;
    private EncounterCreation encounterCreation;
    private LabelsCreation labelsCreation;
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
        encounterCreation = new EncounterCreation();
        labelsCreation = new LabelsCreation();

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
        } else if("door".equals(e.getActionCommand())){
        	doorCreation.creationPanel();
        	frame.dispose();
        } else if("npc".equals(e.getActionCommand())){
        	npcCreation.creationPanel();
        	frame.dispose();
        } else if("encountertile".equals(e.getActionCommand())){
        	encounterCreation.creationPanel();
        	frame.dispose();
        } else if("arenalabels".equals(e.getActionCommand())){
            labelsCreation.creationPanel();
            frame.dispose();
        }
        
    }

    private void showCreationOptions() {

        //some default weapons and items
        ArrayList<AttacksData> fSwordAttack = new ArrayList<AttacksData>();
        ArrayList<AttacksData> iSwordAttack = new ArrayList<AttacksData>();
        AttacksData fireStab = new AttacksData("Fire Stab",10,10,"Damage",10,true);
        AttacksData iceSlash = new AttacksData("Ice Slash",10,10,"Speed",10,true);
        fSwordAttack.add(fireStab);
        iSwordAttack.add(iceSlash);
        WeaponData fireSword = new WeaponData("Fire Sword","FSword",10,10,fSwordAttack);
        WeaponData iceSword = new WeaponData("Ice Sword","ISword",15,15,iSwordAttack);
        FeatureManager.getWorldData().saveWeapons("Fire Sword",fireSword);
        FeatureManager.getWorldData().saveWeapons("Ice Sword",iceSword);

        HashMap<String,Integer> potionValues = new HashMap<String, Integer>();
        String[] valueLabels = {"Speed","Damage","Defense","Health","Level"};
        for(int j=0; j<valueLabels.length; j++){
            potionValues.put(valueLabels[j],j+10);
        }

        ItemData potion = new ItemData("Super Potion","Jar",potionValues);
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
