package authoring;

import authoring.gameObjects.NPCResponseNodeData;
/**
 * Class that handles information relevant to a UserQuery
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class UserQueryNodeData {
	private String myString;
	private NPCResponseNodeData myChild;
	private String myItem;
	public UserQueryNodeData(String s){
		myString = s;
	}
	public UserQueryNodeData(){
		
	}
	/**
	 * Sets the string of the user query
	 * @param s String to be used
	 */
	public void setString(String s){
		myString = s;
	}
	/**
	 * Sets the item of the user query
	 * @param s String name of the item
	 */
	public void setItem(String s){
		myItem = s;
	}
	/**
	 * Sets the child response of the UserQuery
	 * @param n NPCResponseData to be used
	 */
	public void setChild(NPCResponseNodeData n){
		myChild = n;
	}
	/**
	 * Returns the string of the UserQueryNodeData
	 */
	public String getString(){
		return myString;
	}
	/**
	 * Returns the item of the UserQueryNodeData
	 */
	public String getItem(){
		return myItem;
	}
	/**
	 * Returns the child NPCResponseNodeData of the UserQueryNodeData
	 */
	public NPCResponseNodeData getChild(){
		return myChild;
	}
}
