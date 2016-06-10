package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class House {

	public static Rectangle cushionTab = new Rectangle(0, 250, 120, 50);
	public static Rectangle wallTab = new Rectangle(120, 250, 80, 50);
	public static Rectangle carpetTab = new Rectangle(200, 250, 100, 50);
	public static Rectangle clockTab = new Rectangle(300, 250, 100, 50);
	public static Rectangle backToMenu = new Rectangle(Game.WIDTH * 2 - 100, 20, 100, 50);
	public static Rectangle houseBox = new Rectangle(100, 50, 310, 186);
	public static BufferedImage curWall;
	public static BufferedImage curCarpet;
	public static BufferedImage curCushion;
	public static BufferedImage curClock;
	public static Textures tex;

	public House(Textures t) {
		this.tex = t;
	}
	
	public void getCurItems(int[] itemsInUse) {
		curWall = Textures.allItemsTex[itemsInUse[0]];
		curCarpet = Textures.allItemsTex[itemsInUse[2]];
		curCushion = Textures.allItemsTex[itemsInUse[1]];
		curClock = Textures.allItemsTex[itemsInUse[3]];
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// draw wall
		for (int i = 0; i < houseBox.width; i += 31) {
			for (int j = 0; j < houseBox.height; j += 31) {
				g.drawImage(curWall, houseBox.x + i, houseBox.y + j, null);
			}
		}
		//draw carpet
		for (int i = 0; i < houseBox.width; i += 31) {
			g.drawImage(curCarpet, houseBox.x + i, houseBox.y + 190 - 32, null);
		}

		// draw cushion
		if (curCushion != null) {
			g.drawImage(curCushion, houseBox.x + 100, 200, null);
		}
		
		// draw clock
		if (curClock != null)
			g.drawImage(curClock, houseBox.x + 100, 150, null);

		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		g.drawString("Cushions", cushionTab.x + 19, cushionTab.y + 30);			
		g2d.draw(cushionTab);
		g.drawString("Walls", wallTab.x + 19, wallTab.y + 30);
		g2d.draw(wallTab);
		g.drawString("Carpets", carpetTab.x + 19, carpetTab.y + 30);
		g2d.draw(carpetTab);
		g.drawString("Clocks", clockTab.x + 19, clockTab.y + 30);
		g2d.draw(clockTab);
		g.drawString("Menu", backToMenu.x + 19, backToMenu.y + 30);
		g2d.draw(backToMenu);
		
		if (Game.ShopTab == Game.SHOP_TAB.WALLS) {
				for (int i = 1; i <= 6; i++) {
					if (Game.availableItems[i] != 0) {
						g.drawImage(Textures.allItemsTex[i], 50 * i, wallTab.y + 80, null);
					}
				}
		}
		
		else if (Game.ShopTab == Game.SHOP_TAB.CUSHIONS) {
			int x = 1;
			for (int i = 7; i <= 11; i++) {
				if (Game.availableItems[i] != 0) {
					g.drawImage(Textures.allItemsTex[i], 50 * x, cushionTab.y + 80, null);
				}
				x++;
			}
		}

		else if (Game.ShopTab == Game.SHOP_TAB.CARPETS) {
			int x = 1;
			for (int i = 12; i <= 17; i++) {
				if (Game.availableItems[i] != 0) {
					g.drawImage(Textures.allItemsTex[i], 50 * x, carpetTab.y + 80, null);
				}
				x++;
			}
		}

		else if (Game.ShopTab == Game.SHOP_TAB.CLOCKS) {
			int x = 1;
			for (int i = 18; i <= 22; i++) {
				if (Game.availableItems[i] != 0) {
					g.drawImage(Textures.allItemsTex[i], 50 * x, clockTab.y + 80, null);
				}	
				x++;
			}
		}
		
	}
	
	public static void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (my >= wallTab.y + 80 && my <= wallTab.y + 112) {
			if (Game.ShopTab == Game.SHOP_TAB.WALLS){
				int x = 1;
				for (int i = 1; i <= 6; i++) {
					if (Game.availableItems[i] != 0 && mx >= (50 * x) && mx <= (50 * x) + 32) {
						updateItemsInUse(Game.itemsInUse[0], Game.availableItems[i]);
						Game.itemsInUse[0] = Game.availableItems[i];
						curWall = Textures.allItemsTex[Game.itemsInUse[0]];
					}
					x++;
				}
			}
			else if (Game.ShopTab == Game.SHOP_TAB.CUSHIONS){
				int x = 1;
				for (int i = 7; i <= 11; i++) {
					if (Game.availableItems[i] != 0 && mx >= (50 * x) && mx <= (50 * x) + 32) {
						updateItemsInUse(Game.itemsInUse[1], Game.availableItems[i]);
						Game.itemsInUse[1] = Game.availableItems[i];
						curCushion = Textures.allItemsTex[Game.itemsInUse[1]];
					}
					x++;
				}
			
			}
			else if (Game.ShopTab == Game.SHOP_TAB.CARPETS){
				int x = 1;
				for (int i = 12; i <= 17; i++) {
					if (Game.availableItems[i] != 0 && mx >= (50 * x) && mx <= (50 * x) + 32) {
						updateItemsInUse(Game.itemsInUse[2], Game.availableItems[i]);
						Game.itemsInUse[2] = Game.availableItems[i];
						curCarpet = Textures.allItemsTex[Game.itemsInUse[2]];
					}
					x++;
				}
			}
			else if (Game.ShopTab == Game.SHOP_TAB.CLOCKS){
				int x = 1;
				for (int i = 18; i <= 22; i++) {
					if (Game.availableItems[i] != 0 && mx >= (50 * x) && mx <= (50 * x) + 32) {
						updateItemsInUse(Game.itemsInUse[3], Game.availableItems[i]);
						Game.itemsInUse[3] = Game.availableItems[i];
						curClock = Textures.allItemsTex[Game.itemsInUse[3]];
					}
					x++;
				}
			}	
		}
	}
	
	public static void updateItemsInUse(int oldId, int newId) {
		String query, insert;
		Statement statement = null;
		ResultSet results = null;
		int i = 0;
		
		query = "UPDATE Inventory SET inUse = 0 "
				+ " WHERE userId = " + Game.currUserId + " AND itemId = " + oldId + ";";
		
		System.out.println(query);
		try {
			statement = Game.conn.createStatement();
			statement.executeUpdate(query);
		
 			if (statement != null) 
 				statement.close();
 			} catch (Exception e) {
	 			e.printStackTrace();
	 	}
		
		query = "UPDATE Inventory SET inUse = 1 "
				+ " WHERE userId = " + Game.currUserId + " AND itemId = " + newId + ";";
		
		System.out.println(query);
		try {
			statement = Game.conn.createStatement();
			statement.executeUpdate(query);
		
 			if (statement != null) 
 				statement.close();
 			} catch (Exception e) {
	 			e.printStackTrace();
	 	}
	}

}