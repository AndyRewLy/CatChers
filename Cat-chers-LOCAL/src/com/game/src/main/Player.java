package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityP;

public class Player extends GameObject implements EntityP{

	private double velX = 0;

	public double getVelX() {
		return velX;
	}
	private double velY = 0;
	private Game game;
	private Textures tex;
	private int score;
	
	public Player(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		score = 0;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		if(Physics.Collision(this, game.eI)) {
			
		}
		
		if (x <= 0) {
			x = 0;
		}
		if (x >= 612) {
			x = 612;
		}
	}

	public void render(Graphics g) {
		if (Game.CatChosen == Game.CAT_CHOSEN.BLACKCAT) {
			g.drawImage(tex.blackCat,  (int)x, (int)y,  null);
		}
		else if (Game.CatChosen == Game.CAT_CHOSEN.ORANGECAT) {
			g.drawImage(tex.orangeCat,  (int)x, (int)y,  null);
		}
		else if (Game.CatChosen == Game.CAT_CHOSEN.GREENCAT) {
			g.drawImage(tex.greenCat,  (int)x, (int)y,  null);
		}
		else if (Game.CatChosen == Game.CAT_CHOSEN.BLUECAT) {
			g.drawImage(tex.blueCat,  (int)x, (int)y,  null);
		}
		else if (Game.CatChosen == Game.CAT_CHOSEN.REDCAT) {
			g.drawImage(tex.redCat,  (int)x, (int)y,  null);
		}
		g.drawString("Score: " + this.score, Game.WIDTH * 2 - 100 , 25);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x + 24, (int)y, 32, 32);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

