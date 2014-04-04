package Data;

import static org.junit.Assert.*;

import org.junit.Test;

import authoring.WorldData;

public class DataTester {

	@Test
	public void basicStoreLoadTest() throws Exception{
		WorldData d=new WorldData();
		FileStorer f=new FileStorer();
		f.storeWorldData("TestWorld2.txt", d);
		WorldData d2=f.loadFile("TestWorld2.txt");
	}

}
