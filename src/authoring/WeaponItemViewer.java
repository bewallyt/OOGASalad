package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class WeaponItemViewer implements MouseListener {

    private JFrame weaponFrame;
    private DefaultListModel weaponListModel;
    private JList weaponList;
    private DefaultListModel itemListModel;
    private JList itemList;
    private Map<String,Weapon> weaponMap;
    private Map<String,Item> itemMap;

    public WeaponItemViewer(){
        weaponMap = new HashMap<String, Weapon>();
        itemMap = new HashMap<String, Item>();
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
                        for(int i=0; i<weaponList.getModel().getSize(); i++){
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
