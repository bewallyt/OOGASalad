package GameView;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import engine.world.Canvas;

public class View extends JComponent{
	private Canvas myCanvas;
	
	public View(int width, int height) {
		myCanvas = new Canvas(width, height);
		
		initUI();
	}
	
	private void initUI() {
		setLayout(new BorderLayout());
		this.add(makeDisplay(), BorderLayout.CENTER);
		update();
	}
	
	private JComponent makeDisplay() {
		JPanel canvasPanel = new JPanel();
		canvasPanel.add(myCanvas);
		canvasPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		return canvasPanel;
	}
	
	public void update() {
		myCanvas.update();
		repaint();
	}
	
	
}
