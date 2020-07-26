package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	private Game game;

	public KeyInput(Game game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		game.keyPressed(key);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		game.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		char key = event.getKeyChar();
		game.keyTyped(key);
	}
	
	

}
