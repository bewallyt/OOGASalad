package authoring;

import java.util.List;

import Data.FileLister;
/**
 * This class handles most of the Sprite image management for the front end, providing the String
 * arrays of images neccessary to create animated players and enemies
 * @author Pritam M, Richard Cao, Davis Treybig, Jacob Lettie
 *
 */
public class SpriteImageChooser {

	public static final String PLAYER_IMAGE_FOLDER="src/PlayerImages/";
	public SpriteImageChooser() {
	}
	
	private String[] convertedFileList(String s){
		FileLister f=new FileLister();
		List<String> list=f.getFileList(s);
		return convertListToArray(list);
		
	}
	/**
	 * @param sprite The name of a sprite, such as "Ash". This should be chosen from the options provided in
	 * getSpriteOptions() method
	 * @return Returns a list of the sprite images for a given sprite in a String array
	 */
	public String[] getSpriteImages(String sprite){
		String[] temp = convertedFileList(PLAYER_IMAGE_FOLDER+sprite);
		for(int i = 0; i < temp.length; i++){
			temp[i]="PlayerImages/"+sprite+"/"+temp[i];
		}
		
		return temp;
	}
	/**
	 * @return Returns the possible sprite options available to animate players and enemies. Use in conjunction with
	 * getSpriteImages to load a specific set of images. 
	 */
	public String[] getSpriteOptions(){
		return convertedFileList(PLAYER_IMAGE_FOLDER);
	}
	/**
	 * Converts a string list to an array of strings
	 * @param list List to be converted
	 * @return String[] version of the list
	 */
	private String[] convertListToArray(List<String> list){
		String[] array=new String[list.size()];
        int i=0;
        for(String s: list){
        	array[i]=s;
        	i++;
        }
       return array;
	}
 
	public static void main(String[] args){
		SpriteImageChooser sprite=new SpriteImageChooser();
		sprite.getSpriteOptions();
		sprite.getSpriteImages("Zelda");
	}
}
