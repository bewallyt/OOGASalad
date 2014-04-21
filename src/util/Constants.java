package util;


/**
 * Defines global constants for both the game engine
 * and the authoring environment.
 * 
 * @author Team Rocket
 * 
 */

public class Constants {
	// player
	public static final int CANVASWIDTH = 400, CANVASHEIGHT = 400;
	public static final int TILE_SIZE = 40;

	public static final String SAVEDGAMESPATH = "/src/SavedGames";
	public static final String SELECTGAMETEXT = "Select a Game";
		
    // engine
    public static final String IMAGEPATH = "ImageFiles/";
    public static final String GRIDOBJECTPATH = "gridobject/";
    public static final String TILEIMAGEPATH = "TileImage/";
    
    // authoring
    public static final String BARRIER = "Barrier";
    public static final String DOOR = "Door";
    public static final String NPC = "NPC";
    public static final String ENEMY = "ENEMY";
    
    public static final int X_CONST = 0;
    public static final int Y_CONST = 1;
    public static final int IMAGE_CONST = 2;
    public static final int NAME_CONST = 3;
    public static final int VALUES_CONST = 4;
    public static final int WEAPONS_CONST = 5;
    
    public static final int TO_X_CONST = 6;
    public static final int TO_Y_CONST = 7;
    public static final int TO_MAP_CONST = 8;
    
    public static final int ROOT_CONST = 9;
    
    
    // data
    
    // util
    public final static int BUFFER_SIZE = 12800000;

}
