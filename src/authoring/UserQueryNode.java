package authoring;

import authoring.gameObjects.NPCResponseNode;

public class UserQueryNode {
	private String myString;
	private NPCResponseNode myChild;
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
	public void setChild(NPCResponseNode n){
		myChild = n;
	}
	
	public String getString(){
		return myString;
	}
	public String getItem(){
		return myItem;
	}
	public NPCResponseNode getChild(){
		return myChild;
	}
}
