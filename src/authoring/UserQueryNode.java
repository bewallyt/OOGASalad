package authoring;

import authoring.gameObjects.NPCResponseNodeData;

public class UserQueryNode {
	private String myString;
	private NPCResponseNodeData myChild;
	private String myItem;
	public UserQueryNode(String s){
		myString = s;
	}
	public UserQueryNode(){
		
	}
	public void setString(String s){
		myString = s;
	}
	public void setItem(String s){
		myItem = s;
	}
	public void setChild(NPCResponseNodeData n){
		myChild = n;
	}
	
	public String getString(){
		return myString;
	}
	public String getItem(){
		return myItem;
	}
	public NPCResponseNodeData getChild(){
		return myChild;
	}
}
