package authoring.gameObjects;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import Data.FileLister;
import authoring.SpringUtilities;
import authoring.SpriteImageChooser;
import authoring.features.FeatureManager;
import authoring.view.GridObjectImageEditor;
import authoring.view.TilePanel;








import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("ALL")
/**
 * Abstract class that allows easier creation of the GUI windows typical in an RPG authoring environment
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public abstract class CommonAttributes {
    protected String name;
    protected String image;
    protected String nameTab = "Name/Image";
    protected String attributeTab = "Attributes";
    protected String locationTab = "Location";
    protected String[] attributes = {"Speed","Damage","Defense","Health","Level"};
    protected Map<String,Integer> attributeValues;
    protected Map<String,JTextField> textValues;
    protected Map<String,WeaponData> weaponMap;
    protected Map<String,ItemData> itemMap;
    protected JTextField itemName;
    protected JTextField imageName;
    protected JTextField xcoor;
    protected JTextField ycoor;
    protected JTextField widthField;
    protected JTextField heightField;
    protected JComboBox worldList;
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
    protected SpriteImageChooser sprite=new SpriteImageChooser();
    
    protected JCheckBox one;
    protected JCheckBox two;
    protected JCheckBox three;
    protected ButtonModel movement;
    protected JComboBox<String> playerEnemyImages;

    public CommonAttributes(){
        weaponMap = new HashMap<String, WeaponData>();
        itemMap = new HashMap<String, ItemData>();
        weaponListModel = new DefaultListModel();
        itemListModel = new DefaultListModel();
    }
    /**
     * Gets the sprite image options currently available
     */
    protected JComboBox spriteField(){
    	 playerEnemyImages = new JComboBox<String>(sprite.getSpriteOptions());
         return playerEnemyImages;
    }
    /**
     * Creates a basic attribute field GUI Panel
     */
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
    /**
     * Creates a basic name/image field GUI Panel
     */
    protected JPanel nameImageFields(){
    	/*
        JLabel nameLabel = new JLabel("Name");
        JLabel imageLabel = new JLabel("Image (for non-animated object)");
        itemName = new JTextField("Name",15);
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
        */
    	
    	JPanel one=nameField();
    	JPanel two=imageField();
    	JPanel combined=new JPanel();
    	combined.setLayout(new SpringLayout());
    	combined.add(one);
    	combined.add(two);  	
    	SpringUtilities.makeCompactGrid(combined, 2, 1, 6, 6, 6, 6);
    	return combined;
    	
    }
    protected JPanel nameField(){
    	 JLabel nameLabel = new JLabel("Name");
    	 itemName = new JTextField("Name",15);
         JPanel name= new JPanel(){
             public Dimension getPreferredSize() {
                 Dimension size = super.getPreferredSize();
                 size.width += 200;
                 return size;
             }
         };
         name.setLayout(new SpringLayout());
         name.add(nameLabel);
         nameLabel.setLabelFor(itemName);
         name.add(itemName);
         SpringUtilities.makeCompactGrid(name, 1, 2, 6, 6, 6, 6);
         return name;
    }
    protected JPanel imageField(){
    	 JLabel imageLabel = new JLabel("Image (for non-animated objects)");
    	 JPanel panel=new JPanel();
    	 panel.add(imageLabel);
    	 imageLabel.setLabelFor(imagePanel);
    	 Border defaultBorder = new MatteBorder(1, 1, 1, 1, Color.GRAY);
         imagePanel = new TilePanel(1,1);
         imagePanel.setBorder(defaultBorder);
         imagePanel.setToolTipText("Image here is only used for Random Enemies.");
         panel.add(imagePanel);
         editor=new GridObjectImageEditor(imagePanel);
         return panel;
    }
    /**
     * Creates a basic location JPanel
     */
    protected JPanel locationFields(){
        JPanel locationPanel = new JPanel(new SpringLayout());
        JLabel xcoordinate = new JLabel("Y");
        JLabel ycoordinate = new JLabel("X");
        xcoor = new JTextField("2",5);
        ycoor = new JTextField("2",5);
        locationPanel.add(ycoordinate);
        xcoordinate.setLabelFor(xcoor);
        locationPanel.add(xcoor);
        locationPanel.add(xcoordinate);
        ycoordinate.setLabelFor(ycoor);
        locationPanel.add(ycoor);
        SpringUtilities.makeCompactGrid(locationPanel,2,2,6,6,6,6);
        return locationPanel;
    }

    /**
     * Creates a basic size field Panel
     */
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
    /**
     * Adds currently available weapons and items to their corresponding variables
     */
    public void iterateWeaponsAndItems(){
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
    /**
	 * Parses the integer value from a String
	 * @param s String to be parsed
	 */
	protected int getIntValue(String s){
		return Integer.parseInt(s);
	}
    protected String[] getWorldArray(){
		Map<String, MapData> maps=FeatureManager.getWorldData().getMaps();
		String[] mapArray=new String[maps.keySet().size()];
		int i=0;
		for(String s : maps.keySet()){
			mapArray[i]=s;
			i++;
		}
		return mapArray;
	}

    /**
     * Adds a image to be shown on the map for animated objects
     */
    protected void addImage(int xVal, int yVal, String imageString){
    	FileLister f=new FileLister();
    	List<String> imageList=f.getFileList(util.Constants.PLAYER_IMAGE_FILE+imageString);
    	String first=imageList.get(0);
    	File img=new File(util.Constants.PLAYER_IMAGE_FILE+imageString+"/"+first);
    	BufferedImage bimg=null;
    	try {
			bimg=ImageIO.read(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ImageIcon icon=new ImageIcon(bimg);
    	new GridObjectPainter(xVal, yVal, 1, 1, icon); 	
    }
    
}
