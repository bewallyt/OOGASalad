package engine.menu;

import Data.DataManager;

public class SaveNode extends MenuNode {
	
	
	
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
	}

	public void save(String saveFile){
		DataManager dm = new DataManager();
		dm.saveWorldDataToFile(saveFile);
	}

	@Override
	void changeWorld() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void changeState() {
		// TODO Auto-generated method stub
		
	}
}
