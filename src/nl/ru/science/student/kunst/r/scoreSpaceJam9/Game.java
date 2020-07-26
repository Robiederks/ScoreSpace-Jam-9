package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -3727750864526617945L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private Handler handler;
	private Menu menu;
	private Background background;
	
	private enum State {
		MENU, GAME
	}
	private State state;
	
	public Game() {
		background = new Background();
		
		menu = new Menu(this);
		addMouseListener(menu);
		
		addKeyListener(new KeyInput(this));
		
		new Window(WIDTH , HEIGHT, "The wall",this);
		
		state = State.MENU;
		
		requestFocus();
	}

	/**
	 * Start het spel en de loop
	 */
	public synchronized void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * De game loop
	 */
	@Override
	public void run() {
		double tps = 60;
		long lastTick = System.nanoTime();
		double tickLength = 1000000000/tps;
		int frames = 0;
		long startTime = System.currentTimeMillis();
		
		// Game loop
		while (true) {
			long now = System.nanoTime();
			long elapsedTime = now - lastTick;
			while (elapsedTime >= tickLength) {
				tick();
				lastTick += tickLength;
				elapsedTime = now - lastTick;
			}
			render();
			frames++;
			long currentTime = System.currentTimeMillis();
			long elapsed = currentTime - startTime;
			while (elapsed >= 1000) {
				System.out.println(frames + " FPS");
				frames = 0;
				startTime += 1000;
				elapsed = currentTime - startTime;
			}
		}
	}

	private void render() {
		// Graphics ophalen
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		// Hier gebeurt het
		background.render(g);	
		
		switch (state) {
		case MENU:
			menu.render(g);
			break;
		case GAME:
			handler.render(g);
			break;
		}
		
		
		// Graphics netjes afsluiten
		g.dispose();
		bs.show();
	}

	private void tick() {
		background.tick();
		
		switch (state) {
		case MENU:
			break;
		case GAME:
			handler.tick();
			break;
		}
	}

	public void gameOver(int score) {
		menu.gameOver(score);
		state = State.MENU;
		addMouseListener(menu);
	}

	public void play() {
		handler = new Handler(this);
		state = State.GAME;
		removeMouseListener(menu);
	}

	public void keyPressed(int key) {
		handler.keyPressed(key);
	}

	public void keyReleased(int key) {
		handler.keyReleased(key);
	}

	public void keyTyped(char key) {
		handler.keyTyped(key);
	}
	
	

}
