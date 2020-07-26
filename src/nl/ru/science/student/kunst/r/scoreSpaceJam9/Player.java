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
		if (pixelY + 69 <= Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight() || world.Hopperdie = True) {
			if (pixelX + vx >= 0 && pixelX + width + vx <= Game.WIDTH) {
				pixelX += vx;
			}
		}
		if (pixelY + 69 <= Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight()) {//&& pixelY + 69 + vy > Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight()) {
			for (int i = 0; i < world.getNumberOfLadders(); i++) {
				if (i*World.LADDER_WIDTH + 0.2*(World.LADDER_WIDTH - 44) + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2 <= pixelX && i*World.LADDER_WIDTH + 0.8*(World.LADDER_WIDTH - 44) + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2 >= pixelX) {
					if (vy > 0) {
						pixelX = i*World.LADDER_WIDTH + (World.LADDER_WIDTH - 44)/2 + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2;
						pixelY += vy;
					}
				}
			}
		}
		else if (pixelY + vy <= Game.HEIGHT - height) {
			pixelY += vy;
		}
		
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
			kickTimer = 20;
			break;
		}
	}
	
	public void keyReleased(int key) {
		switch (key) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			if (vy == -5) {
				vy = 0;
			}
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			if (vx == -5) {
				vx = 0;
			}
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			if (vy == 5) {
				vy = 0;
			}
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			if (vx == 5) {
				vx = 0;
			}
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
