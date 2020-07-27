package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class HopItem extends Collectable {
	
	public HopItem(int x, int y, World world) {
		super(x, y, world);
		sprite = new Sprite("hop");
	}
	
	public void use(int player_x, int player_y) {
		world.setHop(150);
	}
}
