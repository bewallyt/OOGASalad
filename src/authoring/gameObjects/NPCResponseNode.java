package authoring.gameObjects;

import java.util.ArrayList;
import java.util.List;

import authoring.UserQueryNode;

public class NPCResponseNode {
	private String myString;
	private List<UserQueryNode> myChildren;
	private String myItem;
	public NPCResponseNode(String s){
		myString = s;
		myChildren = new ArrayList<UserQueryNode>();
	}
	public void setString(String s){
		myString = s;
	}
	public void addChild(UserQueryNode n){
		myChildren.add(n);
	}
	public void removeChild(UserQueryNode n){
		for(UserQueryNode nn: myChildren){
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
	public List<UserQueryNode> getChildren(){
		return myChildren;
	}
	public String getItem(){
		return myItem;
	}
}
