package engine.collision;

import java.util.List;

import engine.gridobject.GridObject;
import engine.gridobject.item.Weapon;

public abstract class CollisionHandler {
	
	List<GridObject> gridObjectList;
	
	public CollisionHandler() {
	}
	
	public void makeCollisionMatrix(){
		CollisionHandler[][] collisionMatrix = new CollisionHandler[gridObjectList.size()][gridObjectList.size()];
		for (int i=0; i<gridObjectList.size() ; i++){
			for(int j=0; j<gridObjectList.size(); j++){
				if(gridObjectList.get(i) instanceof Weapon && !(gridObjectList.get(j) instanceof Weapon)){
					collisionMatrix[i][j]= new HurtCollision();
				}
				else if (gridObjectList.get(j) instanceof Weapon && !(gridObjectList.get(i) instanceof Weapon))
					collisionMatrix[i][j]= new HurtCollision();
				else if (i==j){
					collisionMatrix[i][j]=null;
				}
				else{
					collisionMatrix[i][j]= new BumpCollision();
				}
				gridObjectMatrix[i][j]= 
			}
			
		}
	}
	
	public abstract void doCollision();
}
