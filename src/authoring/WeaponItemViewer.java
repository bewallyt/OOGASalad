package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeaponItemViewer {

    private JFrame weaponFrame;
    private JFrame detailFrame;
    private DefaultListModel weaponListModel;
    private JList weaponList;
    private DefaultListModel itemListModel;
    private JList itemList;
    private Map<String,Weapon> weaponMap;
    private Map<String,Item> itemMap;

    public WeaponItemViewer(){
        weaponMap = new HashMap<String, Weapon>();
        itemMap = new HashMap<String, Item>();
        detailFrame = new JFrame();
        weaponFrame = new JFrame("Existing Weapons/Items");
        weaponFrame.setLayout(new BorderLayout());
        weaponFrame.setBounds(100, 0, 300, 300);
        weaponListModel = new DefaultListModel();
        weaponList = new JList(weaponListModel);
        weaponList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        weaponList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    if (weaponList.getSelectedIndex() != -1) {
                        String clicked = (String)weaponList.getSelectedValue();
                        HashMap<String,Weapon> detailsWeaponMap =
                                (HashMap<String, Weapon>) FeatureManager.getWorldData().getMyWeapons();
                        for(String s: detailsWeaponMap.keySet()){
                            if(clicked.equals(s)){
                                Weapon editWeapon = detailsWeaponMap.get(s);
                                JPanel detailsPanel = new JPanel();
                                detailsPanel.setLayout(new BoxLayout(detailsPanel,BoxLayout.PAGE_AXIS));
                                ArrayList<Attacks> detailAttacks = (ArrayList<Attacks>) editWeapon.getMyAttacks();
                                JLabel dWeapon = new JLabel(editWeapon.getMyName()+" -"+" Speed:"+
                                        String.valueOf(editWeapon.getMySpeed())+" -"+" Damage:"+
                                        String.valueOf(editWeapon.getMyDamage()));
                                detailsPanel.add(dWeapon);
                                JLabel newLine = new JLabel("<html><br></html>");
                                JLabel attack = new JLabel("Attacks:");
                                detailsPanel.add(newLine);
                                detailsPanel.add(attack);
                                for(int i=0;i<detailAttacks.size();i++){
                                    JLabel dAttack = new JLabel(detailAttacks.get(i).getMyName()+" -"+" Speed:"+
                                    String.valueOf(detailAttacks.get(i).getMySpeed())+" -"+" Damage:"+
                                    String.valueOf(detailAttacks.get(i).getMyDamage()));
                                    detailsPanel.add(dAttack);
                                }
                                JOptionPane.showMessageDialog(detailFrame,detailsPanel,"Details",
                                        JOptionPane.INFORMATION_MESSAGE);
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
        });
        weaponList.setVisibleRowCount(10);
        itemListModel = new DefaultListModel();
        itemList = new JList(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        itemList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    if (itemList.getSelectedIndex() != -1) {
                        String itemClicked = (String) itemList.getSelectedValue();
                        HashMap<String,Item> detailItems =
                                (HashMap<String, Item>) FeatureManager.getWorldData().getMyItems();
                        for(String s: detailItems.keySet()){
                            if(itemClicked.equals(s)){
                                Item dItem = detailItems.get(s);
                                JPanel detailItemPanel = new JPanel();
                                detailItemPanel.setLayout(new BoxLayout(detailItemPanel,BoxLayout.PAGE_AXIS));
                                JLabel details = new JLabel(dItem.getItemName());
                                detailItemPanel.add(details);
                                if(dItem.getMyItemValues()==null){} else{
                                for(String v: dItem.getMyItemValues().keySet()){
                                    JLabel l = new JLabel(v+": "+String.valueOf(dItem.getMyItemValues().get(v)));
                                    detailItemPanel.add(l);
                                }
                                }
                                JOptionPane.showMessageDialog(null,detailItemPanel,"Details",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }}
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
        });
        itemList.setVisibleRowCount(10);
        JScrollPane weaponScroll = new JScrollPane(weaponList);
        JScrollPane itemScroll = new JScrollPane(itemList);
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel,BoxLayout.PAGE_AXIS));
        JLabel weaponLabel = new JLabel("Weapons");
        JLabel itemLabel = new JLabel("Items");
        weaponPanel.add(weaponLabel);
        weaponPanel.add(weaponScroll);
        weaponPanel.add(itemLabel);
        weaponPanel.add(itemScroll);
        weaponFrame.add(weaponPanel);
        weaponFrame.setVisible(true);
    }

    protected void iterateWeaponsAndItems(){
        weaponMap = FeatureManager.getWorldData().getMyWeapons();
        itemMap = FeatureManager.getWorldData().getMyItems();
        if(weaponMap==null){} else{

            for(String s: weaponMap.keySet()){
                if(weaponListModel.contains(s)){
                } else{
                    weaponListModel.addElement(s);
                }
            }
        }
        if(itemMap==null){} else{

            for(String i: itemMap.keySet()){
                if(itemListModel.contains(i)){
                } else{
                    itemListModel.addElement(i);
                }
            }
        }

    }
}
