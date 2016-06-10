package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SelectCat {

	public BufferedImage blackCat, orangeCat, greenCat, blueCat, redCat, mysteryCat;
	public Rectangle backToMenu = new Rectangle(Game.WIDTH * 2 - 100, 20, 100, 50);
	public Textures tex;
	
	public SelectCat(Textures t) {
		this.tex = t;
	}
	public static enum AVAILABLE_CATS{
		BLACKCAT,
    	ORANGECAT,
    	GREENCAT,
    	BLUECAT,
    	REDCAT		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		blackCat = tex.blackCat;
		orangeCat = tex.orangeCat;
		greenCat = tex.greenCat;
		blueCat = tex.blueCat;
		redCat = tex.redCat;
		mysteryCat = tex.mysteryCat;
		g.drawString("Menu", backToMenu.x + 12, backToMenu.y + 30);
		g2d.draw(backToMenu);
		if (Game.availableCats[0] == 1) {
			g.drawImage(blackCat, 50, Game.HEIGHT / 2, null);
		}
		else {
			g.drawImage(mysteryCat, 50, Game.HEIGHT / 2, null);
		}
		
		if (Game.availableCats[1] == 1) {
			g.drawImage(orangeCat, 150, Game.HEIGHT / 2, null);
		}
		else {
			g.drawImage(mysteryCat, 150, Game.HEIGHT / 2, null);
		}
		
		if (Game.availableCats[2] == 1) {
			g.drawImage(greenCat, 250, Game.HEIGHT / 2, null);
		}
		else {
			g.drawImage(mysteryCat, 250, Game.HEIGHT / 2, null);
		}
		
		if (Game.availableCats[3] == 1) {
			g.drawImage(blueCat, 350, Game.HEIGHT / 2, null);
		}
		else {
			g.drawImage(mysteryCat, 350, Game.HEIGHT / 2, null);
		}
		
		if (Game.availableCats[4] == 1) {
			g.drawImage(redCat, 450, Game.HEIGHT / 2, null);
		}
		else {
			g.drawImage(mysteryCat, 450, Game.HEIGHT / 2, null);
		}
	}
	
	public static void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (my >= Game.HEIGHT / 2 && my <= Game.HEIGHT / 2 + 64) {
			if (mx >= 50 && mx <= 114 && Game.availableCats[0] == 1) {
				Game.State = Game.STATE.GAME;
				Game.CatChosen = Game.CAT_CHOSEN.BLACKCAT;
			}
			else if (mx >= 150 && mx <= 214 && Game.availableCats[1] == 1) {
				Game.State = Game.STATE.GAME;
				Game.CatChosen = Game.CAT_CHOSEN.ORANGECAT;
			}
			else if (mx >= 250 && mx <= 314 && Game.availableCats[2] == 1) {
				Game.State = Game.STATE.GAME;
				Game.CatChosen = Game.CAT_CHOSEN.GREENCAT;
			}
			else if (mx >= 350 && mx <= 414 && Game.availableCats[3] == 1) {
				Game.State = Game.STATE.GAME;
				Game.CatChosen = Game.CAT_CHOSEN.BLUECAT;
			}
			else if (mx >= 450 && mx <= 514 && Game.availableCats[4] == 1) {
				Game.State = Game.STATE.GAME;
				Game.CatChosen = Game.CAT_CHOSEN.REDCAT;
			}
		}
		else if (my >= 20 && my <= 70 && mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2) {
			Game.State = Game.STATE.MENU;
		}
	}
}
