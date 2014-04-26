package engine.dialogue;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import engine.gridobject.GridObject;
import engine.images.ScaledImage;

public abstract class AbstractManager implements InteractionBox{
	private static final int X_OFFSET = 5/10;
	private static final int Y_OFFSET = 3/10;
	private static final int SYMBOL_RADIUS = 10;
	private static final int CHARACTERS_PER_LINE = 25;
	private static final int OPTION_CHARS_PER_LINE = 10;
	private InteractionMatrix2x2 myMatrix;

	public AbstractManager(){
		myMatrix = new InteractionMatrix2x2();
	}
	@Override
	public void paintDisplay(Graphics2D g2d, int xSize, int ySize, int width, int height) {
		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font=null;

		try {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
			Font sizedFont = font.deriveFont(16f);
			g2d.setFont(sizedFont);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g2d.setColor(Color.white);
		Image img = new ScaledImage(width, 150,Constants.IMAGEPATH+"textbox.png").scaleImage();
		g2d.drawImage(img, 0, height + 70, null);
		//g2d.fill(new Rectangle((int) ((int) 0), ySize/2+60, width , height));
		g2d.setColor(Color.black);

		if (isResponding()) 
			printResponses(g2d, myMatrix, xSize, ySize, width, height);
		else {
			printStringToScreen(g2d, getTextToBeDisplayed(), (int) xSize, ySize);
			//g2d.drawString(getTextToBeDisplayed(), (int) xSize/10, ySize/2+120);		
		}	
	}
	
	private void printStringToScreen(Graphics2D g2d, String textToBeDisplayed,
			int xPos, int yPos) {
		int lineCount = 0;
		int start = 0;
		List<String> myLines = createTextLines(textToBeDisplayed, CHARACTERS_PER_LINE);
		
		for (int i = 0; i < myLines.size(); i++) {
			g2d.drawString(myLines.get(i), (int) xPos/10, yPos/2+120 + (lineCount*g2d.getFont().getSize()));	
			lineCount++;
		}
	}
	
	protected void printResponses(Graphics2D g2d, InteractionMatrix matrix, int xSize, int ySize, 
			int width, int height) {
		int xCornerLoc = xSize/10;
		int yCornerLoc = ySize/2 + 120;
		for (int i = 0; i < matrix.getDimension()[0]; i++) {
			for (int j = 0; j < matrix.getDimension()[1]; j++) {
				MatrixNode qn = (MatrixNode) matrix.getNode(j, i);
				if(qn!=null && qn.toString()!=null) {
					printOption(g2d, qn.toString(), xSize, ySize, width, height, i, j);
//					g2d.drawString(qn.toString(), (int) (xCornerLoc + j*(xSize*5/10)), 
//													(int)(yCornerLoc + i*(height*3/10)));
				}
				else {
					g2d.drawString("     -", (int) (xCornerLoc + j*(xSize*5/10)), (int)(yCornerLoc + i*(height*3/10)));
				}
			}
		}

		int[] selectedOptionLoc = matrix.getSelectedNodeLocation();
		g2d.fillOval((int) (xCornerLoc-10 + selectedOptionLoc[0]*(xSize-25)*5/10) - SYMBOL_RADIUS, 
				(int) (yCornerLoc + selectedOptionLoc[1]*(height-15)*3/10) - SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}
	
	
	private void printOption(Graphics2D g2d, String textToBeDisplayed, int xSize, int ySize, int width, 
							int height, int xLoc, int yLoc) {
		int lineCount = 0;
		int xCornerLoc = xSize/10;
		int yCornerLoc = ySize/2 + 120;
		List<String> myLines = createTextLines(textToBeDisplayed, OPTION_CHARS_PER_LINE);
		
		for (int i = 0; i < myLines.size(); i++) {
			g2d.drawString(myLines.get(i), (int) (xCornerLoc + yLoc*(xSize*5/10)), 
					(int)(yCornerLoc + xLoc*(height*3/10) + (lineCount*g2d.getFont().getSize())));
			lineCount++;
		}
		
	}
	
	private List<String> createTextLines(String textToBeDisplayed, int charactersPerLine) {
		
		int start = 0;
		List<String> myLines = new ArrayList<String>();
		
		while(textToBeDisplayed.length() > charactersPerLine) {
			myLines.add(textToBeDisplayed.substring(start, start+charactersPerLine));
			textToBeDisplayed = textToBeDisplayed.substring(start + charactersPerLine);
		}
		
		myLines.add(textToBeDisplayed);
		return myLines;
	}

	public void moveUp() {
		myMatrix.moveUp();
		setCurrentNode();
	}

	public void moveDown() {
		myMatrix.moveDown();
		setCurrentNode();
	}

	public void moveLeft() {
		myMatrix.moveLeft(); 
		setCurrentNode();
	}

	public void moveRight() {
		myMatrix.moveRight();
		setCurrentNode();
	}
	public InteractionMatrix getMatrix(){
		return myMatrix;
	}
	
	public abstract void setCurrentNode();
	public abstract boolean isResponding();
	public abstract String getTextToBeDisplayed();
}
