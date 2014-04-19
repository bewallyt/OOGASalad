package authoring;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

 public class PlayerCreation extends Feature implements ActionListener, MouseListener, ItemListener{

    private JFrame frame;
    private Boolean isChecked;
    private int xc;
    private int yc;
    private String charImage;
    private Map<String,Integer> playerValues;
    private JCheckBox isEnemy;
    private String[] labels = {"Damage","Defense","Health","Level","Speed"};
    private String[] imaLabels = {"Ash","Zelda"};
    private String[] weaponNames;
    private String[] itemNames;
    private Map<String,JTextField> textvals;

    public PlayerCreation(){
        JButton createPlayer = new JButton("+ Player/Enemy");
        createPlayer.addActionListener(this);
        createPlayer.setActionCommand("newplayer");
        myComponents.put(createPlayer,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if("newplayer".equals(e.getActionCommand())){
            playerSettings();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED) {
        }

    }

    private void playerSettings() {
        String nameTab = "Player or Enemy";
        String locationTab = "Player Location";
        String attriTab = "Attributes";
        String imaTab = "Images";
        String objTab = "Items/Weapons";

        JTextField xCoor = new JTextField(5);
        JTextField yCoor = new JTextField(5);

        isEnemy = new JCheckBox("Is An Enemy");
        isEnemy.addItemListener(this);
        playerValues = new HashMap<String, Integer>();
        textvals = new HashMap<String, JTextField>();

        JTabbedPane itemPane = new JTabbedPane();
        JPanel panel1 = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                return size;
            }
        };

        panel1.add(isEnemy);

        JPanel panel2 = new JPanel(new SpringLayout());
        JLabel x = new JLabel("X",JLabel.TRAILING);
        JLabel y = new JLabel("Y",JLabel.TRAILING);
        panel2.add(x);
        x.setLabelFor(xCoor);
        panel2.add(xCoor);
        panel2.add(y);
        y.setLabelFor(yCoor);
        panel2.add(yCoor);

        SpringUtilities.makeCompactGrid(panel2,
                2, 2,
                50, 2,
                50, 2);

        JPanel panel3 = new JPanel(new SpringLayout());

        for(int i=0; i<5; i++){
            JLabel l = new JLabel(labels[i],JLabel.TRAILING);
            JTextField n = new JTextField(10);
            panel3.add(l);
            l.setLabelFor(n);
            panel3.add(n);
            textvals.put(labels[i],n);
        }

        SpringUtilities.makeCompactGrid(panel3,
                5, 2,
                50, 2,
                50, 2);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.PAGE_AXIS));
        JComboBox chooseImaSet = new JComboBox(imaLabels);
        panel4.add(chooseImaSet);
        panel4.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));

        //Weapon and item lists

        JPanel panel5 = new JPanel(new FlowLayout());
        DefaultListModel listModelWeapon = new DefaultListModel();
        JList weaponList = new JList(listModelWeapon);
        weaponList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        weaponList.addMouseListener(this);
        weaponList.setVisibleRowCount(5);

        DefaultListModel listModelItems = new DefaultListModel();
        JList itemList = new JList(listModelItems);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addMouseListener(this);
        itemList.setVisibleRowCount(5);

        JScrollPane listScrollPane = new JScrollPane(weaponList);
        JScrollPane listScrollPane2 = new JScrollPane(itemList);
        panel5.add(listScrollPane);
        panel5.add(listScrollPane2);

        //Add tabs

        itemPane.addTab(nameTab, panel1);
        itemPane.addTab(locationTab,panel2);
        itemPane.addTab(attriTab,panel3);
        itemPane.addTab(imaTab,panel4);
        itemPane.addTab(objTab,panel5);

        //Popup when the creation button is pressed

        int result = JOptionPane.showOptionDialog(null, itemPane, "New Player/Enemy", JOptionPane.CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        //Check the pressed button value

        if(result == JOptionPane.OK_OPTION) {

        //Check that text fields and lists are not empty

            int count = 0;
            for(String s: textvals.keySet()){
                if(textvals.get(s).getText().equals("")){
                    count++;
                }
            }

            int weaponcount = weaponList.getModel().getSize();
            int itemcount = itemList.getModel().getSize();

            if (xCoor.getText().equals("") || yCoor.getText().equals("") || count>0 || weaponcount==0 || itemcount==0) {

                //Error message

                JOptionPane.showMessageDialog(frame, "All fields must be completed.", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                playerSettings();
            } else{
                charImage = (String)chooseImaSet.getSelectedItem();
                xc = Integer.parseInt(xCoor.getText());
                yc = Integer.parseInt(yCoor.getText());
                for(int j=0; j<5; j++) {
                    playerValues.put(labels[j],Integer.parseInt(textvals.get(labels[j]).getText()));
                }
                weaponNames = new String[weaponcount];
                    itemNames = new String[itemcount];
                    for(int j=0; j<weaponcount; j++){
                        Object w = weaponList.getModel().getElementAt(j);
                        String wp = (String)w;
                        weaponNames[j] = wp;
                    }
                    for(int k=0; k<itemcount; k++){
                        Object im = itemList.getModel().getElementAt(k);
                        String it = (String)im;
                    itemNames[k] = it;
                }
                makeandsavePlayer();
            }
        } else{}
    }

     private void makeandsavePlayer() {
        PlayerData madePlayer = new PlayerData(charImage,xc,yc,playerValues,weaponNames,itemNames);
        FeatureManager.getWorldData().savePlayer(madePlayer);
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
