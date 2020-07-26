package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class Trap extends Entity {
	
	public Trap(int x, int y, World world) {
		super(x, y, 24, 24, world);
		sprite = new Sprite("Trap");
	}
	
	public void tick() {
	}
}
