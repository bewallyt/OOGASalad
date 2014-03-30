package engine.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Main extends JPanel{
	public Main(){
		
	}
	
	public void addKeyListener(KeyListener l){
		KeyListener k = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
	}
	
	public void addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			
			player.keyPressed(e);
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
			player.keyReleased(e);
			
		}
	});
}
