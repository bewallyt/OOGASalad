package authoring;



public class WorldData {
	private TileData[][] myTileDatas;
	private int myWorldWidth;
	private int myWorldHeight;
	
	public WorldData(int width, int height) {
		myWorldWidth = width;
		myWorldHeight = height;
		myTileDatas = new TileData[myWorldWidth][myWorldHeight];
	}
	
	public TileData getTileData(int x, int y) {
		return myTileDatas[x][y];
	}
	
	public int[] getWorldSize(){
		return new int[]{myWorldWidth, myWorldHeight};
	}
	
	public GridObjectData getPlayerData(){
		return null;
	}

}
