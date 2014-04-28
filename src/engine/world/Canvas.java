
package engine.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import util.Constants;
//import engine.AbstractGameState;
import engine.Control;
import engine.gridobject.GridObject;
import engine.images.ScaledImage;

public class Canvas extends JComponent{

	private static final int IMAGE_OFFSET = 10;
	private static final int FILLREC_OFFSET = 58;
	private static final double REC_OFFSET = 1.4;
	private static final double BOTTOM_OFFSET = .45;
	private JFrame myFrame;
	private int myHeight;
	private int myWidth;
	private World myWorld;
	private int myWorldHeight;
	private int myWorldWidth;
	private int offsetMinX = 0;
	private int offsetMinY=0;
	private KeyListener myControl;
	public final static int CANVAS_SIZE = 500;

	/**
	 * Instantiates a new canvas.
	 *
	 * @param numTileWidth the num tile width
	 * @param numTileHeight the num tile height
	 * @param tileSize the tile size
	 */
	public Canvas(int width, int height){
		JFrame frame = new JFrame("Pokemon");
		myFrame = frame;
		myHeight=height;
		myWidth=width;
		frame.setSize((int) width, (int) height);
		myFrame.add(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.setBounds(100, 100,width, height-1);
		myControl = null;
	}

	public void setWorld(World world){
		myFrame.add(this);
		
		if (myFrame.getKeyListeners().length != 0) {
			myFrame.removeKeyListener(myControl);
		}
		myControl = new Control(this, world);
		myFrame.addKeyListener(myControl);
		myWorld = world;
		myWorldHeight = myWorld.getPlayHeight();
		myWorldWidth = myWorld.getPlayWidth();
	}

	//	public void setState(Control state){
	//		myFrame.addKeyListener(state);
	//	}

	public int getHeight(){
		return myHeight;
	}

	public int getWidth(){
		return myWidth;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		setOpaque(false);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);



		if(myWorld instanceof WalkAroundWorld) paintWalkAroundWorld(g2d);
		else if (myWorld instanceof ArenaWorld){
			paintArenaWorld(g2d);
		}
		else if (myWorld instanceof TitleWorld){
			paintTitleWorld(g2d);
		}

	}

	private void paintArenaWorld(Graphics2D g2d) {
		ArenaWorld world = (ArenaWorld) myWorld;
		g2d.drawImage(world.getBackground().scaleImage(),0,0, null);
		g2d.drawImage(world.getPlayerImage(), myWidth/IMAGE_OFFSET, (int) (myHeight*BOTTOM_OFFSET), null);
		g2d.drawImage(world.getEnemyImage(), (int) (myWidth/1.7), myHeight/7, null);
		
		drawStatusBars(g2d, world);
		world.getTextDisplayer().paintDisplayer(g2d, myWidth, myHeight, 0,0);

	}
	
	private void paintTitleWorld(Graphics2D g2d){
		TitleWorld world = (TitleWorld) myWorld;
		g2d.drawImage(world.getBackground(), 0, 0, null);
		world.getTextDisplayer().paintDisplayer(g2d, myWidth, myHeight, 0,0);
	}

	private void drawStatusBars(Graphics2D g2d, ArenaWorld world) {
		Image img = new ScaledImage((int) (myWidth/2.5), myHeight/IMAGE_OFFSET,Constants.IMAGEPATH+"status bar.png").scaleImage();
		drawTopImage(g2d, world, img);
		
		drawBottomImage(g2d, world, img);
	}

	private void drawBottomImage(Graphics2D g2d, ArenaWorld world, Image img) {
		g2d.drawImage(img, (int) (myWidth/15), (int) (myHeight/8), null);
		g2d.setColor(Color.green);
		g2d.draw(new Rectangle((int) (myWidth/4)-7,myHeight/6+3, myWidth/5, IMAGE_OFFSET));
		g2d.fill(new Rectangle((int) (myWidth/4)-7,myHeight/6+3, (int) (myWidth/5*((float) world.getEnemy()
					.getStatsMap().get(Constants.HEALTH).getValue()/world.getEnemy().getStatsMap().get(Constants.HEALTH)
					.getMaxValue())), IMAGE_OFFSET));
		g2d.setColor(Color.black);
		g2d.drawString(world.getEnemy().toString() +"  lv " + world.getEnemy().getStatsMap().get(Constants.LEVEL).getValue(), myWidth/15+IMAGE_OFFSET, myHeight/8+20);
	}

