package authoring;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class GridObject {

	protected String myImage;
	protected int x;
	protected int y;
	private List<Item> itemList;
	
	public GridObject(int x, int y, String image){
		this.x=x;
		this.y=y;
		myImage=image;
        itemList=new ArrayList<Item>();
	}
	public void init(){
		FeatureManager.getWorldData().getCurrentMap().getTileData(x,y).addGridObjectData(this);
	}

	public String getImageName(){
		return myImage;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
    public List<Item> getItemList(){ return itemList;}
	public void addItem(Item i){
		itemList.add(i);
	}
}
=======
//public class GridObject {
//
//	protected String myImage;
//	protected int x;
//	protected int y;
//	private List<Item> itemList=new ArrayList<Item>();
//	
//	public GridObject(int x, int y, String image){
//		this.x=x;
//		this.y=y;
//		myImage=image;
//	}
//	public void init(){
//		FeatureManager.getWorldData().getCurrentMap().getTileData(x,y).addGridObjectData(this);
//	}
//
//	public String getImageName(){
//		return myImage;
//	}
//	public int getX(){
//		return x;
//	}
//	public int getY(){
//		return y;
//	}
//	public void addItem(Item i){
//		
//	}
//}
>>>>>>> 6eed6209cfa25c7b613c1869163641828fdef7e3
