package authoring;

public class NPCObject extends GridObject{
	private NPCResponseNode myRoot;
	public NPCObject(int x, int y, String image, NPCResponseNode root) {
		super(x,y,image);
		myRoot = root;
		init();
	}
	public NPCResponseNode getDialogue(){
		return myRoot;
	}
}
