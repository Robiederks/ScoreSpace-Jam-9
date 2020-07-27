package nl.ru.science.student.kunst.r.scoreSpaceJam9;
import java.util.Random;

public class Monster extends Entity {
	
	Random rand = new Random();
	
	private int speed;
	private int t;
	private int lives;
	private int fall_direction;
	
	public Monster(int x, int y, World world, int startLives) {
		super(x, y, 44, 69, world);
		this.fall_direction = rand.nextInt(2);
		speed = 1;
		sprite = new Sprite("monster");
		lives = startLives;
		t = 0;
	}

	@Override
	public void tick() {
		if (lives > 0) {
			pixelY -= speed;
			if (pixelY <= 10) {
				lives -= 1;
			}
		}
		else {
			pixelY += 0.005*0.5*9.81*(t*t);
			pixelX += (this.fall_direction*2 - 1);
			t += 1;
		}
		
		if (pixelY < Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight() - height) {
			world.addHealth(-1);
			world.removeNonPlayer(this);
		}
		
		if (pixelY > Game.HEIGHT + 500) {
			world.removeNonPlayer(this);
		}
	}
	
	public void damage() {
		lives -= 1;
		if (lives == 0) {
			world.addScore(1);
		}
	}
	
	public void getNewBounds() {
	}
}
