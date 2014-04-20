package authoring;

import javax.swing.*;
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

    public CommonAttributes(){}

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
        imageName = new JTextField("defaultIW",15);
        JPanel namePanel = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                return size;
            }
        };

        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.PAGE_AXIS));
        namePanel.add(nameLabel);
        namePanel.add(itemName);
        namePanel.add(imageLabel);
        namePanel.add(imageName);
        return namePanel;
    }
}
