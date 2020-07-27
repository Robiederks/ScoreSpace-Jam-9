package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;

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
	
	private Sprite[][] sprites;
	private boolean kickEnabled;
	private boolean spaceDown;
	
	public Player(int x, int y, World world) {
		super(x, y, 44, 69, world);
		Sprite[][] sprites = {{new Sprite("player_idle")},
							{new Sprite("player_climb1"), new Sprite("player_climb8")},
							{new Sprite("player_kick1"), new Sprite("player_kick2")}};
		this.sprites = sprites;
		sprite = sprites[0][0];
		vx = 0;
		vy = 0;
		kicking = false;
		kickEnabled = true;
		spaceDown = false;
	}

	@Override
	public void tick() {
		if (pixelY + 69 <= Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight() || (world.gethopTimer() >= 0 && (0.2*(World.LADDER_WIDTH - 44) + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2 <= pixelX && (world.getNumberOfLadders()-1)*World.LADDER_WIDTH + 0.8*(World.LADDER_WIDTH - 44) + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2 >= pixelX))) {
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
		
		kickTimer--;
		if (kickTimer <= 0) {
			kicking = false;
			height = 69;
			
			
			if (kickTimer <= -10) {
				kickEnabled = true;
			}
		}
		else {
			sprite = sprites[2][pixelY/35 % 2];
		}
		
		if (!kicking) {
			if (pixelY + 69 > Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight()) {
				sprite = sprites[1][pixelY/35 % 2];
			}
			else {
				sprite = sprites[0][0];
			}
		}
		else {
			sprite = sprites[2][pixelY/35 % 2];
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
			if (kickEnabled && !spaceDown) {
				kickTimer = 10;
				kicking = true;
				height = 90;
				kickEnabled = false;
				spaceDown = true;
			}
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
			if (pixelY + 69 > Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight()) {
				int ladder = (pixelX + 22 - (Game.WIDTH-World.LADDER_WIDTH*world.getNumberOfLadders())/2) / World.LADDER_WIDTH;
				pixelX = ladder*World.LADDER_WIDTH + (World.LADDER_WIDTH - 44)/2 + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2;
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
			if (pixelY + 69 > Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight()) {
				int ladder = (pixelX + 22 - (Game.WIDTH-World.LADDER_WIDTH*world.getNumberOfLadders())/2) / World.LADDER_WIDTH;
				pixelX = ladder*World.LADDER_WIDTH + (World.LADDER_WIDTH - 44)/2 + (Game.WIDTH - World.LADDER_WIDTH*world.getNumberOfLadders())/2;
			}
			break;
		case KeyEvent.VK_SPACE:
			spaceDown = false;
		}
	}

	public boolean isKicking() {
		return kicking;
	}
	
	public Rectangle getKickBounds() {
		return new Rectangle(pixelX , pixelY + 69, width, 21);
	}
}
