package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class FreezeItem extends Collectable {
	
	public FreezeItem(int x, int y, World world) {
		super(x, y, world);
		sprite = new Sprite("collectable");
	}
	
	@Override
	public void use(int player_x, int player_y) {
		world.setFreeze();
	}
}
