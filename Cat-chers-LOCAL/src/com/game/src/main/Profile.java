package com.game.src.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.Statement;

import com.game.src.main.Game;

public class Profile {

	public Rectangle house = new Rectangle(20, 200, 100, 50);
	public Rectangle backToMenu = new Rectangle(Game.WIDTH * 2 - 100, 20, 100, 50);
	public String query;
	public int highScore, highestRank;
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Statement statement = null;
		ResultSet results = null;
		
		query = "SELECT highestScore, highestRank FROM UserProfile WHERE userId = " + Game.currUserId + ";";
		
		try {
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);	

			if (results.next()) {
				highScore = results.getInt(1);
				highestRank = results.getInt(2);
			}
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		Font fnt0 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt0);
		g.drawString("HIGH SCORE: " + highScore, house.x + 19, house.y - 100);			
		g.drawString("HIGHEST RANK:" + highestRank, house.x + 19, house.y - 50);			
		
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		g.drawString("House", house.x + 19, house.y + 30);			
		g2d.draw(house);

		g.drawString("Menu", backToMenu.x + 19, backToMenu.y + 30);
		g2d.draw(backToMenu);
		
	}
}
