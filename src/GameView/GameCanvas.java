package GameView;

import javax.swing.JPanel;

import engine.WalkAroundState;
import engine.world.World;

public class GameCanvas extends JComponent{
	private JPanel myPanel;
	private int myHeight;
	private int myWidth;
	
	public GameCanvas(int width, int height) {
		JPanel panel = new JPanel();
		myPanel = panel;
		myWidth = width;
		myHeight = height;
		
		panel.setSize(width, height);
		panel.setVisible(true);
		panel.setFocusable(true);
		panel.requestFocus();
	}
	
	public void setWorld(World world) {
		myPanel.add(this);
		myPanel.addKeyListener(new WalkAroundState(this, world));
	}
}
