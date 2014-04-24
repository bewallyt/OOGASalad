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
	public static final int CANVASWIDTH = 500, CANVASHEIGHT = 500;
	public static final int TILE_SIZE = 40;

	public static final String SAVEDGAMESPATH = "/src/SavedGames";
	public static final String SELECTGAMETEXT = "Select a Game";
		
    // engine
	public static final int DEF_TIL = 1;
	public static final int SPEED = 1;
    public static final String IMAGEPATH = "ImageFiles/";
    public static final String GRIDOBJECTPATH = "gridobject/";
    public static final String TILEIMAGEPATH = "TileImage/";
    
    // authoring
    public static final String BARRIER = "engine.gridobject.Barrier";
    public static final String DOOR = "engine.gridobject.Door";
    public static final String NPC = "engine.gridobject.person.NPC";
    public static final String ENEMY = "engine.gridobject.person.Enemy";
    
    public static final int WIDTH_CONST = 0;
    public static final int HEIGHT_CONST = 1;
    public static final int IMAGE_CONST = 2;

    public static final int NAME_CONST = 3;
    public static final int VALUES_CONST = 4;
    public static final int WEAPONS_CONST = 5;
    public static final int MOVEMENT_CONST = 6;

    public static final int TO_X_CONST = 3;
    public static final int TO_Y_CONST = 4;
    public static final int TO_MAP_CONST = 5;

    public static final int ROOT_CONST = 3;
    
    
    // data
    
    // util
    public final static int BUFFER_SIZE = 12800000;

}
