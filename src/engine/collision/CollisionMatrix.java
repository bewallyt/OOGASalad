package engine.collision;

import java.util.List;

import engine.gridobject.GridObject;

public class CollisionMatrix {
	private List<GridObject> myGridObjectList;
	private CollisionHandler[][] myCollisionMatrix;

	/**
	 * Instantiates a new collision matrix.
	 *
	 * @param gridObjectList the grid object list
	 */
	public CollisionMatrix(List<GridObject> gridObjectList) {
		myGridObjectList = gridObjectList;
		myCollisionMatrix = makeCollisionMatrix();
	}

	private CollisionHandler[][] makeCollisionMatrix() {
		CollisionHandler[][] collisionMatrix = new CollisionHandler[myGridObjectList.size()]
																   [myGridObjectList.size()];
		for (int i = 0; i < myGridObjectList.size(); i++) {
			for (int j = 0; j < myGridObjectList.size(); j++) {
				if (i != j) {
					collisionMatrix[i][j] = new BumpCollision(myGridObjectList.get(i),myGridObjectList.get(j));
				} else {
					collisionMatrix[i][j] = new NullCollision(myGridObjectList.get(i),myGridObjectList.get(j));
				}
			}

		}
		return collisionMatrix;
	}

	/**
	 * Sets a new collision handler.
	 *
	 * @param handler the handler
	 * @param x the x coordinate of the handler in the matrix
	 * @param y the y coordinate of the handler in the matrix
	 */
	public void setCollisionHandler(CollisionHandler handler, int x, int y) {
		myCollisionMatrix[x][y] = handler;
	}
	
	public CollisionHandler[][] getMatrix() {
		return myCollisionMatrix;
	}

}
