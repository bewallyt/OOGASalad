package engine.collision;

import java.util.List;

import engine.gridobject.GridObject;

public class CollisionMatrix {
	private List<GridObject> myGridObjectList;
	private CollisionHandler[][] myCollisionMatrix;
	
	public CollisionMatrix(List<GridObject> gridObjectList){
		myGridObjectList = gridObjectList;
		myCollisionMatrix = makeCollisionMatrix();
	}
	
	private CollisionHandler[][] makeCollisionMatrix(){
		CollisionHandler[][] collisionMatrix = new CollisionHandler[myGridObjectList.size()][myGridObjectList.size()];
		for (int i=0; i<myGridObjectList.size() ; i++){
			for(int j=0; j<myGridObjectList.size(); j++){
				if(myGridObjectList.get(i).getDoesHarm() && !(myGridObjectList.get(j).getDoesHarm())){
					collisionMatrix[i][j]= new HurtCollision();
				}
				else if (myGridObjectList.get(j).getDoesHarm() && !(myGridObjectList.get(i).getDoesHarm()))
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
	
	public CollisionHandler[][] getMatrix(){
		return myCollisionMatrix;
	}
}
