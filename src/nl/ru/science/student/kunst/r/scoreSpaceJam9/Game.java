package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -3727750864526617945L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public Game() {
		new Window(WIDTH , HEIGHT, "Climbing the social ladder",this);
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Graphics netjes afsluiten
		g.dispose();
		bs.show();
	}

	private void tick() {
		
	}
	
	

}
