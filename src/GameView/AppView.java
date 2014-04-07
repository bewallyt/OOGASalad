package GameView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class AppView extends JFrame{
	public AppView(String title, GameFrame game) {
		setTitle(title);
		
		setJMenuBar(makeMenuBar());
		
//		this.getContentPane().add(game);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		AppView view = new AppView("yay", game);
	}
}
