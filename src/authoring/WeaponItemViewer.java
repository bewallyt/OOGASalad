package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class WeaponItemViewer implements MouseListener {

    private JFrame weaponFrame;
    private DefaultListModel weaponListModel;
    private JList weaponList;
    private DefaultListModel itemListModel;
    private JList itemList;

    public WeaponItemViewer(){
        weaponFrame = new JFrame("Existing Weapons/Items");
        weaponFrame.setLayout(new BorderLayout());
        weaponFrame.setBounds(100, 0, 300, 300);
        weaponListModel = new DefaultListModel();
        weaponList = new JList(weaponListModel);
        weaponList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        weaponList.addMouseListener(this);
        weaponList.setVisibleRowCount(10);
        itemListModel = new DefaultListModel();
        itemList = new JList(itemListModel);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        itemList.addMouseListener(this);
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
        iterateWeaponsAndItems();
    }

    protected void iterateWeaponsAndItems(){
        HashMap<String,Weapon> weaponMap = (HashMap<String, Weapon>) FeatureManager.getWorldData().getMyWeapons();
        HashMap<String,Item> itemMap = (HashMap<String, Item>) FeatureManager.getWorldData().getMyItems();

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
