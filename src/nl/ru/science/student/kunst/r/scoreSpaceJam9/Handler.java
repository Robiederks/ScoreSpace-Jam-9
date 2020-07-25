package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Graphics;

public class Handler {

	private Game game;
	
	private World world;
	
	public Handler(Game game) {
		this.game = game;
		world = new World("level", this);
	}
	
	public void tick() {
		world.tick();
	}
	
	public void render(Graphics g) {
		world.render(g);
	}
	
	public void keyPressed(int key) {
		world.keyPressed(key);
	}
	
	public void keyReleased(int key) {
		world.keyReleased(key);
	}

}
