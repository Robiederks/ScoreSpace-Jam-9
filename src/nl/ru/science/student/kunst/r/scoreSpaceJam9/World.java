package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
	
	private Queue<Integer> monsterTimes;
	private Queue<Integer> monsterLocations;
	
	private int monsterTimer;
	
	public World(String filename, Handler handler) {
		this.handler = handler;
		nonPlayers = new ArrayList<>();
		nonPlayersToAdd = new ArrayList<>();
		nonPlayersToRemove = new ArrayList<>();
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
		
		// Monsters
		monsterLocations = new LinkedList<>();
		monsterTimes = new LinkedList<>();
		int numberOfMonsters = in.nextInt();
		for (int i = 0; i < numberOfMonsters; i++) {
			int timeSinceLast = in.nextInt();
			int ladder = in.nextInt();
			monsterLocations.add(ladder);
			monsterTimes.add(timeSinceLast);
		}
		monsterTimes.add(Integer.MAX_VALUE); // +oneindig als laatste wachttijd om een lege rij te voorkomen
		
		player = new Player(numberOfLadders/2, 0, this);
		
		// Bestand afsluiten
		in.close();
	}
	
	public void tick() {
		monsterTimer++;
		while (monsterTimer >= monsterTimes.peek()) {
			monsterTimer = 0;
			monsterTimes.poll();
			nonPlayers.add(new Monster(monsterLocations.poll(), getWallHeight() + 2, this, 1));
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
			}
		}
		
		nonPlayers.removeAll(nonPlayersToRemove);
		nonPlayersToRemove.clear();
		nonPlayers.addAll(nonPlayersToAdd);
		nonPlayersToAdd.clear();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
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
	}
	
	public void keyPressed(int key) {
		if (key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9 && key - 48 < numberOfLadders) {
			nonPlayersToAdd.add(new Monster(key - 48, getWallHeight() + 2, this, 1));
		}
		else {
			player.keyPressed(key);
		}
	}
	
	public void keyReleased(int key) {
		player.keyReleased(key);
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

}
