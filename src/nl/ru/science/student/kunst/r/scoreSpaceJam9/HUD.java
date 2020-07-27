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
	private Sprite heart;
	
	public HUD(Handler handler) {
		this.handler = handler;
		heart = new Sprite("heart");
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		
		FontMetrics fm = g.getFontMetrics();
		
		g.drawString("Score: " + handler.getScore(), 20, 20 + fm.getAscent());
		
		if (handler.getHealth() <= 10) {
			for (int i = 1; i <= handler.getHealth(); i++) {
				heart.draw(g, Game.WIDTH - 52 * i, 20);
			}
		}
		else {
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			fm = g.getFontMetrics();
			heart.draw(g, Game.WIDTH - 52, 20);
			
			String healthString = handler.getHealth() + " " + (char) 0x00D7 + " ";
			g.drawString(healthString, Game.WIDTH - 52 - fm.stringWidth(healthString), 36 - fm.getHeight()/2 + fm.getAscent());
		}
	}

}
