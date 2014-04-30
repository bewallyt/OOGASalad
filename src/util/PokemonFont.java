package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

import engine.gridobject.GridObject;

/*
 * Sets Pokemon Font instead of constantly calling those lines over and over.
 * Default size is 16f
 */

public class PokemonFont {

	public static final void setFont(Graphics g2d, float size) {

		InputStream is = GridObject.class.getResourceAsStream("PokemonGB.ttf");
		Font font = null;

		try {
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException e) {
				e.printStackTrace();
			}
			Font sizedFont = font.deriveFont(size);
			g2d.setFont(sizedFont);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
