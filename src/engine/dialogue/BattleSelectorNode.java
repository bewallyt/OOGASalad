package engine.dialogue;

import java.util.ArrayList;
import java.util.List;

public class BattleSelectorNode implements MatrixNode{
	
	private List<BattleExecutorNode> myChildren;
	private String myName;
	
	public BattleSelectorNode(String name){
		myChildren = new ArrayList<BattleExecutorNode>();
		myName=name;
	}
	
	public void addExecutorNode(BattleExecutorNode node){
		myChildren.add(node);
	}
	
	public String getName(){
		return myName;
	}
	
	public void setChild(BattleExecutorNode child){
		myChildren.add(child);
	}
	
	public List<BattleExecutorNode> getChildren(){
		return myChildren;
	}
	
	
	
	

}
