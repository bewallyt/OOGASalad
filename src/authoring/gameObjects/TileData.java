package authoring.gameObjects;

import java.util.*;

import util.Constants;

/**
 * Returns the information relevant to a given tile in the authoring environment
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class TileData {
	private String myImageName;
	private List<GridObjectData> myGridObjectDatas;
	
	public TileData(String s) {
		if (prependTileImagePath(s)) {
			myImageName = Constants.TILEIMAGEPATH+s;
		} else {
			myImageName = s;
		}
		myGridObjectDatas = new ArrayList<GridObjectData>();
	}
	/**
	 * Sets the image name of the image on the current tile
	 * @param s Name to be used
	 */
	public void setImageName(String s){
		if (prependTileImagePath(s)) {
			myImageName = Constants.TILEIMAGEPATH+s;
		} else {
			myImageName = s;
		}
	}
	/**
	 * Gets the name of the current image
	 */
	public String getImageName(){
		return myImageName;
	}
	/**
	 * Returns a list of GridObjects currently on the tile
	 */
	public List<GridObjectData> getGridObjectDatas() {
		return myGridObjectDatas;
	}
	/**
	 * Adds a new GridObject to the tile
	 * @param gd GridObject to be added
	 */
	protected void addGridObjectData(GridObjectData gd){
		myGridObjectDatas.add(gd);
		System.out.println(myGridObjectDatas);
	}

	private boolean prependTileImagePath(String s) {
		boolean prepend = true;
		if (s != null) {
			if (s.startsWith(Constants.TILEIMAGEPATH)) {
				prepend = false;
			} 
		} 
		return prepend;
	}
}
