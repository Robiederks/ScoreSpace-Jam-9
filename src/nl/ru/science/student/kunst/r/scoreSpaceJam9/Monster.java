package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class Monster extends Entity {

	private int speed;
	private int t;
	private int lives;
	
	public Monster(int x, int y, World world, int start_lives) {
		super(x, y, 60, 80, world);
		speed = 1;
		sprite = new Sprite("monster");
		lives = start_lives;
		t = 0;
	}

	@Override
	public void tick() {
		if (lives != 0) {
			pixelY -= speed;
			if (pixelY <= 10) {
				lives -= 1;
			}
		}
		else {
			pixelY += 0.01*0.5*9.81*(t*t);
			t += 1;
		}
	}
	
	public void damage() {
		lives -= 1;
	}
}
