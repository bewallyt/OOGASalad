package authoring;

/**
 * @ Pritam M.
 * @ Davis Treybig
 * */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public abstract class CommonAttributes {
    protected String name;
    protected String image;
    protected String nameTab = "Name/Image";
    protected String attributeTab = "Attributes";
    protected String locationTab = "Location";
    protected String[] attributes = {"Speed","Damage","Defense","Health","Level"};
    protected Map<String,Integer> attributeValues;
    protected Map<String,JTextField> textValues;
    protected Map<String,Weapon> weaponMap;
    protected Map<String,Item> itemMap;
    protected JTextField itemName;
    protected JTextField imageName;
    protected JTextField xcoor;
    protected JTextField ycoor;
    protected JTextField widthField;
    protected JTextField heightField;
    protected GridObjectImageEditor editor;
    protected TilePanel imagePanel;
    protected JPanel namePanel;
    protected JFrame frame;
    protected JList weaponList;
    protected JList itemList;
    protected DefaultListModel weaponListModel;
    protected DefaultListModel itemListModel;
    protected int x;
    protected int y;

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

    protected JPanel locationFields(){
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
        return locationPanel;
    }

    protected JPanel sizeFields(){
        JPanel sizePanel = new JPanel(new SpringLayout());
        JLabel widthLabel=new JLabel("Width");
        JLabel heightLabel=new JLabel("Height");
        widthField=new JTextField("1", 15);
        heightField=new JTextField("1", 15);
        sizePanel.add(widthLabel);
        widthLabel.setLabelFor(widthField);
        sizePanel.add(widthField);
        sizePanel.add(heightLabel);
        heightLabel.setLabelFor(heightField);
        sizePanel.add(heightField);
        SpringUtilities.makeCompactGrid(sizePanel,2,2,5,5,5,5);
        return sizePanel;
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
