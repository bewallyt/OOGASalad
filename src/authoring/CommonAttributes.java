package authoring;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prit on 4/19/14.
 */
public class CommonAttributes {
    protected Map<String,JTextField> nameImageValues;
    protected Map<String,JTextField> textValues;
    protected String[] attributes = {"Speed","Damage","Defense","Health","Level"};

    public CommonAttributes(){}

    public JPanel attributeFields(){
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
}
