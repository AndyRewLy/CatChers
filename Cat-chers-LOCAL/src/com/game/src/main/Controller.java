package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.EntityI;
import com.game.src.main.classes.EntityP;

public class Controller {
	
	private LinkedList<EntityI> eI = new LinkedList<EntityI>();
	private LinkedList<EntityP> eP = new LinkedList<EntityP>();
	private Game game;
	private Player p;
	
	Random r = new Random();
	
	EntityI entI;
	EntityP entP;
	
	private Textures tex;
	
	public Controller(Textures tex, Game game, Player p) {
		this.tex = tex;
		this.game = game;
		this.p = p;
	}
	
	public void createItem(int item_count) {
		int good;
		for (int i = 0; i < item_count; i++) {
			good = r.nextInt(10);
			if (good >= 6) {
				addEntity(new Item(r.nextInt(610), -64, tex, this, game, p, 100));
			}
			else {
				addEntity(new Item(r.nextInt(610), -64, tex, this, game, p, 100));
			}
		}
	}
	
	public void tick() {
		//EntityI
		for(int i = 0; i < eI.size(); i++) {
			entI = eI.get(i);
			entI.tick();
		}
		
		//EntityP
		for(int i = 0; i < eP.size(); i++) {
			entP = eP.get(i);
			entP.tick();
		}
	}
	
	public void render(Graphics g) {
		//EntityI
		for(int i = 0; i < eI.size(); i++) {
			entI = eI.get(i);
			entI.render(g);
		}
		
		//EntityP
		for(int i = 0; i < eP.size(); i++) {
			entP = eP.get(i);
			entP.render(g);
		}
	}
	
	public void addEntity(EntityI block) {
		eI.add(block);
	}
	
	public void removeEntity(EntityI block) {
		eI.remove(block);
	}
	
	public void addEntity(EntityP block) {
		eP.add(block);
	}
	
	public void removeEntity(EntityP block) {
		eP.remove(block);
	}
	
	public LinkedList<EntityI> getEntityI(){
		return eI;
	}
	
	public LinkedList<EntityP> getEntityP(){
		return eP;
	}

	
}
