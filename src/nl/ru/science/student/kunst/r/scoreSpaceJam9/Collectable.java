package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public abstract class Collectable extends Entity {

	private int state;
	private int timer;
	
	public Collectable(int x, int y, World world) {
		super(x, y, 24, 24, world);
		state = 0;
		sprite = new Sprite("Collectable");
	}

	@Override
	public void tick() {
		if (state == 0) {
			pixelY += 1;
			if (pixelY == Game.HEIGHT - World.STEP_HEIGHT * world.getWallHeight() - 49) {
				state = 1;
			}
		}
		else {
			timer++;
			if (timer == 10) {
				state = (state % 8) + 1;
				timer = 0;
			}
			switch (state) {
			case 1:
				pixelY += 1;
				break;
			case 2:
				if (timer % 2 == 0) {
					pixelY += 1;
				}
				break;
			case 3:
				break;
			case 4:
				if (timer % 2 == 0) {
					pixelY -= 1;
				}
				break;
			case 5:
				pixelY -= 1;
				break;
			case 6:
				if (timer % 2 == 0) {
					pixelY -= 1;
				}
				break;
			case 7:
				break;
			case 8:
				if (timer % 2 == 0) {
					pixelY += 1;
				}
				break;
			}
		}
	}
	
	public abstract void use(int player_x, int player_y);

}
