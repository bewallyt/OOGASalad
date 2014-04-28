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
	public static final int COLLISION_OFFSET=2;
	public static final int TILE_SIZE = 40;

	public static final String SAVEDGAMESPATH = "/src/SavedGames";
	public static final String SELECTGAMETEXT = "Select a Game";

    // engine
	public static final int DEF_TIL = 1;
	//public static final int SPEED = 1;
	public static final int DEF_MAX_STAT = 100;
    public static final String IMAGEPATH = "engine/images/";
    public static final String GRIDOBJECTPATH = "gridobject/";
    public static final String SPRITEPATH="PlayerImages/";
    public static final String PLAYERASHPATH = "PlayerImages/Ash/";
    public static final String TILEIMAGEPATH = "TileImage/";
	public static final String[] BATTLE_LABELS = new String[]{"Attack","Pokemon","Bag","Run"};
    public static final String[] ANIMIMAGES = {				
    	"PlayerUp0.png",
		"PlayerUp1.png",
		"PlayerUp2.png",
		"PlayerRight0.png",
		"PlayerRight1.png",
		"PlayerRight2.png",
		"PlayerDown0.png",
		"PlayerDown1.png",
		"PlayerDown2.png",
		"PlayerLeft0.png",
		"PlayerLeft1.png",
		"PlayerLeft2.png",
};
    
	public static final int UP = 38;
	public static final int DOWN = 40;
	public static final int LEFT = 37;
	public static final int RIGHT = 39;
	public static final int A = 65;
	public static final int B = 66;
	public static final int SPACE = 32;
	public static final int ESC = 27;
	public static final int ENTER = 10;
	public static final int SHIFT = 16;
	public static final int BACKSPACE = 8;
    
    // authoring
    public static final String BARRIER = "engine.gridobject.Barrier";
    public static final String DOOR = "engine.gridobject.Door";
    public static final String NPC = "engine.gridobject.person.NPC";
    public static final String ENEMY = "engine.gridobject.person.Enemy";
    public static final String HEALER = "engine.gridobject.person.Healer";
    public static final String SHOPKEEPER = "engine.gridobject.person.Shopkeeper";
    
    public static final int WIDTH_CONST = 0;
    public static final int HEIGHT_CONST = 1;
    public static final int IMAGE_CONST = 2;

    public static final int NAME_CONST = 3;
    public static final int VALUES_CONST = 4;
    public static final int ENEMY_MOVEMENT_CONST = 7;
    public static final int NPC_MOVEMENT_CONST = 4;
    public static final int WEAPONS_CONST = 5;
    public static final int WEP_LENGTH_CONST = 6;
    public static final int ENEMY_ALL_WEPS = 10;

    public static final int TO_X_CONST = 3;
    public static final int TO_Y_CONST = 4;
    public static final int TO_MAP_CONST = 5;

    public static final int RESPONSE_ROOT_CONST = 5;
    
    public static final int NPC_PLAYER_CONST = 6;
    public static final int NPC_ITEMS_CONST = 7;
    public static final int ENEMY_PLAYER_CONST = 8;
    public static final int SHOP_PLAYER_CONST = ;

    
    
    // data
    
    // util
    public final static int BUFFER_SIZE = 12800000;
	public static final String DAMAGE = "Damage";
	public static final String SPEED = "Speed";
	public static final String HEALTH = "Health";
	public static final String LEVEL = "Level";
	public static final String DEFENSE = "Defense";
	public static final int BATTLE_IMAGE_SIZE = 150;


}