	private void drawTopImage(Graphics2D g2d, ArenaWorld world, Image img) {
		g2d.drawImage(img, (int) (myWidth/1.5-60), (int) (myHeight*BOTTOM_OFFSET+35), null);
		g2d.setColor(Color.green);
		g2d.draw(new Rectangle((int) (myWidth/REC_OFFSET),(int) (myHeight*BOTTOM_OFFSET+FILLREC_OFFSET), myWidth/5, IMAGE_OFFSET));
		g2d.fill(new Rectangle((int) (myWidth/REC_OFFSET),(int) (myHeight*BOTTOM_OFFSET+FILLREC_OFFSET), (int) (myWidth/5*((float)world.getPlayer()
					.getStatsMap().get(Constants.HEALTH).getValue()/world.getPlayer().getStatsMap().get("Health").
					getMaxValue())), IMAGE_OFFSET));
		g2d.setColor(Color.black);

		g2d.setFont(new Font(getFont().getFontName(),Font.BOLD,20));
		g2d.drawString(world.getPlayer().toString()+"  lv " + 
		world.getPlayer().getStatsMap().get(Constants.LEVEL).getValue(), (int)(myWidth/1.75), (int) (myHeight*BOTTOM_OFFSET+53));
	}
	
	

	private void paintWalkAroundWorld(Graphics2D g2d) {
	
		WalkAroundWorld world = (WalkAroundWorld) myWorld;
		for (int i = 0; i < world.getTileGridWidth(); i++) {
			for (int j = 0; j < world.getTileGridHeight(); j++) {
				if (myWorld.getPlayer()!=null && tileIsInView(world.getTileMatrix()[i][j], getCameraOffset()[0], getCameraOffset()[1]))
					world.getTileMatrix()[i][j].paint(g2d, getCameraOffset()[0], getCameraOffset()[1]);
			}
		}

		for(int i=0; i<world.getGridObjectList().size(); i++) {
			if(isInView(world.getGridObjectList().get(i),getCameraOffset()[0],getCameraOffset()[1])){
				world.getGridObjectList().get(i).paint(g2d,getCameraOffset()[0], getCameraOffset()[1]);
				world.getGridObjectList().get(i).paintDialogue(g2d, myWidth, myHeight, getCameraOffset()[0], getCameraOffset()[1]);
			}
		}
		
		world.getTextDisplayer().paintDisplayer(g2d, myWidth, myHeight, getCameraOffset()[0], 
												getCameraOffset()[1]);
		
//		world.getMenuDisplayer().paintDisplayer(g2d, myWidth, myHeight, getCameraOffset()[0], 
//				getCameraOffset()[1]);
	}

	public int[] getCameraOffset(){
		int offsetMaxX = myWorldWidth-myWidth;
		int offsetMaxY = myWorldHeight-myHeight;
		//		System.out.println(myWorld.getPlayer().getX());
		int cameraX = myWorld.getPlayer().getX() - myWidth /2;
		int cameraY = myWorld.getPlayer().getY() - myHeight /2;
		if (cameraX > offsetMaxX)
			cameraX = offsetMaxX;
		else if (cameraX < offsetMinX)
			cameraX = offsetMinX;
		if (cameraY>offsetMaxY)
			cameraY = offsetMaxY;
		else if(cameraY<offsetMinY)
			cameraY=offsetMinY;
		return new int[] {cameraX, cameraY};
	}

	public boolean isInView(GridObject go, int cameraX, int cameraY){
		return (go.getBounds().getMaxX()>cameraX && go.getBounds().getMaxY()>cameraY
				&& go.getBounds().getMinX()<(cameraX+myWidth) && go.getBounds().getMinY()<(cameraY + myHeight));
	}

	public boolean tileIsInView(Tile go, int cameraX, int cameraY){
		return (go.getBounds().getMaxX()>cameraX && go.getBounds().getMaxY()>cameraY
				&& go.getBounds().getMinX()<(cameraX+myWidth) && go.getBounds().getMinY()<(cameraY + myHeight));
	}
}
