package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class TrapItem extends Collectable {
	
	public TrapItem(int x, int y, World world) {
		super(x, y, world);
		sprite = new Sprite("Trap_lit");
	}
	
	@Override
	public void use(int player_x, int player_y) {
		world.addNonPlayer(new Trap(player_x, player_y, world));	
	}
	
}
