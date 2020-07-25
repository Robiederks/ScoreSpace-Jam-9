package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.event.KeyEvent;

/**
 * De bestuurbare speler in het spel.
 * @author Robert
 *
 */
public class Player extends Entity {

	int vx;
	int vy;
	
	public Player(int x, int y, World world) {
		super(x, y, 60, 80, world);
		sprite = new Sprite("player");
		vx = 0;
		vy = 0;
	}

	@Override
	public void tick() {
		pixelX += vx;
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
		}
	}

}
