package engine.gridobject;

import engine.gridobject.person.Player;

public class Door{
	int myX;
	int myY;
	public Door (int xpos, int ypos){
		myX = xpos;
		myY = ypos;
	}
	
	public int getX(){
		return myX;
	}
	
	public int getY(){
		return myY;
	}
	
	public boolean playerAtDoor(Player player){
		return (Math.abs(player.getX()-myX)<player.getWidth() && Math.abs(player.getY()-myY)<player.getHeight());
	}
}
