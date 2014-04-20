package authoring;

public class UserQueryNode {
	private String myString;
	private NPCResponseNode myChild;
	public UserQueryNode(String s){
		myString = s;
	}
	public void setString(String s){
		myString = s;
	}
	public void setChild(NPCResponseNode n){
		myChild = n;
	}
	
	public String getString(){
		return myString;
	}
	public NPCResponseNode getChild(){
		return myChild;
	}
}
