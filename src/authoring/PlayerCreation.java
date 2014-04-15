package authoring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerCreation extends Feature implements ActionListener{

    private JFrame frame;
    private Boolean isChecked;
    private String pName;
    private int xc;
    private int yc;

    public PlayerCreation(){
        JButton createPlayer = new JButton("New Player");
        createPlayer.addActionListener(this);
        createPlayer.setActionCommand("newplayer");
        myComponents.put(createPlayer,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if("newplayer".equals(e.getActionCommand())){
            playerSettings();
        }
    }

    private void playerSettings() {
        String nameTab = "Player Animation";
        String locationTab = "Player Location";
        String attriTab = "Player Images";
        JTextField xCoor = new JTextField(5);
        JTextField yCoor = new JTextField(5);
        JCheckBox isAnimated = new JCheckBox("Is Animated?");

        JTabbedPane itemPane = new JTabbedPane();
        JPanel panel1 = new JPanel(){
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width += 200;
                return size;
            }
        };
        panel1.add(isAnimated);
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
                6, 6,
                6, 6);

        JPanel panel3 = new JPanel(new SpringLayout());
        String[] labels = {"Non-animated image name:","Left animation image name:","Top animation image name:",
                "Right animation image name:","Bottom animation image name:"};
        for(int i=0; i<labels.length; i++){
            JLabel l = new JLabel(labels[i],JLabel.TRAILING);
            JTextField n = new JTextField(10);
            panel3.add(l);
            l.setLabelFor(n);
            panel3.add(n);
        }

        SpringUtilities.makeCompactGrid(panel3,
                5, 2,
                6, 6,
                6, 6);

        itemPane.addTab(nameTab,panel1);
        itemPane.addTab(locationTab,panel2);
        itemPane.addTab(attriTab,panel3);

        int result = JOptionPane.showOptionDialog(null, itemPane, "New Player", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION) {
            if (xCoor.getText().equals("") || yCoor.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "All fields must be completed.", "Error Message", JOptionPane.ERROR_MESSAGE);
                playerSettings();
            } else{
                isChecked = isAnimated.isSelected();
                xc = Integer.parseInt(xCoor.getText());
                yc = Integer.parseInt(yCoor.getText());
                makeandsavePlayer();
            }
        } else{}
    }

    private void makeandsavePlayer() {
        //PlayerData madePlayer = new PlayerData();
    }
}
