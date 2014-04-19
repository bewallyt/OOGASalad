package authoring;

public class GridObject {

	private String myImage;
	private int x;
	private int y;
	
	public GridObject(int x, int y, String image){
		this.x=x;
		this.y=y;
		myImage=image;
	}
	public void init(){
		FeatureManager.getWorldData().getCurrentMap().getTileData(x,y).addGridObjectData(this);
	}

	public String getImage(){
		return myImage;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
