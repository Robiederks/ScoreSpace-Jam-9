package nl.ru.science.student.kunst.r.scoreSpaceJam9;

public class TestItem extends Collectable {

	public TestItem(int x, World world) {
		super(x, world);
		sprite = new Sprite("collectable");
	}

	@Override
	public void use(int x, int y) {
	}

}
