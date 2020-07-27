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
		pixelX = (Game.WIDTH - World.LADDER_WIDTH * world.getNumberOfLadders())/2 + x * World.LADDER_WIDTH + (World.LADDER_WIDTH - width)/2;
		pixelY = Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight() + y * World.STEP_HEIGHT - height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(pixelX, pixelY, width, height);
	}
	
	public Rectangle what() {
		return new Rectangle(1, 1, 1, 1);
	}
	
	public abstract void tick();
	
	public void render(Graphics g) {
		sprite.draw(g, pixelX, pixelY);
	}

	public int getPixelX() {
		return pixelX;
	}

	public int getPixelY() {
		return pixelY;
	}

}
