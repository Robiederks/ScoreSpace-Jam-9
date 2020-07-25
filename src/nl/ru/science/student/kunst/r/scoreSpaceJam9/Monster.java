package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class Monster extends Entity {

	int speed;
	
	public Monster(int x, int y, World world) {
		super(x, y, 60, 80, world);
		speed = 1;
		sprite = new Sprite("monster");
	}

	@Override
	public void tick() {
		pixelY -= speed;
	}

}
