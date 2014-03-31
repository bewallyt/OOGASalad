package engine.collision;

import java.util.List;

import engine.gridobject.GridObject;
import engine.gridobject.item.Weapon;

public abstract class CollisionHandler {
	
	List<GridObject> gridObjectList;
	protected boolean harm=false;
	
	public CollisionHandler() {
	}
	
	public CollisionHandler[][] makeCollisionMatrix(){
		CollisionHandler[][] collisionMatrix = new CollisionHandler[gridObjectList.size()][gridObjectList.size()];
		for (int i=0; i<gridObjectList.size() ; i++){
			for(int j=0; j<gridObjectList.size(); j++){
				if(gridObjectList.get(i).getDoesHarm() && !(gridObjectList.get(j).getDoesHarm())){
					collisionMatrix[i][j]= new HurtCollision();
				}
				else if (gridObjectList.get(j).getDoesHarm() && !(gridObjectList.get(i).getDoesHarm()))
					collisionMatrix[i][j]= new HurtCollision();
				else if (i==j){
					collisionMatrix[i][j]=null;
				}
				else{
					collisionMatrix[i][j]= new BumpCollision();
				}
			}
			
		}
		return collisionMatrix;
	}
	
	public abstract void doCollision();
}
