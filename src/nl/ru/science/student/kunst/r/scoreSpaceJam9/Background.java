package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Graphics;

public class Background {

	public Background() {
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Color color = new Color(0x80DAEB);
		for (int i = 0; i < 60; i++) {
			g.setColor(color);
			g.fillRect(0, 10 * i, Game.WIDTH, 10);
			color = new Color(color.getRed() - 1, color.getGreen() - 1, 0xEB);
		}
	}

}
