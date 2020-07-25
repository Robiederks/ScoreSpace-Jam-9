package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstracte class voor alle bewegende objecten in het spel.
 * @author Robert
 *
 */
public abstract class Entity {
	
	protected World world;
	
	protected int x;
	protected int y;
	
	protected int pixelX;
	protected int pixelY;
	protected int width;
	protected int height;
	
	protected Sprite sprite;
	
	public Entity(int x, int y, int width, int height, World world) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
		pixelX = 150 + x * World.LADDER_WIDTH;
		pixelY = 100 + y * World.STEP_HEIGHT;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(pixelX, pixelY, width, height);
	}
	
	public abstract void tick();
	
	public void render(Graphics g) {
		sprite.draw(g, pixelX, pixelY);
	}

}
