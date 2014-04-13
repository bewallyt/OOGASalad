package authoring;

import java.awt.*;
import javax.swing.*;

public class TileImageButton extends JPanel {
	
	public TileImageButton(String s){
		
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(36, 36);
	}
	
	@Override
	public Dimension getMaximumSize(){
		return new Dimension(36, 36);
	}
}
