package authoring;

import authoring.gameObjects.NPCResponseNodeData;

public class UserQueryNodeData {
	private String myString;
	private NPCResponseNodeData myChild;
	private String myItem;
	public UserQueryNodeData(String s){
		myString = s;
	}
	public UserQueryNodeData(){
		
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
