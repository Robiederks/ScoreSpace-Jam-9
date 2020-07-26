package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {

	private ArrayList<Collectable> items;
	private ArrayList<Integer> amounts;
	
	private int selected;
	
	public Inventory() {
		items = new ArrayList<>();
		amounts = new ArrayList<>();
		selected = -1;
	}
	
	public void addItem(Collectable item) {
		if (items.contains(item)) {
			amounts.set(items.indexOf(item), amounts.get(items.indexOf(item)) + 1);
		}
		else {
			amounts.add(1);
			items.add(item);
		}
	}
	
	public void useItem(int x, int y) {
		if (selected >= 0 && selected < items.size()) {
			if (amounts.get(selected) > 0) {
				amounts.set(selected, amounts.get(selected) - 1);
				items.get(selected).use(x, y);
			}
		}
		else {
			System.out.println("Illegal item index!");
		}
	}
	
	public void render(Graphics g) {
		int x = 24;
		int y = Game.HEIGHT - 48;
		
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < items.size(); i++) {
			items.get(i).getSprite().draw(g, x, y);
			g.drawString(amounts.get(i).toString(), x + 24, y + 24);
			x += 48;
		}
		
		if (selected > -1) {
			g.drawRect(20 + 48 * selected, y - 4, 36, 36);
		}
		
	}

	public void keyTyped(char key) {
		if (!items.isEmpty()) {
			switch (key) {
			case 'q':
				selected--;
				selected %= items.size();
				break;
			case 'e':
				selected++;
				selected %= items.size();
				break;
			}
		}
	}

}
