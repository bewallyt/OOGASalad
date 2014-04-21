package authoring;

public class NPCData extends GridObject{
	private NPCResponseNode myRoot;
	public NPCData(int x, int y, String image, NPCResponseNode root) {
		super(x,y,image);
		myRoot = root;
		init();
	}
	public NPCResponseNode getDialogue(){
		return myRoot;
	}
}
