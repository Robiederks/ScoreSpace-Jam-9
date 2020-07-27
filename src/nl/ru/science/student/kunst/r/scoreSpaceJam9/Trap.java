package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Rectangle;

public class Trap extends Entity {
	
	public Trap(int x, int y, World world) {
		super(x, y, 24, 24, world);
		pixelX = x + 10;
		pixelY = y + 22;
		sprite = new Sprite("Trap");
	}
	
	public void getNewBounds() {
		pixelY -= 100;
		pixelX -= 100;
		height = 200;
		width = 200;
	}
	
	public void tick() {
	}
}
