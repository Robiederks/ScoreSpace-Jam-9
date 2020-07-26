package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

	private Game game;
	
	private Sprite button;
	
	private int clicked;
	
	private enum State {
		MAIN, HELP, GAME_OVER, LEADERBOARD
	}
	private State state;
	
	public Menu(Game game) {
		this.game = game;
		state = State.MAIN;
		button = new Sprite("menu_button");
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 44));
		FontMetrics fm = g.getFontMetrics();
		switch (state) {
		case MAIN:
			button.draw(g, 100, 100);
			button.draw(g, 100, 350);
			button.draw(g, 450, 100);
			button.draw(g, 450, 350);
			
			g.drawString("Play", 225 - fm.stringWidth("Play")/2, 175 - fm.getHeight()/2 + fm.getAscent());
			g.drawString("How to play", 575 - fm.stringWidth("How to play")/2, 175 - fm.getHeight()/2 + fm.getAscent());
			g.drawString("High scores", 225 - fm.stringWidth("High scores")/2, 425 - fm.getHeight()/2 + fm.getAscent());
			g.drawString("Exit", 575 - fm.stringWidth("Exit")/2, 425 - fm.getHeight()/2 + fm.getAscent());
			break;
		}
		
		g.setColor(new Color(255, 255, 255, 127));
		switch (clicked) {
		case 1:
			g.fillRect(104, 104, 242, 142);
			break;
		case 2:
			g.fillRect(454, 104, 242, 142);
			break;
		case 3:
			g.fillRect(104, 354, 242, 142);
			break;
		case 4:
			g.fillRect(454, 354, 242, 142);
			break;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		Point clickLocation = new Point(event.getX(), event.getY());
		
		switch (state) {
		case MAIN:
			if ((new Rectangle(100, 100, 250, 150)).contains(clickLocation)) { // Play
				clicked = 1;
			}
			else if ((new Rectangle(450, 100, 250, 150)).contains(clickLocation)) { // How to play
				clicked = 2;
			}
			else if ((new Rectangle(100, 350, 250, 150)).contains(clickLocation)) { // High score
				clicked = 3;
			}
			else if ((new Rectangle(450, 350, 250, 150)).contains(clickLocation)) { // Exit
				clicked = 4;
			}
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		Point clickLocation = new Point(event.getX(), event.getY());
		
		switch (state) {
		case MAIN:
			if (clicked == 1 && (new Rectangle(100, 100, 250, 150)).contains(clickLocation)) { // Play
				game.play();
			}
			else if (clicked == 2 && (new Rectangle(450, 350, 250, 150)).contains(clickLocation)) { // How to play
				
			}
			else if (clicked == 3 && (new Rectangle(450, 350, 250, 150)).contains(clickLocation)) { // High score
				
			}
			else if (clicked == 4 && (new Rectangle(450, 350, 250, 150)).contains(clickLocation)) { // Exit
				System.exit(0);
			}
			break;
		}
		clicked = 0;
	}

}
