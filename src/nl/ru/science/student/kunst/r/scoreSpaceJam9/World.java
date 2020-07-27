package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
	
	private boolean Hopperdiehop;
	private int gameTimer;
	private int monsterTimer;
	private int itemTimer;
	private int itemTimelimit;
	private int freezeTimer;
	private int hopTimer;
	private Random random;
	
	private Sprite wall;
	private Sprite ladder;
	
	public World(String filename, Handler handler) {
		this.handler = handler;
		nonPlayers = new ArrayList<>();
		nonPlayersToAdd = new ArrayList<>();
		nonPlayersToRemove = new ArrayList<>();
		inventory = new Inventory();
		random = new Random();
		wall = new Sprite("wall");
		ladder = new Sprite("ladder");
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
		itemTimer = 1000;
		itemTimelimit = -1;
		freezeTimer = 0;
		hopTimer = 0;
		Hopperdiehop = true;
		
		// Bestand afsluiten
		in.close();
	}
	
	public void tick() {
		monsterTimer--;
		itemTimer--;
		itemTimelimit--;
		gameTimer++;
		freezeTimer--;
		hopTimer--;
		while (monsterTimer <= 0) {
			monsterTimer = 30 + random.nextInt(30 + 300000/(gameTimer+600));
			nonPlayers.add(new Monster(random.nextInt(numberOfLadders), getWallHeight() + 2, this, 1));
		}
		//maakt random verschillend items aan
		while (itemTimer <= 0) {
			itemTimer += 900 + random.nextInt(100);
			itemTimelimit = 600;
			int randitem = random.nextInt(5);
			switch (randitem) {
			case 0:
				addNonPlayer(new TrapItem(random.nextInt(numberOfLadders), 3 + random.nextInt(Game.HEIGHT/World.STEP_HEIGHT-5), this));
				break;
			case 1:
				addNonPlayer(new BulletItem(random.nextInt(numberOfLadders), 3 + random.nextInt(Game.HEIGHT/World.STEP_HEIGHT-5), this));
				break;
			case 2:
				addNonPlayer(new FreezeItem(random.nextInt(numberOfLadders), 3 + random.nextInt(Game.HEIGHT/World.STEP_HEIGHT-5), this));
				break;
			case 3:
				addNonPlayer(new HopItem(random.nextInt(numberOfLadders), 3 + random.nextInt(Game.HEIGHT/World.STEP_HEIGHT-5), this));
				break;
			case 4:
				addNonPlayer(new HealthItem(random.nextInt(numberOfLadders), 3 + random.nextInt(Game.HEIGHT/World.STEP_HEIGHT-5), this));
				break;
			}
		}
		if (itemTimelimit <= 0) {
			for (Entity entity : nonPlayers) {
				if (entity instanceof Collectable) {
					nonPlayersToRemove.add(entity);
					itemTimelimit = -1;
				}
			}
		}
		if (hopTimer <= 0) {
			Hopperdiehop = false;
		}
		
		player.tick();
		
		if (freezeTimer <= 0) {
			for (Entity entity : nonPlayers) {
				entity.tick();
			}
		}
		
		for (Entity entity : nonPlayers) {
			if (entity.getBounds().intersects(player.getKickBounds())) {
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
			for (Entity other_entity : nonPlayers) {
				if (entity.getBounds().intersects(other_entity.getBounds())) {
					if (entity.getClass().equals(Monster.class)) {
						if (other_entity.getClass().equals(Trap.class)) {
							other_entity.getNewBounds();
							for (Entity new_entity : nonPlayers) {
								if (new_entity.getClass().equals(Monster.class) && new_entity.getBounds().intersects(other_entity.getBounds())) {
									Monster monster = (Monster) new_entity;
									monster.damage();
									removeNonPlayer(new_entity);
									removeNonPlayer(other_entity);
								}
							}
							
						}
						if (other_entity.getClass().equals(Bullet.class)) {
							Monster monster = (Monster) entity;
							monster.damage();
						}
					}
				}
			}
		}
		
		for (Entity entity : nonPlayers) {
			if (entity.pixelY >= Game.HEIGHT + 5) {
				nonPlayersToRemove.add(entity);
			}
		}
		
		nonPlayers.removeAll(nonPlayersToRemove);
		nonPlayersToRemove.clear();
		nonPlayers.addAll(nonPlayersToAdd);
		nonPlayersToAdd.clear();
	}
	
	public void render(Graphics g) {
		wall.draw(g, 0, Game.HEIGHT - STEP_HEIGHT * getWallHeight());
		
		for (int i = 0; i < numberOfLadders; i++) {
			ladder.draw(g,(Game.WIDTH - LADDER_WIDTH * numberOfLadders)/2 + i * LADDER_WIDTH + 20, Game.HEIGHT - STEP_HEIGHT * getWallHeight() - 10);
			
		}
		
		player.render(g);
		
		for (Entity entity : nonPlayers) {
			entity.render(g);
		}
		
		inventory.render(g);
	}
	
	public void keyPressed(int key) {
		if (key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9 && key - 48 < numberOfLadders) {
			addNonPlayer(new Monster(key - 48, getWallHeight() + 2, this, 1));
		}
		else if (key == KeyEvent.VK_C) {
			nonPlayersToAdd.add(new TestItem(2, this));
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
	
	public boolean getHopperdiehop() {
		return Hopperdiehop;
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
	
	public void setFreeze(int x) {
		freezeTimer = x;
	}
	
	public void setHop(int x) {
		Hopperdiehop = true;
		hopTimer = x;
	}
}
