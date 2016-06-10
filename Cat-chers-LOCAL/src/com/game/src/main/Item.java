package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityI;

public class Item extends GameObject implements EntityI{
	
	private Textures tex;
	private Game game;
	private Controller c;
	private Player p;
	private int score;
	private boolean good;
	private int randVal;
	
	Random r = new Random();
	
	private int speed = r.nextInt(3) + 1;
	
	public Item(double x, double y, Textures tex, Controller c, Game game, Player p, int score) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		this.p = p;
		this.score = score;
		randVal = r.nextInt(1000);
		if (randVal < 600) {
			good = true;
		}
		else 
			good = false;
	}

	public void tick() {
		y += speed;
		
		if (y > Game.HEIGHT * Game.SCALE) {
			speed = r.nextInt(3) + 1;
			x = r.nextInt(640);
			y = -32;
		}
		
		if(Physics.Collision(this, this.p) && this.getY() - this.p.getY() <= 5) {
			c.removeEntity(this);
			
			if(good) {
				p.setScore(p.getScore()+this.getScore());
				game.setItem_caught(game.getItem_caught() + 1);
			}
			else {
				Game.LIVES--;
				game.setItem_caught(game.getItem_caught() + 1);
			}
		}
		
	}
	
	public void render(Graphics g) {
		if (randVal == 1) {
			g.drawImage(tex.goldFish, (int)x, (int)y, null);
		}
		else if (randVal < 200) {
			if (randVal % 2 == 0)
				g.drawImage(tex.blueYarn, (int)x, (int)y, null);
			else
				g.drawImage(tex.maroonYarn,(int)x, (int)y, null);
		}
		else if (randVal < 400) {
			if (randVal % 2 == 0)
				g.drawImage(tex.catGrass, (int)x, (int)y, null);
			else
				g.drawImage(tex.catnip,(int)x, (int)y, null);
		}
		else if (randVal < 600) {
			if (randVal % 2 == 0)
				g.drawImage(tex.blueFish,(int)x, (int)y, null);
			else
				g.drawImage(tex.silverFish,(int)x, (int)y, null);
		}
		else if (randVal < 800) {
			if (randVal % 2 == 0)
				g.drawImage(tex.deadGrass,(int)x, (int)y, null);
			else
				g.drawImage(tex.water,(int)x, (int)y, null);
		}
		else {
			if (randVal % 2 == 0)
				g.drawImage(tex.oldFish,(int)x, (int)y, null);
			else
				g.drawImage(tex.tick,(int)x, (int)y, null);	
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getY() {
		return y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public void setX(double newX) {
		x = newX;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
