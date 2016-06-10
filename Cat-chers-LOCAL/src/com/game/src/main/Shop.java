package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Shop {

	public Rectangle cushionTab = new Rectangle(0, 150, 100, 50);
	public Rectangle wallTab = new Rectangle(100, 150, 100, 50);
	public Rectangle carpetTab = new Rectangle(200, 150, 100, 50);
	public Rectangle clockTab = new Rectangle(300, 150, 100, 50);
	public Rectangle backToMenu = new Rectangle(Game.WIDTH * 2 - 100, 20, 100, 50);
	
	public String[] walls = new String[6];
	public static boolean[] wallsT = new boolean[6];
	
	public String[] carpet = new String[6];
	public static boolean[] carpetT = new boolean[6];
	
	public String[] cushion = new String[5];
	public static boolean[] cushionT = new boolean[5];
	
	public String[] clock = new String[5];
	public static boolean[] clockT = new boolean[5];
	
	public boolean init = true;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (init) {
			for (int i = 0; i < 6; i++) {
				wallsT[i] = false;
				carpetT[i] = false;
			}
			for (int i = 0; i < 5; i++) {
				cushionT[i] = false;
				clockT[i] =false;
			}
			init = false;
		}
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.BLACK);
		g.drawString("SHOP", Game.WIDTH / 2 + 60, 100);
	
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		g.drawString("Cushions", cushionTab.x + 5, cushionTab.y + 30);			
		g2d.draw(cushionTab);
		g.drawString("Walls", wallTab.x + 19, wallTab.y + 30);
		g2d.draw(wallTab);
		g.drawString("Carpets", carpetTab.x + 19, carpetTab.y + 30);
		g2d.draw(carpetTab);
		g.drawString("Clocks", clockTab.x + 19, clockTab.y + 30);
		g2d.draw(clockTab);
		g.drawString("Menu", backToMenu.x + 19, backToMenu.y + 30);
		g2d.draw(backToMenu);
		g.drawString("You have: " + getCoins() + " coins", clockTab.x + 119, clockTab.y + 30);
		
		if (Game.ShopTab == Game.SHOP_TAB.WALLS) {
			checkIfWallBought();
			drawWallTab(g);
		}
		else if (Game.ShopTab == Game.SHOP_TAB.CUSHIONS) {
			checkIfCushionBought();
			drawCushionTab(g);
		}
		else if (Game.ShopTab == Game.SHOP_TAB.CARPETS) {
			checkIfCarpetBought();
			drawCarpetTab(g);
		}
		else if (Game.ShopTab == Game.SHOP_TAB.CLOCKS) {
			checkIfClockBought();
			drawClockTab(g);
		}
	}
	
	public void drawWallTab(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		
		Rectangle wallBlue = new Rectangle (0, 200, Game.WIDTH * 2, 50);
		Rectangle wallOrange = new Rectangle (0, 250, Game.WIDTH * 2, 50);
		Rectangle wallFloral = new Rectangle (0, 300, Game.WIDTH * 2, 50);
		Rectangle wallStripes = new Rectangle (0, 350, Game.WIDTH * 2, 50);
		Rectangle wallCatPaper = new Rectangle (0, 400, Game.WIDTH * 2, 50);
		
		g.setColor(Color.BLACK);
		Rectangle buyButton1 = new Rectangle(Game.WIDTH * 2 - 100, 205, 40, 40);
		Rectangle buyButton2 = new Rectangle(Game.WIDTH * 2 - 100, 255, 40, 40);
		Rectangle buyButton3 = new Rectangle(Game.WIDTH * 2 - 100, 305, 40, 40);
		Rectangle buyButton4 = new Rectangle(Game.WIDTH * 2 - 100, 355, 40, 40);
		Rectangle buyButton5 = new Rectangle(Game.WIDTH * 2 - 100, 405, 40, 40);

		g.setFont(fnt1);
		g.drawImage(Game.shopTab, wallBlue.x, wallBlue.y, null);
		g.drawString("Blue Wall", wallBlue.x + 10, wallBlue.y + 30);
		g.drawString("Price: 5", wallBlue.x + 250, wallBlue.y + 30);
		if (wallsT[1]) {
			g.drawString("Bought" , buyButton1.x + 2, buyButton1.y + 30);
		}
		else {
			g.drawString("Buy", buyButton1.x + 2, buyButton1.y + 30);
		}
		
		g.drawImage(Game.shopTab, wallOrange.x, wallOrange.y, null);
		g.drawString("Orange Wall", wallOrange.x + 10, wallOrange.y + 30);
		g.drawString("Price: 5", wallOrange.x + 250, wallOrange.y + 30);
		if (wallsT[2]) {
			g.drawString("Bought" , buyButton2.x + 2, buyButton2.y + 30);
		}
		else {
			g.drawString("Buy", buyButton2.x + 2, buyButton2.y + 30);
		}
		
		g.drawImage(Game.shopTab, wallFloral.x, wallFloral.y, null);
		g.drawString("Floral Wall", wallFloral.x + 10, wallFloral.y + 30);
		g.drawString("Price: 10", wallFloral.x + 250, wallFloral.y + 30);
		if (wallsT[3]) {
			g.drawString("Bought" , buyButton3.x + 2, buyButton3.y + 30);
		}
		else {
			g.drawString("Buy", buyButton3.x + 2, buyButton3.y + 30);
		}
		
		g.drawImage(Game.shopTab, wallStripes.x, wallStripes.y, null);
		g.drawString("Striped Wall", wallStripes.x + 10, wallStripes.y + 30);
		g.drawString("Price: 10", wallStripes.x + 250, wallStripes.y + 30);
		if (wallsT[4]) {
			g.drawString("Bought" , buyButton4.x + 2, buyButton4.y + 30);
		}
		else {
			g.drawString("Buy", buyButton4.x + 2, buyButton4.y + 30);
		}
		
		g.drawImage(Game.shopTab, wallCatPaper.x, wallCatPaper.y, null);
		g.drawString("Cat Paper Wall", wallCatPaper.x + 10, wallCatPaper.y + 30);
		g.drawString("Price: 25", wallCatPaper.x + 250, wallCatPaper.y + 30);
		if (wallsT[5]) {
			g.drawString("Bought" , buyButton5.x + 2, buyButton5.y + 30);		
		}
		else {
			g.drawString("Buy",  buyButton5.x + 2,  buyButton5.y + 30);
		}
	}
	
	public void drawCushionTab(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		
		Rectangle cushWhite= new Rectangle (0, 200, Game.WIDTH * 2, 50);
		Rectangle cushBlue = new Rectangle (0, 250, Game.WIDTH * 2, 50);
		Rectangle cushRed = new Rectangle (0, 300, Game.WIDTH * 2, 50);
		Rectangle cushTree = new Rectangle (0, 350, Game.WIDTH * 2, 50);
		Rectangle cushCat = new Rectangle (0, 400, Game.WIDTH * 2, 50);
		
		g.setColor(Color.BLACK);
		Rectangle buyButton1 = new Rectangle(Game.WIDTH * 2 - 100, 205, 40, 40);
		Rectangle buyButton2 = new Rectangle(Game.WIDTH * 2 - 100, 255, 40, 40);
		Rectangle buyButton3 = new Rectangle(Game.WIDTH * 2 - 100, 305, 40, 40);
		Rectangle buyButton4 = new Rectangle(Game.WIDTH * 2 - 100, 355, 40, 40);
		Rectangle buyButton5 = new Rectangle(Game.WIDTH * 2 - 100, 405, 40, 40);

		g.setFont(fnt1);
		g.drawImage(Game.shopTab, cushWhite.x, cushWhite.y, null);
		g.drawString("White Cushion", cushWhite.x + 10, cushWhite.y + 30);
		g.drawString("Price: 5", cushWhite.x + 250, cushWhite.y + 30);
		if (cushionT[0]) {
			g.drawString("Bought", buyButton1.x + 2, buyButton1.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton1.x + 2, buyButton1.y + 30);
		}
		
		g.drawImage(Game.shopTab, cushBlue.x, cushBlue.y, null);
		g.drawString("Blue Cushion", cushBlue.x + 10, cushBlue.y + 30);
		g.drawString("Price: 10", cushBlue.x + 250, cushBlue.y + 30);
		if (cushionT[1]) {
			g.drawString("Bought", buyButton2.x + 2, buyButton2.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton2.x + 2, buyButton2.y + 30);
		}
		
		g.drawImage(Game.shopTab, cushRed.x, cushRed.y, null);
		g.drawString("Red Cushion", cushRed.x + 10, cushRed.y + 30);
		g.drawString("Price: 10", cushRed.x + 250, cushRed.y + 30);
		if (cushionT[2]) {
			g.drawString("Bought", buyButton3.x + 2, buyButton3.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton3.x + 2, buyButton3.y + 30);
		}
		
		g.drawImage(Game.shopTab, cushTree.x, cushTree.y, null);
		g.drawString("Tree Design Cushion", cushTree.x + 10, cushTree.y + 30);
		g.drawString("Price: 25", cushTree.x + 250, cushTree.y + 30);
		if (cushionT[3]) {
			g.drawString("Bought", buyButton4.x + 2, buyButton4.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton4.x + 2, buyButton4.y + 30);
		}
		
		g.drawImage(Game.shopTab, cushCat.x, cushCat.y, null);
		g.drawString("Cat Cushion", cushCat.x + 10, cushCat.y + 30);
		g.drawString("Price: 50", cushCat.x + 250, cushCat.y + 30);
		if (cushionT[4]) {
			g.drawString("Bought", buyButton5.x + 2, buyButton5.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton5.x + 2, buyButton5.y + 30);		
		}
		
	}
	
	public void drawCarpetTab(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		
		Rectangle carpetNavy = new Rectangle (0, 200, Game.WIDTH * 2, 50);
		Rectangle carpetMaroon = new Rectangle (0, 250, Game.WIDTH * 2, 50);
		Rectangle carpetLinoleum = new Rectangle (0, 300, Game.WIDTH * 2, 50);
		Rectangle carpetWood = new Rectangle (0, 350, Game.WIDTH * 2, 50);
		Rectangle carpetCat = new Rectangle (0, 400, Game.WIDTH * 2, 50);
		
		g.setColor(Color.BLACK);
		Rectangle buyButton1 = new Rectangle(Game.WIDTH * 2 - 100, 205, 40, 40);
		Rectangle buyButton2 = new Rectangle(Game.WIDTH * 2 - 100, 255, 40, 40);
		Rectangle buyButton3 = new Rectangle(Game.WIDTH * 2 - 100, 305, 40, 40);
		Rectangle buyButton4 = new Rectangle(Game.WIDTH * 2 - 100, 355, 40, 40);
		Rectangle buyButton5 = new Rectangle(Game.WIDTH * 2 - 100, 405, 40, 40);

		g.setFont(fnt1);
		g.drawImage(Game.shopTab, carpetNavy.x, carpetNavy.y, null);
		g.drawString("Navy Blue Carpet", carpetNavy.x + 10, carpetNavy.y + 30);
		g.drawString("Price: 5", carpetNavy.x + 250, carpetNavy.y + 30);
		if (carpetT[1]) {
			g.drawString("Bought", buyButton1.x + 2,  buyButton1.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton1.x + 2, buyButton1.y + 30);
		}
		
		g.drawImage(Game.shopTab, carpetMaroon.x, carpetMaroon.y, null);
		g.drawString("Maroon Carpet", carpetMaroon.x + 10, carpetMaroon.y + 30);
		g.drawString("Price: 5", carpetMaroon.x + 250, carpetMaroon.y + 30);
		if (carpetT[2]) {
			g.drawString("Bought", buyButton2.x + 2,  buyButton2.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton2.x + 2, buyButton2.y + 30);
		}
		
		g.drawImage(Game.shopTab, carpetLinoleum.x, carpetLinoleum.y, null);
		g.drawString("Linoleum", carpetLinoleum.x + 10, carpetLinoleum.y + 30);
		g.drawString("Price: 10", carpetLinoleum.x + 250, carpetLinoleum.y + 30);
		if (carpetT[3]) {
			g.drawString("Bought", buyButton3.x + 2,  buyButton3.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton3.x + 2, buyButton3.y + 30);
		}
		
		g.drawImage(Game.shopTab, carpetWood.x, carpetWood.y, null);
		g.drawString("Wooden Floor", carpetWood.x + 10, carpetWood.y + 30);
		g.drawString("Price: 10", carpetWood.x + 250, carpetWood.y + 30);
		if (carpetT[4]) {
			g.drawString("Bought", buyButton4.x + 2,  buyButton4.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton4.x + 2, buyButton4.y + 30);
		}
		
		g.drawImage(Game.shopTab, carpetCat.x, carpetCat.y, null);
		g.drawString("Cat Tiles", carpetCat.x + 10, carpetCat.y + 30);
		g.drawString("Price: 25", carpetCat.x + 250, carpetCat.y + 30);
		if (carpetT[5]) {
			g.drawString("Bought", buyButton5.x + 2,  buyButton5.y + 30);	
		}
		else {
			g.drawString("Buy" , buyButton5.x + 2, buyButton5.y + 30);		
		}
	}
	
	public void drawClockTab(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		
		Rectangle clockBlue = new Rectangle (0, 200, Game.WIDTH * 2, 50);
		Rectangle clockRed = new Rectangle (0, 250, Game.WIDTH * 2, 50);
		Rectangle clockBlack = new Rectangle (0, 300, Game.WIDTH * 2, 50);
		Rectangle clockWhite = new Rectangle (0, 350, Game.WIDTH * 2, 50);
		Rectangle clockCat = new Rectangle (0, 400, Game.WIDTH * 2, 50);
		
		g.setColor(Color.BLACK);
		Rectangle buyButton1 = new Rectangle(Game.WIDTH * 2 - 100, 205, 40, 40);
		Rectangle buyButton2 = new Rectangle(Game.WIDTH * 2 - 100, 255, 40, 40);
		Rectangle buyButton3 = new Rectangle(Game.WIDTH * 2 - 100, 305, 40, 40);
		Rectangle buyButton4 = new Rectangle(Game.WIDTH * 2 - 100, 355, 40, 40);
		Rectangle buyButton5 = new Rectangle(Game.WIDTH * 2 - 100, 405, 40, 40);

		g.setFont(fnt1);
		g.drawImage(Game.shopTab, clockBlue.x, clockBlue.y, null);
		g.drawString("Blue Clock", clockBlue.x + 10, clockBlue.y + 30);
		g.drawString("Price: 5", clockBlue.x + 250, clockBlue.y + 30);
		if (clockT[0]) {
			g.drawString("Bought", buyButton1.x + 2, buyButton1.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton1.x + 2, buyButton1.y + 30);
		}
		
		g.drawImage(Game.shopTab, clockRed.x, clockRed.y, null);
		g.drawString("Red Clock", clockRed.x + 10, clockRed.y + 30);
		g.drawString("Price: 5", clockRed.x + 250, clockRed.y + 30);
		if (clockT[1]) {
			g.drawString("Bought", buyButton2.x + 2, buyButton2.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton2.x + 2, buyButton2.y + 30);
		}
		
		g.drawImage(Game.shopTab, clockBlack.x, clockBlack.y, null);
		g.drawString("Black Clock", clockBlack.x + 10, clockBlack.y + 30);
		g.drawString("Price: 15", clockBlack.x + 250, clockBlack.y + 30);
		if (clockT[2]) {
			g.drawString("Bought", buyButton3.x + 2, buyButton3.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton3.x + 2, buyButton3.y + 30);
		}
		
		g.drawImage(Game.shopTab, clockWhite.x, clockWhite.y, null);
		g.drawString("White Clock", clockWhite.x + 10, clockWhite.y + 30);
		g.drawString("Price: 15", clockWhite.x + 250, clockWhite.y + 30);
		if (clockT[3]) {
			g.drawString("Bought", buyButton4.x + 2, buyButton4.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton4.x + 2, buyButton4.y + 30);
		}
		
		g.drawImage(Game.shopTab, clockCat.x, clockCat.y, null);
		g.drawString("Cat Clock", clockCat.x + 10, clockCat.y + 30);
		g.drawString("Price: 30", clockCat.x + 250, clockCat.y + 30);
		if (clockT[4]) {
			g.drawString("Bought", buyButton5.x + 2, buyButton5.y + 30);
		}
		else {
			g.drawString("Buy" , buyButton5.x + 2, buyButton5.y + 30);		
		}
	}
	
	public void checkIfWallBought() {
		Statement statement = null;
		ResultSet results = null;
		String query;
	
		query = "SELECT * FROM Inventory i " + 
		        "JOIN Shop s ON s.itemId = i.itemId " + 
				"WHERE userId = " + Game.currUserId + " AND s.category = 'wall';";
		
		try {
			
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				if (results.getString("decorationName").equals("Wall Color - White")) {
				   wallsT[0] = true;
				}
				else if (results.getString("decorationName").equals("Wall Color - Blue")) {
					wallsT[1] = true;
				}
				else if (results.getString("decorationName").equals("Wall Color - Orange")) {
					wallsT[2] = true;
				}
				else if (results.getString("decorationName").equals("Wall Color - Floral")) {
					wallsT[3] = true;
				}
				else if (results.getString("decorationName").equals("Wall Color - Stripes")) {
					wallsT[4] = true;
				}
				else if (results.getString("decorationName").equals("Wall Color - Cat Paper")) {
					wallsT[5] = true;
				}
			}
			
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkIfCushionBought() {
		Statement statement = null;
		ResultSet results = null;
		String query;
		
		query = "SELECT * FROM Inventory i " + 
		        "JOIN Shop s ON s.itemId = i.itemId " + 
				"WHERE userId = " + Game.currUserId + " AND s.category = 'cushion';";
		
		try {
			
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				if (results.getString("decorationName").equals("White Cushion")) {
				   cushionT[0] = true;
				}
				else if (results.getString("decorationName").equals("Blue Cushion")) {
					cushionT[1] = true;
				}
				else if (results.getString("decorationName").equals("Red Cushion")) {
					cushionT[2] = true;
				}
				else if (results.getString("decorationName").equals("Tree Design Cushion")) {
					cushionT[3] = true;
				}
				else if (results.getString("decorationName").equals("Cat Cushion")) {
					cushionT[4] = true;
				}
			}
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkIfCarpetBought() {
		Statement statement = null;
		ResultSet results = null;
		String query;
		
		query = "SELECT * FROM Inventory i " + 
		        "JOIN Shop s ON s.itemId = i.itemId " + 
				"WHERE userId = " + Game.currUserId + " AND s.category = 'carpet';";
		
		try {
			
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				if (results.getString("decorationName").equals("White Carpet")) {
				   carpetT[0] = true;
				}
				else if (results.getString("decorationName").equals("Navy Blue Carpet")) {
					carpetT[1] = true;
				}
				else if (results.getString("decorationName").equals("Maroon Carpet")) {
					carpetT[2] = true;
				}
				else if (results.getString("decorationName").equals("Linoleum Carpet")) {
					carpetT[3] = true;
				}
				else if (results.getString("decorationName").equals("Wood Carpet")) {
					carpetT[4] = true;
				}
				else if (results.getString("decorationName").equals("Cat Tiles")) {
					carpetT[5] = true;
				}
			}
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkIfClockBought() {
		Statement statement = null;
		ResultSet results = null;
		String query;
		
		query = "SELECT * FROM Inventory i " + 
		        "JOIN Shop s ON s.itemId = i.itemId " + 
				"WHERE userId = " + Game.currUserId + " AND s.category = 'clock';";
		
		try {
			
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				if (results.getString("decorationName").equals("Clock - Blue")) {
				   clockT[0] = true;
				}
				else if (results.getString("decorationName").equals("Clock - Red")) {
					clockT[1] = true;
				}
				else if (results.getString("decorationName").equals("Clock - Black")) {
					clockT[2] = true;
				}
				else if (results.getString("decorationName").equals("Clock - White")) {
					clockT[3] = true;
				}
				else if (results.getString("decorationName").equals("Clock - Cat")) {
					clockT[4] = true;
				}
			}
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static int getCoins() {
		Statement statement = null;
		ResultSet results = null;
		String query;
		int usersCoins = 0;
		
		query = "SELECT totalCatCoins FROM UserProfile " +  
				"WHERE userId = " + Game.currUserId + ";";
		
		try {
			
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				usersCoins = results.getInt(1);
			}
			
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return usersCoins;
		
	}
	
	public static void setCoins(int coins) {
		Statement statement = null;
		String update;
		
		update = "UPDATE UserProfile SET totalCatCoins = " + coins + " WHERE userId = " + Game.currUserId + ";";
		
		try {
			
			statement = Game.conn.createStatement();
			statement.executeUpdate(update);
			
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addIntoInv(int itemId) {
		Statement statement = null;
		String insert;
		
		insert = "INSERT INTO Inventory (userId, userName, itemId, inUse) " +
		         "VALUES (" + Game.currUserId + ",'" + Game.currUser + "'," + itemId + ",0);";
	  
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
	
	public static void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.ShopTab == Game.SHOP_TAB.WALLS) {
			if (mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2 - 60) {
				if (my >= 205 && my <= 245 && !wallsT[1]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(2);
					}
				}
				else if (my >= 255 && my <= 295 && !wallsT[2]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(3);
					}
				}
				else if (my >= 305 && my <= 345 && !wallsT[3]) {
					if (getCoins() >= 10) {
						setCoins(getCoins() - 10);
						addIntoInv(4);
					}
				}
				else if (my >= 355 && my <= 395 && !wallsT[4]) {
					if (getCoins() >= 10) {
						setCoins(getCoins() - 10);
						addIntoInv(5);
					}
				}
				else if (my >= 405 && my <= 445 && !wallsT[5]) {
					if (getCoins() >= 25) {
						setCoins(getCoins() - 25);
						addIntoInv(6);
					}
				}
			}
		}
		else if (Game.ShopTab == Game.SHOP_TAB.CUSHIONS) {
			if (mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2 - 60) {
				if (my >= 205 && my <= 245 && !cushionT[0]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(7);
					}
				}
				else if (my >= 255 && my <= 295 && !cushionT[1]) {
					if (getCoins() >= 10) {
						setCoins(getCoins() - 10);
						addIntoInv(8);
					}
				}
				else if (my >= 305 && my <= 345 && !cushionT[2]) {
					if (getCoins() >= 10) {
						setCoins(getCoins() - 10);
						addIntoInv(9);
					}
				}
				else if (my >= 355 && my <= 395 && !cushionT[3]) {
					if (getCoins() >= 25) {
						setCoins(getCoins() - 25);
						addIntoInv(10);
					}
				}
				else if (my >= 405 && my <= 445 && !cushionT[4]) {
					if (getCoins() >= 50) {
						setCoins(getCoins() - 50);
						addIntoInv(11);
					}
				}
			}
		}
		else if (Game.ShopTab == Game.SHOP_TAB.CARPETS) {
			if (mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2 - 60) {
				if (my >= 205 && my <= 245 && !carpetT[1]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(13);
					}
				}
				else if (my >= 255 && my <= 295 && !carpetT[2]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(14);
					}
				}
				else if (my >= 305 && my <= 345 && !carpetT[3]) {
					if (getCoins() >= 10) {
						setCoins(getCoins() - 10);
						addIntoInv(15);
					}
				}
				else if (my >= 355 && my <= 395 && !carpetT[4]) {
					if (getCoins() >= 10) {
						setCoins(getCoins() - 10);
						addIntoInv(16);
					}
				}
				else if (my >= 405 && my <= 445 && !carpetT[5]) {
					if (getCoins() >= 25) {
						setCoins(getCoins() - 25);
						addIntoInv(17);
					}
				}
			}
		}
		else if (Game.ShopTab == Game.SHOP_TAB.CLOCKS) {
			if (mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2 - 60) {
				if (my >= 205 && my <= 245 && !clockT[0]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(18);
					}
				}
				else if (my >= 255 && my <= 295 && !clockT[1]) {
					if (getCoins() >= 5) {
						setCoins(getCoins() - 5);
						addIntoInv(19);
					}
				}
				else if (my >= 305 && my <= 345 && !clockT[2]) {
					if (getCoins() >= 15) {
						setCoins(getCoins() - 15);
						addIntoInv(20);
					}
				}
				else if (my >= 355 && my <= 395 && !clockT[3]) {
					if (getCoins() >= 15) {
						setCoins(getCoins() - 15);
						addIntoInv(21);
					}
				}
				else if (my >= 405 && my <= 445 && !clockT[4]) {
					if (getCoins() >= 30) {
						setCoins(getCoins() - 30);
						addIntoInv(22);
					}
				}
			}
		}
	}
	
}
