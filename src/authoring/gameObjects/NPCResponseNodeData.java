package authoring.gameObjects;

import java.util.ArrayList;
import java.util.List;

import authoring.UserQueryNodeData;
/**
 * Class that holds information related to NPCResponses
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class NPCResponseNodeData {
	private String myString;
	private List<UserQueryNodeData> myChildren;
	private String myItem;
	public NPCResponseNodeData(String s){
		myString = s;
		myChildren = new ArrayList<UserQueryNodeData>();
	}
	/**
	 * Sets the primary String in this node
	 * @param s
	 */
	public void setString(String s){
		myString = s;
	}
	/**
	 * Adds a child user query to the NPCResponseNode
	 * @param n UserQueryNodeData to be added
	 */
	public void addChild(UserQueryNodeData n){
		myChildren.add(n);
	}
	/**
	 * Removes a child user query
	 * @param n UserQueryNodeData to be removed
	 */
	public void removeChild(UserQueryNodeData n){
		for(UserQueryNodeData nn: myChildren){
			if(n==nn)
				myChildren.remove(nn);
		}
	}
	/**
	 * Sets an item for this response node
	 * @param s Name of the item to be added
	 */
	public void setItem(String s){
		myItem = s;
	}

	/**
	 * Gets the current string in the NPCResponseNode
	 */
	public String getString(){
		return myString;
	}
	/**
	 * Returns a list of all current UserQueryNodeData objects
	 */
	public List<UserQueryNodeData> getChildren(){
		return myChildren;
	}
	/**
	 * Returns the item associated with this response node
	 */
	public String getItem(){
		return myItem;
	}
}
