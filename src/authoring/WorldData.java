package authoring;

public class WorldData {
	private TileData[][] myTileDatas;
	private int myWorldWidth;
	private int myWorldHeight;
	private PlayerData myPlayerData;
	
	public WorldData(int width, int height) {
		myWorldWidth = width;
		myWorldHeight = height;
		myPlayerData = new PlayerData();
		myTileDatas = new TileData[myWorldWidth][myWorldHeight];
	}
	
	public TileData getTileData(int x, int y) {
		return myTileDatas[x][y];
	}
	
	public int[] getWorldSize(){
		return new int[]{myWorldWidth, myWorldHeight};
	}
	
	public PlayerData getPlayerData(){		
		String[] animImages = new String[12];
		animImages[0] = "fs.png";
		animImages[3] = "ls.png";
		animImages[6] = "bs.png";
		animImages[9] = "rs.png";
		
		myPlayerData.setMyImage("blank");
		myPlayerData.setMyAnimImages(animImages);
		System.out.println(myPlayerData.getMyImage());
		return myPlayerData;
	}

}
