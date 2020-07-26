package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 * Class die alles in en rond de muur regelt, zoals de muur, de speler, de monsters, etcetera.
 * @author Robert
 *
 */
public class World {
	
	private Handler handler;
	
	private int numberOfLadders;
	private int wallHeight; // Hoogte van de muur in aantal sporten
	
	public static final int LADDER_WIDTH = 100; // Breedte in pixels van een kolom
	public static final int STEP_HEIGHT = 35; // Hoogte in pixels tussen sporten
	
	private Player player;
	private ArrayList<Entity> nonPlayers;
	
	private ArrayList<Entity> nonPlayersToAdd;
	private ArrayList<Entity> nonPlayersToRemove;
	
	private Inventory inventory;
	
	private int gameTimer;
	private int monsterTimer;
	private Random random;
	
	public World(String filename, Handler handler) {
		this.handler = handler;
		nonPlayers = new ArrayList<>();
		nonPlayersToAdd = new ArrayList<>();
		nonPlayersToRemove = new ArrayList<>();
		inventory = new Inventory();
		random = new Random();
		readFile(filename);
	}
	
	/**
	 * Leest een levelbestand uit
	 * @param filename
	 */
	public void readFile(String filename) {
		Scanner in;
		try {
			in = new Scanner(new File("res" + File.separator + "levels" + File.separator + filename));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		// Formaat van het spel
		numberOfLadders = in.nextInt();
		wallHeight = in.nextInt();
		
		player = new Player(numberOfLadders/2, 0, this);
		
		monsterTimer = 60;
		
		// Bestand afsluiten
		in.close();
	}
	
	public void tick() {
		monsterTimer--;
		gameTimer++;
		while (monsterTimer <= 0) {
			monsterTimer = random.nextInt(60 + 300000/(gameTimer+600));
			nonPlayers.add(new Monster(random.nextInt(numberOfLadders), getWallHeight() + 2, this, 1));
		}
		
		player.tick();
		
		for (Entity entity : nonPlayers) {
			entity.tick();
		}
		
		for (Entity entity : nonPlayers) {
			if (entity.getBounds().intersects(player.getBounds())) {
				if (player.isKicking() && entity.getClass().equals(Monster.class)) {
					Monster monster = (Monster) entity;
					monster.damage();
				}
				if (entity instanceof Collectable) {
					Collectable item = (Collectable) entity;
					inventory.addItem(item);
					removeNonPlayer(item);
				}
			}
		}
		
		nonPlayers.removeAll(nonPlayersToRemove);
		nonPlayersToRemove.clear();
		nonPlayers.addAll(nonPlayersToAdd);
		nonPlayersToAdd.clear();
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0x888800));
		g.fillRect(0, Game.HEIGHT - STEP_HEIGHT * getWallHeight(), Game.WIDTH, STEP_HEIGHT * getWallHeight());
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < numberOfLadders; i++) {
			g.fillRect((Game.WIDTH - LADDER_WIDTH * numberOfLadders)/2 + i * LADDER_WIDTH + 20, Game.HEIGHT - STEP_HEIGHT * getWallHeight(), 5, STEP_HEIGHT * getWallHeight());
			g.fillRect(150 + i * LADDER_WIDTH + 75, Game.HEIGHT - STEP_HEIGHT * getWallHeight(), 5, STEP_HEIGHT * getWallHeight());
			for (int j = 1; j < getWallHeight(); j++) {
				g.fillRect(150 + i * LADDER_WIDTH + 20, Game.HEIGHT - j * STEP_HEIGHT, 60, 5);
			}
		}
		
		player.render(g);
		
		for (Entity entity : nonPlayers) {
			entity.render(g);
		}
		
		inventory.render(g);
	}
	
	public void keyPressed(int key) {
		if (key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9 && key - 48 < numberOfLadders) {
			nonPlayersToAdd.add(new Monster(key - 48, getWallHeight() + 2, this, 1));
		}
		else if (key == KeyEvent.VK_C) {
			addNonPlayer(new TestItem(2, this));
		}
		else if (key == KeyEvent.VK_X) {
			inventory.useItem(player.getPixelX(), player.getPixelY());
		}
		else {
			player.keyPressed(key);
		}
	}
	
	public void keyReleased(int key) {
		player.keyReleased(key);
	}
	
	public void keyTyped(char key) {
		inventory.keyTyped(key);
	}

	public int getWallHeight() {
		return wallHeight;
	}
	
	public int getNumberOfLadders() {
		return numberOfLadders;
	}
	
	public void addScore(int dScore) {
		handler.addScore(dScore);
	}
	
	public void addHealth(int dHealth) {
		handler.addHealth(dHealth);
	}
	
	public void addNonPlayer(Entity entity) {
		nonPlayersToAdd.add(entity);
	}
	
	public void removeNonPlayer(Entity entity) {
		nonPlayersToRemove.add(entity);
	}

}
