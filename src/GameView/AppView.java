package GameView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import engine.world.Canvas;

public class AppView extends JFrame{
	private Canvas myCanvas;
	private GameFrame myGame;
	
	public AppView(String title, GameFrame game, int width, int height) {
		myGame = game;
		setTitle(title);
		
		setJMenuBar(makeMenuBar());
		
		this.getContentPane().add(myCanvas = game.getMyCanvas());
		
		pack();
		setSize(width, height);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		requestFocus();
	}
	
	private JMenuBar makeMenuBar() {
		JMenuBar menu = new JMenuBar();
		menu.add(makeFileMenu());
		return menu;
	}
	
	@SuppressWarnings("serial")
	private JMenu makeFileMenu() {
		JMenu file = new JMenu("File");
		file.add(new AbstractAction("Load") {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		file.add(new AbstractAction("Save") {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		file.add(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		return file;
	}
	
	public void doGame() throws InterruptedException {
		myGame.doGameLoop();
	}
	
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		AppView view = new AppView("yay", game, 400, 400);
		
		try {
			view.doGame();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
