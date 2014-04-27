package authoring.gameObjects;

/**
 * @ Pritam M.
 * */

import javax.swing.*;

import authoring.features.FeatureManager;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

@SuppressWarnings("ALL")
public class WeaponItemViewer extends CommonAttributes{

    private JFrame weaponFrame;
    private JFrame detailFrame;
    private JList weaponList;
    private JList itemList;

    public WeaponItemViewer(){

        detailFrame = new JFrame();
        weaponFrame = new JFrame("Existing Weapons/Items");
        weaponFrame.setLayout(new BorderLayout());
        weaponFrame.setBounds(1050, 600, 300, 300);

        weaponList = new JList(weaponListModel);
        weaponList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        weaponList.setDragEnabled(true);
        weaponList.setTransferHandler(new TransferHandler(){
            public int getSourceActions(JComponent c){
                return COPY;
            }

            protected Transferable createTransferable(JComponent c){
                JList list = (JList)c;
                String chosenWeapon = (String)list.getSelectedValue();
                return new StringSelection(chosenWeapon);
            }
        });
        weaponList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    if (weaponList.getSelectedIndex() != -1) {
                        String clicked = (String)weaponList.getSelectedValue();
                        HashMap<String,Weapon> detailsWeaponMap =
                                (HashMap<String, Weapon>) FeatureManager.getWorldData().getMyWeapons();
                        for(String s: detailsWeaponMap.keySet())
                            if (clicked.equals(s)) {
                                Weapon editWeapon = detailsWeaponMap.get(s);
                                JPanel detailsPanel = new JPanel();
                                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
                                ArrayList<Attacks> detailAttacks = (ArrayList<Attacks>) editWeapon.getMyAttacks();
                                JLabel dWeapon = new JLabel(editWeapon.getMyName() + " -" + " Speed:" +
                                        String.valueOf(editWeapon.getMySpeed()) + " -" + " Damage:" +
                                        String.valueOf(editWeapon.getMyDamage()));
                                detailsPanel.add(dWeapon);
                                JLabel newLine = new JLabel("<html><br></html>");
                                JLabel attack = new JLabel("Attacks:");
                                detailsPanel.add(newLine);
                                detailsPanel.add(attack);
                                for (Attacks detailAttack : detailAttacks) {
                                    JLabel dAttack = new JLabel(detailAttack.getMyName() + " -" + " Speed:" +
                                            String.valueOf(detailAttack.getMySpeed()) + " -" + " Damage:" +
                                            String.valueOf(detailAttack.getMyDamage()));
                                    JLabel subAttack = new JLabel(detailAttack.getAffectAttribute() + ":" +
                                            detailAttack.getAffectValue() + " -" + " " +
                                            String.valueOf(detailAttack.getAffectWho()));
                                    detailsPanel.add(dAttack);
                                    detailsPanel.add(subAttack);
                                }
                                JOptionPane.showMessageDialog(detailFrame, detailsPanel, "Details",
                                        JOptionPane.INFORMATION_MESSAGE);
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
//        weaponFrame.setLocationRelativeTo();
        weaponFrame.setVisible(true);
    }
    
    public JFrame getWeaponFrame(){
    	return weaponFrame;
    }

}
