package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.event.KeyEvent;

/**
 * De bestuurbare speler in het spel.
 * @author Robert
 *
 */
public class Player extends Entity {

	private int vx;
	private int vy;
	
	private boolean kicking;
	private int kickTimer;
	
	private Sprite[] sprites;
	
	public Player(int x, int y, World world) {
		super(x, y, 44, 69, world);
		Sprite[] sprites = {new Sprite("player"), new Sprite("player_kick1")};
		this.sprites = sprites;
		sprite = sprites[0];
		
		vx = 0;
		vy = 0;
		kicking = false;
	}

	@Override
	public void tick() {
		if (pixelY <= 31) {
			pixelX += vx;
		}
		pixelY += vy;
	}
	
	public void keyPressed(int key) {
		switch (key) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			vy = -5;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			vx = -5;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			vy = 5;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			vx = 5;
			break;
		case KeyEvent.VK_SPACE:
			kicking = true;
			height = 90;
			sprite = sprites[1];
			break;
		}
	}
	
	public void keyReleased(int key) {
		switch (key) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP: 
			vy = 0;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			vx = 0;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			vy = 0;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			vx = 0;
			break;
		case KeyEvent.VK_SPACE:
			kicking = false;
			height = 69;
			sprite = sprites[0];
			break;
		}
	}

	public boolean isKicking() {
		return kicking;
	}

}
