package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class Bullet extends Entity {
	
	private int speed;
	
	public Bullet(int x, int y, World world) {
		super(x, y, 24, 24, world);
		sprite = new Sprite("Bullet");
		speed = 2;
	}
	
	public void tick() {
		pixelY += speed;
	}	
}
