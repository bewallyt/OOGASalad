package engine.dialogue;

import java.awt.Graphics2D;

public class DialogueAnsweringState implements DialogueState{
	private static final int SYMBOL_RADIUS = 10;
	
	private void printResponses(Graphics2D g2d, InteractionMatrix myResponses, int xSize, int ySize, int height) {
		int xCornerLoc = xSize/10;
		int yCornerLoc = ySize/2 + 120;

		for (int i = 0; i < myResponses.getDimension()[0]; i++) {
			for (int j = 0; j < myResponses.getDimension()[1]; j++) {
				UserQueryNode qn = (UserQueryNode) myResponses.getNode(j, i);
				if (qn != null && qn.toString()!=null) {
					g2d.drawString(qn.toString(), (int) (xCornerLoc + j*(xSize*5/10)), (int)(yCornerLoc + i*(height*3/10)));
				} else {
					g2d.drawString("     -", (int) (xCornerLoc + j*(xSize*5/10)), (int)(yCornerLoc + i*(height*3/10)));
				}
			}
		}

		int[] selectedOptionLoc = myResponses.getSelectedNodeLocation();
		g2d.fillOval((int) (xCornerLoc-10 + selectedOptionLoc[0]*(xSize-25)*5/10) - SYMBOL_RADIUS, 
				(int) (yCornerLoc + selectedOptionLoc[1]*(height-15)*3/10) - SYMBOL_RADIUS, SYMBOL_RADIUS, SYMBOL_RADIUS);
	}

}
