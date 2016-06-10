package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.Statement;

public class HighScore {

	public Rectangle backToMenu = new Rectangle(Game.WIDTH * 2 - 100, 20, 100, 50);
	public int[] score = new int[10];
	public String[] user = new String[10];
	public static boolean scoresRead = false;
	public int index = 0;
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Font fnt0 = new Font("arial",Font.BOLD, 16);
		int val;
		
		if (!scoresRead) {
			getScores();
		}
		else {
			g.setFont(fnt0);
			g.setColor(Color.BLACK);
			g.drawString("Menu", backToMenu.x + 19, backToMenu.y + 30);
			g2d.draw(backToMenu);
			g.drawString("HIGHSCORES", Game.WIDTH / 2 + 50, 50);
			for (int i = 0; i < index; i++) {
				val = i + 1;
				g.drawString(user[i], 200, i * 25 + 100);
				g.drawString("" + score[i] + "", 150, i * 25 + 100);
				g.drawString("" + val + "", 100, i * 25 + 100);
			}
	
		}
	}
	
	public void getScores() {
		Statement statement = null;
		ResultSet results = null;
		String query = null;
		index = 0;
		
		query = "SELECT score, userName FROM HighScores WHERE rank < 10 ORDER BY rank ASC";
				
		try {
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
					
			while (results.next()) {
				score[index] = results.getInt(1);
				user[index] = results.getString(2);
				index++;
			}

			scoresRead = true;
			
			statement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
