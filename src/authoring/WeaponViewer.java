package authoring;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Prit on 4/19/14.
 */
public class WeaponViewer extends Feature {

    private JFrame weaponFrame;

    public WeaponViewer(){
        weaponFrame = new JFrame();
        JPanel panel = new JPanel();
        JTextField newfield = new JTextField(10);
        panel.add(newfield);
        weaponFrame.add(panel);
        weaponFrame.setVisible(true);
    }
}
