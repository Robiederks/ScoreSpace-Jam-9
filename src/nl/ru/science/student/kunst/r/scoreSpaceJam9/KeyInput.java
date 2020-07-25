package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		handler.keyPressed(key);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		handler.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		char key = event.getKeyChar();
		handler.keyTyped(key);
	}
	
	

}
