package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class HealthItem extends Collectable{
	
	public HealthItem(int x, int y, World world) {
		super(x, y, world);
		sprite = new Sprite("collectable");
	}
	
	@Override
	public void use(int player_x, int player_y) {
		world.addHealth(1);
	}
}
