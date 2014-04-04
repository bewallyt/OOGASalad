import java.util.List;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Temporary extends Feature{
	
	//The size of the grid
	private final static int NUM_ROWS = 10;
	private final static int NUM_COLS = 10;
	private Object[][] occupantMatrix;
	private JFrame frame;

	public Temporary()  {
		occupantMatrix = new Object[NUM_ROWS][NUM_COLS];
		prepareGUI();
	}

	private void prepareGUI(){
		frame = new JFrame("The World");
		frame.setSize(1200,750);
		frame.setLayout(new BorderLayout());
		frame.add(new Grid());
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		//myComponents.put(new TestPane(), "xxx");
		//frame.pack();
		frame.setVisible(true);  
	}
	
	public static void main(String[] args){
		Temporary a = new Temporary();
	}

}


