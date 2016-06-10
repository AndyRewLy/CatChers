package com.game.src.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search {
	
	public static String input = "";
	public static String user = "";
	public static int userId = 0;
	
	public Rectangle searchInput = new Rectangle(Game.WIDTH / 2 + 40, 150, 250, 25);
	public Rectangle searchButton = new Rectangle(Game.WIDTH / 2 + 300 , 150, 25, 25);
	public Rectangle backToMenu = new Rectangle(Game.WIDTH * 2 - 100, 20, 100, 50);
	public Rectangle house = new Rectangle(20, 200, 100, 50);
	public static boolean noUser = false;
	public static boolean searched = false;
	public static boolean profileVisible = false;
	public static int highestScore;
	public static int highestRank;
	
	public static boolean[] cats = new boolean[5];
	
	public String getInput(){
		return input;
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		Font fnt0 = new Font("arial", Font.BOLD, 16);
		Font fnt1 = new Font("arial", Font.BOLD, 12);
		
		if (noUser) {
			g.drawString("User Not Available", searchInput.x, searchInput.y + 50);
		}
		
		if (!searched) {
			g.drawString("Menu", backToMenu.x + 19, backToMenu.y + 30);
			g2d.draw(backToMenu);
			
			if (input != null) {
				g.drawString(input, searchInput.x, searchInput.y + 20);
			}
		
			g.setFont(fnt0);
			g.drawString("Search", searchInput.x - 60, searchInput.y + 20);
			g2d.draw(searchInput);
		
			g.setFont(fnt1);;
			g.drawString("Go", searchButton.x + 9, searchButton.y + 20);
			g2d.draw(searchButton);
		}
		else {
			profileVisible = true;
			
			g.setFont(fnt0);
			g.drawString("HIGH SCORE:" + highestScore, house.x + 19, house.y - 100);			
			g.drawString("HIGHEST RANK:" + highestRank, house.x + 19, house.y - 50);			
			
			Font fnt2 = new Font("arial", Font.BOLD, 20);
			g.setFont(fnt2);
			g.drawString("User: " + user, 50, 50);
			g.drawString("Menu", backToMenu.x + 19, backToMenu.y + 30);
			g2d.draw(backToMenu);
			
			if (cats[0]) {
				g.drawImage(Game.tex.blackCat, 50, Game.HEIGHT / 2 + 100, null);
			}
			else {
				g.drawImage(Game.tex.mysteryCat, 50, Game.HEIGHT / 2 + 100, null);
			}
			
			if (cats[1]) {
				g.drawImage(Game.tex.orangeCat, 150, Game.HEIGHT / 2 + 100, null);
			}
			else {
				g.drawImage(Game.tex.mysteryCat, 150, Game.HEIGHT / 2 + 100, null);
			}
			
			if (cats[2]) {
				g.drawImage(Game.tex.greenCat, 250, Game.HEIGHT / 2 + 100, null);
			}
			else {
				g.drawImage(Game.tex.mysteryCat, 250, Game.HEIGHT / 2 + 100, null);
			}
			
			if (cats[3]) {
				g.drawImage(Game.tex.blueCat, 350, Game.HEIGHT / 2 + 100, null);
			}
			else {
				g.drawImage(Game.tex.mysteryCat, 350, Game.HEIGHT / 2 + 100, null);
			}
			
			if (cats[4]) {
				g.drawImage(Game.tex.redCat, 450, Game.HEIGHT / 2 + 100, null);
			}
			else {
				g.drawImage(Game.tex.mysteryCat, 450, Game.HEIGHT / 2 + 100, null);
			}
			
		}
	}
	
	public void keyPressed(KeyEvent e){
		char key = e.getKeyChar();
		int keyInt = e.getKeyCode();
		
		if (keyInt == KeyEvent.VK_BACK_SPACE) {
			if(input.length() > 0) {
				input = input.substring(0, input.length()-1);
			}
		}
		else {
			if (input.length() < 32 && keyInt >= 65 && keyInt <= 90) {
				input = input + key;
			}
		}
		
	}
	
	public static void getSearchedUserCats() {
		Statement statement = null;
		ResultSet results = null;
		String query;
		String cat;
		
		query = "SELECT catName FROM Cats WHERE userName = '" + user + "';";
		
		try {
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				cat = results.getString(1);
				if (cat.equals("blackCat")) {
					cats[0] = true;
				}
				else if (cat.equals("orangeCat")) {
					cats[1] = true;
				}
				else if (cat.equals("greenCat")) {
					cats[2] = true;
				}
				else if (cat.equals("blueCat")) {
					cats[3] = true;
				}
				else if (cat.equals("redCat")) {
					cats[4] = true;
				}
						
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (results != null) {
				results.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	} 
	
	public static void mousePressed(MouseEvent e) {
		Statement statement;
		ResultSet results;
		String query;
		
		user = input;
		
		if (!profileVisible) {
			query = "SELECT userId, userName FROM UserData "
		      + "WHERE userName = '" + input + "';";
			
			try {
				statement = Game.conn.createStatement();
				results = statement.executeQuery(query);
					
				if (!results.isBeforeFirst()) {
					noUser = true;
				}
					
			
				while (results.next()) {
					userId = results.getInt(1);
					user = results.getString("userName");
					noUser = false;
					searched = true;
				}
							
				if (statement != null) {
					statement.close();
				}
				
				if (results != null) {
					results.close();
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		
			query = "SELECT highestScore, highestRank FROM UserProfile "
					+ "WHERE userId = " + userId + ";";
				
				try {
					statement = Game.conn.createStatement();
					results = statement.executeQuery(query);
					
					
					while (results.next()) {
						highestScore = results.getInt(1);
						highestRank = results.getInt(2);
					}
					
					if (statement != null) {
						statement.close();
					}
					
					if (results != null) {
						results.close();
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
				getSearchedUserCats();

		}
	}
}
