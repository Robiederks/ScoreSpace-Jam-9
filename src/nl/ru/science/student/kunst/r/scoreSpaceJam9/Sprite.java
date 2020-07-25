package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Regelt het bewaren en tekenen van sprites.
 * @author Robert
 *
 */
public class Sprite {
	
	private BufferedImage image;
	
	public Sprite(String filename) {
		try {
			image = ImageIO.read(new File("res" + File.separator + "sprites" + File.separator + filename + ".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

}
