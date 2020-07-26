package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class BulletItem extends Collectable {
	
	public BulletItem(int x, int y, World world) {
		super(x, y, world);
	}
	
	public void use(int player_x, int player_y) {
		world.addNonPlayer(new Bullet(player_x, player_y, world));
	}

}
