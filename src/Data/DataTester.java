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
		assertTrue(list.contains("test.txt"));
		assertTrue(list.contains("TestWorld.txt"));
	}
}
