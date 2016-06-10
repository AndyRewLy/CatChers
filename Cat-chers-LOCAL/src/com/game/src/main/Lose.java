package com.game.src.main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.Statement;


public class Lose {

	public Rectangle playAgain = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);
	public Rectangle menuButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
	
	private Player p;
	private BufferedImage background;
	
	public static boolean statsUpdated = false;
	public static boolean highScoreChecked = false;
	public static boolean newCat = false;
	public int highestScore;
	public int highestRank;
	public int newRank;
	public int oldCoins;
	public int coinsGained;
	public int totalNewCoins;

	
	public Lose(Player p, BufferedImage background) {
		this.p = p;
		this.background = background;
	}
	
	public void render(Graphics g) {
		Statement statement = null;
		ResultSet results = null;
		String insert, query, update;
		
		g.drawImage(background, 0, 0, null);
		coinsGained = p.getScore()/100;
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		Font fnt2 = new Font("arial", Font.BOLD, 10);
		
		g.setFont(fnt2);
		g.drawString("Play Again", playAgain.x + 19, playAgain.y + 30);
		g2d.draw(playAgain);
		g.drawString("Menu", menuButton.x + 19, menuButton.y + 30);
		g2d.draw(menuButton);
		g.setFont(fnt1);
		g.drawString("Score: " + p.getScore(), 250, 150);
		g.drawString("You've Gained " + coinsGained + " Cat Coins", 150, 200);
		
		if (p.getScore() > 1000 && Game.availableCats[1] == 0) {
			newCat = true;
			g.drawString("New Cat Gained!", 100, 100);
			
			Game.availableCats[1] = 1;
			
			insert = "INSERT INTO Cats (userId, userName, catName)"
		 			   + " VALUES ((SELECT userId FROM UserData WHERE userName = '" + Game.currUser + "'),"
		 			   + "        '" + Game.currUser + "', 'orangeCat');";
			
			try {
	 			statement = Game.conn.createStatement();
	 			statement.executeUpdate(insert);
	 		
	 			if (statement != null) {
	 				statement.close();
	 			}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}	
		else if (p.getScore() > 2000 && Game.availableCats[2] == 0) {
			newCat = true;
			Game.availableCats[2] = 1;
			
			insert = "INSERT INTO Cats (userId, userName, catName)"
		 			   + " VALUES ((SELECT userId FROM UserData WHERE userName = '" + Game.currUser + "'),"
		 			   + "        '" + Game.currUser + "', 'greenCat');";
			
			try {
	 			statement = Game.conn.createStatement();
	 			statement.executeUpdate(insert);
	 		
	 			if (statement != null) {
	 				statement.close();
	 			}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		}
		else if (p.getScore() > 3000 && Game.availableCats[3] == 0) {
			newCat = true;
			Game.availableCats[3] = 1;
			
			insert = "INSERT INTO Cats (userId, userName, catName)"
		 			   + " VALUES ((SELECT userId FROM UserData WHERE userName = '" + Game.currUser + "'),"
		 			   + "        '" + Game.currUser + "', 'blueCat');";
			
			try {
	 			statement = Game.conn.createStatement();
	 			statement.executeUpdate(insert);
	 		
	 			if (statement != null) {
	 				statement.close();
	 			}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		}
		else if (p.getScore() > 4000 && Game.availableCats[4] == 0) {
			newCat = true;
			Game.availableCats[4] = 1;
			
			insert = "INSERT INTO Cats (userId, userName, catName)"
		 			   + " VALUES ((SELECT userId FROM UserData WHERE userName = '" + Game.currUser + "'),"
		 			   + "        '" + Game.currUser + "', 'redCat');";
			
			try {
	 			statement = Game.conn.createStatement();
	 			statement.executeUpdate(insert);
	 		
	 			if (statement != null) {
	 				statement.close();
	 			}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
		}
		if (newCat) {
			g.drawString("New Cat Gained!", 200, 100);
		}
		
		if (!highScoreChecked) {
			checkHighScores();
			highScoreChecked = true;
		}
		
		if (!statsUpdated) {
			//Get Coins
		
			query = "SELECT totalCatCoins FROM UserProfile up JOIN UserData ud ON ud.userId = up.userId "
				  + "WHERE ud.userName = '" + Game.currUser + "';";
			query = "SELECT totalCatCoins FROM UserProfile"
				  + "WHERE userId = " + Game.currUserId + ";";
			try {
				if (statement != null) {
					statement.close();
				}
				
				statement = Game.conn.createStatement();
				results = statement.executeQuery(query);
						
				while (results.next()) {
					oldCoins = results.getInt(1);
				}
							
				if (statement != null) {
					statement.close();
				}
				
				if (results != null) {
					results.close();
				}
			}
			catch (Exception e) {
			}
			//Get Highest Score
			
			query = "SELECT highestScore FROM UserProfile up JOIN UserData ud ON ud.userId = up.userId "
			      + "WHERE ud.userName = '" + Game.currUserId + "';";

			query = "SELECT highestScore FROM UserProfile"
					  + "WHERE userId = " + Game.currUserId + ";";
			
			try {
				statement = Game.conn.createStatement();
				results = statement.executeQuery(query);
						
				while (results.next()) {
					highestScore = results.getInt(1);
				}
							
				if (statement != null) {
					statement.close();
				}
				
				if (results != null) {
					results.close();
				}
			}
			catch (Exception e) {
			}
			
			// Get Highest Rank
			query = "SELECT highestRank FROM UserProfile up JOIN UserData ud ON ud.userId = up.userId "
				      + "WHERE ud.userName = '" + Game.currUserId + "';";

			query = "SELECT highestRank FROM UserProfile"
					  + "WHERE userId = " + Game.currUserId + ";";
			
			try {
				statement = Game.conn.createStatement();
				results = statement.executeQuery(query);
						
				while (results.next()) {
					highestRank = results.getInt(0);
				}
							
				if (statement != null) {
					statement.close();
				}
				
				if (results != null) {
					results.close();
				}
			}
			catch (Exception e) {
			}
			
			//Update Highest Score
			
			if (highestScore < p.getScore()) {
				update = "UPDATE UserProfile"
						+ " SET highestScore = " + p.getScore() 
						+ " WHERE userId = '" + Game.currUserId + "';";
				try {
					statement = Game.conn.createStatement();
					statement.executeUpdate(update);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			
			//Update Coins
			
			totalNewCoins = oldCoins + coinsGained;
			
			update = "UPDATE UserProfile"
				   + " SET totalCatCoins = " + totalNewCoins + " "
				   + " WHERE userId = " + Game.currUserId + ";";
			try {
				statement = Game.conn.createStatement();
				statement.executeUpdate(update);
				
				if (statement != null) {
					statement.close();
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		
			//Update Rank
			
			if (highestRank < newRank) {
				update = "UPDATE UserProfile"
						+ " SET highestRank = " + newRank 
						+ " WHERE userId = '" + Game.currUserId + "';";
				try {
					statement = Game.conn.createStatement();
					statement.executeUpdate(update);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			
			statsUpdated = true;
			
		}		
	}
	
	public void checkHighScores() {
		Statement statement = null;
		ResultSet results = null;
		String query, insert, update;
		int rankAbove = 0, rankBelow = 0;
		boolean firstScore = false;
		
		query = null;
		insert = null;
		
		query = "SELECT min.min, max.max " +
				" FROM (SELECT MIN(rank) as min FROM HighScores WHERE " + p.getScore() + " > score) min" +
				" JOIN (SELECT MAX(rank) as max FROM HighScores WHERE " + p.getScore() + " < score) max;";
		
		try {
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			if (!results.isBeforeFirst()) {
			}
			
			while (results.next()) {
				rankBelow = results.getInt(1);
				rankAbove = results.getInt(2);
			}
			
			if (rankBelow == 0 && rankAbove == 0) {
				firstScore = true;
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
		
		if (firstScore) {
			newRank = 1;
			insert = "INSERT INTO HighScores (userId, userName, score, rank)" +
		             " VALUES (" + Game.currUserId + ",'" + Game.currUser + "'," + p.getScore() + "," + "1);";
			
			try {
				statement = Game.conn.createStatement();
				statement.executeUpdate(insert);
				
				if (statement != null) {
					statement.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			if (rankAbove != 0 && rankBelow == 0) {
				newRank = rankAbove + 1;
				
				update = "UPDATE HighScores SET rank = rank + 1 WHERE rank > " + newRank;
				
				try {
					if (statement != null) {
						statement.close();
					}
					
					statement = Game.conn.createStatement();
					statement.executeUpdate(update);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				insert = "INSERT INTO HighScores (userId, userName, score, rank)" +
						 " VALUES (" + Game.currUserId + ",'" + Game.currUser + "'," + p.getScore() + "," + newRank + ");";
				
				try {
					if (statement != null) {
						statement.close();
					}
					
					statement = Game.conn.createStatement();
					statement.executeUpdate(insert);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (rankAbove == 0 && rankBelow != 0) {
				newRank = rankBelow - 1;
				
				update = "UPDATE HighScores SET rank = rank + 1 WHERE rank > " + newRank;
				
				try {
					if (statement != null) {
						statement.close();
					}
					
					statement = Game.conn.createStatement();
					statement.executeUpdate(update);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				newRank++;
				insert = "INSERT INTO HighScores (userId, userName, score, rank)" +
						 " VALUES (" + Game.currUserId + ",'" + Game.currUser + "'," + p.getScore() + "," + newRank + ");";
				
				try {
					if (statement != null) {
						statement.close();
					}
					
					statement = Game.conn.createStatement();
					statement.executeUpdate(insert);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				newRank = rankBelow - 1;
				
				update = "UPDATE HighScores SET rank = rank + 1 WHERE rank > " + newRank;
				
				try {
					if (statement != null) {
						statement.close();
					}
					
					statement = Game.conn.createStatement();
					statement.executeUpdate(update);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				newRank++;
				insert = "INSERT INTO HighScores (userId, userName, score, rank)" +
						 " VALUES (" + Game.currUserId + ",'" + Game.currUser + "'," + p.getScore() + "," + newRank + ");";
				try {
					if (statement != null) {
						statement.close();
					}
					
					statement = Game.conn.createStatement();
					statement.executeUpdate(insert);
					
					if (statement != null) {
						statement.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
