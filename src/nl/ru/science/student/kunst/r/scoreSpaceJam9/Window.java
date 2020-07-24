package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 612274539688287643L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		frame.requestFocus();
		
		game.start();
	}

}
