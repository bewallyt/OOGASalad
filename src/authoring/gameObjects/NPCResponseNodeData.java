package authoring.gameObjects;

import java.util.ArrayList;
import java.util.List;

import authoring.UserQueryNodeData;

public class NPCResponseNodeData {
	private String myString;
	private List<UserQueryNodeData> myChildren;
	private String myItem;
	public NPCResponseNodeData(String s){
		myString = s;
		myChildren = new ArrayList<UserQueryNodeData>();
	}
	public void setString(String s){
		myString = s;
	}
	public void addChild(UserQueryNodeData n){
		myChildren.add(n);
	}
	public void removeChild(UserQueryNodeData n){
		for(UserQueryNodeData nn: myChildren){
			if(n==nn)
				myChildren.remove(nn);
		}
	}
	public void setItem(String s){
		myItem = s;
	}

	
	public String getString(){
		return myString;
	}
	public List<UserQueryNodeData> getChildren(){
		return myChildren;
	}
	public String getItem(){
		return myItem;
	}
}
