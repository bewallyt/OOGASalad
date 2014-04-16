package GameView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test extends JPanel {

    private static JComboBox comboBox;
    private static JTextField textField;

    // Create a form with the fields
    public Test() {
        super(new BorderLayout());
        // Panel for the labels
        JPanel labelPanel = new JPanel(new GridLayout(2, 1)); // 2 rows 1 column
        add(labelPanel, BorderLayout.WEST);

        // Panel for the fields
        JPanel fieldPanel = new JPanel(new GridLayout(2, 1)); // 2 rows 1 column
        add(fieldPanel, BorderLayout.CENTER);

        // Combobox
        JLabel labelCombo = new JLabel("Bank Code");

        // Options in the combobox
        String[] options = { "Option1", "Option2", "Option3", "Option4", "Option15" };
        comboBox = new JComboBox(options);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Do something when you select a value

            }
        });

        // Textfield
        JLabel labelTextField = new JLabel("Bank account number");
        textField = new JTextField();

        // Add labels
        labelPanel.add(labelCombo);
        labelPanel.add(labelTextField);

        // Add fields
        fieldPanel.add(comboBox);
        fieldPanel.add(textField);
    }

    public static void main(String[] args) {
        final Test form = new Test();

        // Button submit
        JButton submit = new JButton("Submit Form");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createIban((String) comboBox.getSelectedItem(), textField.getText());
            }
        });

        // Frame for our test
        JFrame f = new JFrame("Text Form Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);

        // Panel with the button
        JPanel p = new JPanel();
        p.add(submit);
        f.getContentPane().add(p, BorderLayout.SOUTH);

        // Show the frame
        f.pack();
        f.setVisible(true);
    }

    private static void createIban(String selectedItem, String text) {
        // Do stuff with your data
        System.out.println("Im in createIban with the values: " + selectedItem + " and " + text);
    }
}