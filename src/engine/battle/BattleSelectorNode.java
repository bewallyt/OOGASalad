package engine.battle;

import java.util.ArrayList;
import java.util.List;

import engine.dialogue.MatrixNode;

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
	
	public String toString(){
		return myName;
	}
	
	public void setChild(BattleExecutorNode child){
		myChildren.add(child);
	}
	
	public List<BattleExecutorNode> getChildren(){
		return myChildren;
	}
	
	
	
	

}
