package Data;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import authoring.WorldData;

public class DataTester {


	@Test
	public void basicStoreLoadTest() throws Exception{
		WorldData d=new WorldData();
		FileStorer f=new FileStorer();
		f.storeWorldData("TestWorld2.txt", d);
		WorldData d2=f.getWorldData("TestWorld2.txt");
	}
	@Test
	public void fileListTest() throws Exception{
		FileStorer f=new FileStorer();
		List<String> list=f.getSavedGameList();
		if (!list.isEmpty()) {
			assertTrue(list.contains("default"));
			assertTrue(list.contains("WorldData1.json"));
		}

	}
	@Test
	public void imageListTest() throws Exception{
		ImageManager m=new ImageManager();
	//	List<String> list=m.getSavedImageList();
		assertTrue(list.contains("TestImage.JPG"));
		assertTrue(list.contains("TestImage2.jpg"));
		assertFalse(list.contains("bloobloo"));
	}
	
	@Test
	public void dataManagerTest1() throws Exception{
		DataManager dm = new DataManager();
		WorldData d = new WorldData();
		dm.setWorldData("WorldData1.json", d);
		WorldData d2 = dm.getWorldData("WorldData1.json");
		assertTrue(d2 != null);
		dm.saveWorldDataToFile("WorldData1.json");
		WorldData d3 = dm.loadWorldDataFromFile("WorldData1.json");
		assertTrue(d3 != null);
	}
	
	
}
