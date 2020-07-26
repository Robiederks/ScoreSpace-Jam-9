package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class TrapItem extends Collectable{
	
	public TrapItem(int x, int y, World world) {
		super(x, y, world);
	}
	
	public void use(int player_x, int player_y) {
		world.AddnonPlayer(new Trap(player_x, player_y, world));
		world.RemovenonPlayer(this);	
	}
	
}
