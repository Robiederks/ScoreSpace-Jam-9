package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Graphics;

public class Handler {

	private Game game;
	
	private World world;
	private HUD hud;
	
	private int score;
	private int health;
	
	public Handler(Game game) {
		this.game = game;
		world = new World("level", this);
		hud = new HUD(this);
		health = 3;
	}
	
	public void tick() {
		world.tick();
		
		if (health <= 0) {
			game.gameOver();
		}
	}
	
	public void render(Graphics g) {
		world.render(g);
		hud.render(g);
	}
	
	public void keyPressed(int key) {
		world.keyPressed(key);
	}
	
	public void keyReleased(int key) {
		world.keyReleased(key);
	}
	
	public void keyTyped(char key) {
		world.keyTyped(key);
	}
	
	public void addScore(int dScore) {
		score += dScore;
	}

	public int getScore() {
		return score;
	}

	public void addHealth(int dHealth) {
		health += dHealth;
	}

	public int getHealth() {
		return health;
	}
	
}
