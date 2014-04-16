package Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFile {
	private String imageType;
	private String name;
	public static final String SRC="src/";
	
	public ImageFile(String name, String imageType) {
		this.name=name;
		this.imageType=imageType;
		System.out.println("Name: "+name + "   Type: "+ imageType);
	}

	public String getName(){
		return name;
	}
	public void changeName(String s){
		name=s;
	}
	public String getType(){
		return imageType;
	}
	public Image getImage(){
		File file=getFile();
		BufferedImage temp;
		try {
			temp = ImageIO.read(file);
		} catch (IOException e) {
			temp = null;
		}
		return (Image)temp;
		
	}
	public File getFile(){
		return new File(SRC+imageType+"/"+name);
	}
}
