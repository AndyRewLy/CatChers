package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

		public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 60, 175, 80, 50);
		public Rectangle shopButton = new Rectangle(Game.WIDTH / 2 + 220, 175, 80, 50);
		public Rectangle profileButton = new Rectangle(Game.WIDTH / 2 + 130, 230, 100, 50);
		public Rectangle searchButton = new Rectangle(Game.WIDTH / 2 + 130, 290, 110, 50);
		public Rectangle highScores = new Rectangle(Game.WIDTH / 2 + 105, 350, 175, 50);
		public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 150, 410, 80, 50);

		public void render(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			
			if (Game.newUser == true) {
				g.setColor(Color.BLACK);
				g.drawString("Welcome to", Game.WIDTH / 2 + 85, 50);
			}
		}
}
