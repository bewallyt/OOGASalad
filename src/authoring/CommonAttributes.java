package authoring;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CommonAttributes {
    protected String name;
    protected String image;
    protected Map<String,Integer> attributeValues;
    protected Map<String,JTextField> textValues;
    protected String[] attributes = {"Speed","Damage","Defense","Health","Level"};
    protected JTextField itemName;
    protected JTextField imageName;
    protected String nameTab = "Name/Image";
    protected String attributeTab = "Attributes";
    protected GridObjectImageEditor editor;
    protected TilePanel imagePanel;
    protected JPanel namePanel;
    protected Map<String,Weapon> weaponMap;
    protected Map<String,Item> itemMap;
    protected DefaultListModel weaponListModel;
    protected DefaultListModel itemListModel;


    public CommonAttributes(){
        weaponMap = new HashMap<String, Weapon>();
        itemMap = new HashMap<String, Item>();
        weaponListModel = new DefaultListModel();
        itemListModel = new DefaultListModel();
    }

    protected JPanel attributeFields(){
        attributeValues = new HashMap<String, Integer>();
        textValues = new HashMap<String, JTextField>();
        JPanel attributePanel = new JPanel(new SpringLayout());
        for(int i=0; i<attributes.length; i++){
            JLabel l = new JLabel(attributes[i],JLabel.TRAILING);
            JTextField n = new JTextField("5",10);
            attributePanel.add(l);
            l.setLabelFor(n);
            attributePanel.add(n);
            textValues.put(attributes[i],n);
        }

        SpringUtilities.makeCompactGrid(attributePanel,
                5, 2,
                6, 6,
                6, 6);

        return attributePanel;
    }

    protected JPanel nameImageFields(){
        JLabel nameLabel = new JLabel("Name");
        JLabel imageLabel = new JLabel("Image");
        itemName = new JTextField("newItem",15);
        namePanel = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                return size;
            }
        };


        namePanel.setLayout(new SpringLayout());
        namePanel.add(nameLabel);
        nameLabel.setLabelFor(itemName);
        namePanel.add(itemName);
        namePanel.add(imageLabel);

        imageLabel.setLabelFor(imagePanel);

        Border defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
        imagePanel = new TilePanel(1,1);
        imagePanel.setBorder(defaultBorder);
        imagePanel.setToolTipText("Image here is only used for Random Enemies.");
        namePanel.add(imagePanel);
		editor=new GridObjectImageEditor(imagePanel);
        SpringUtilities.makeCompactGrid(namePanel,2,2,6,6,6,6);
        return namePanel;
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
