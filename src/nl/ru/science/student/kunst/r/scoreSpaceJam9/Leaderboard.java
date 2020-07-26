package nl.ru.science.student.kunst.r.scoreSpaceJam9;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Leaderboard {
	
	private File file;
	private LinkedList<Integer> scores;
	private LinkedList<String> names;
	private Sprite sprite;

	public Leaderboard() {
		file = new File("res" + File.separator + "High scores.txt");
		scores = new LinkedList<>();
		names = new LinkedList<>();
		sprite = new Sprite("leaderboard");
		try {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				scores.add(reader.nextInt());
				names.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g, int x, int y) {
		sprite.draw(g, x, y);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN, 20));
		FontMetrics fm = g.getFontMetrics();
		
		int rank = 1;
		int previousScore = 0;
		for (int i = 0; i < Math.min(scores.size(), 5); i++) {
			if (scores.get(i) != previousScore) {
				rank = i + 1;
			}
			previousScore = scores.get(i);
			String rankString = rank + "";
			String name = names.get(i);
			String score = scores.get(i) + "";
			
			int lineY = y + 15 + 35 * i + (30 - fm.getHeight())/2 + fm.getAscent();
			
			g.drawString(rankString, x + 55 + (50 - fm.stringWidth(rankString))/2, lineY);
			g.drawString(name, x + 120, lineY);
			g.drawString(score, x + 495 + (50 - fm.stringWidth(score))/2, lineY);
		}
	}
	
	public void addScore(int score, String name) {
		ListIterator<Integer> scoreIterator = scores.listIterator();
		ListIterator<String> nameIterator = names.listIterator();
		
		boolean added = false;
		
		while (scoreIterator.hasNext()) {
			if (scoreIterator.next() <= score) {
				scoreIterator.previous();
				scoreIterator.add(score);
				nameIterator.add(name);
				added = true;
				break;
			}
			nameIterator.next();
		}
		
		if (!added) {
			names.add(name);
			scores.add(score);
		}
		
		try {
			FileWriter writer = new FileWriter(file);
			for (int i = 0; i < Math.min(scores.size(), 5); i++) {
				writer.write(scores.get(i) + " " + names.get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
