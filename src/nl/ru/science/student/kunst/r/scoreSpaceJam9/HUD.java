package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Heads-up display showing the score and other stats during the game.
 * @author Robert
 *
 */
public class HUD {

	private Handler handler;
	
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		
		FontMetrics fm = g.getFontMetrics();
		
		g.drawString("Score: " + handler.getScore(), 20, 20 + fm.getAscent());
	}

}
