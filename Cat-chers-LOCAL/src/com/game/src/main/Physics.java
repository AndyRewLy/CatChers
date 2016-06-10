package com.game.src.main;

import java.util.LinkedList;

import com.game.src.main.classes.EntityI;
import com.game.src.main.classes.EntityP;

public class Physics{
	
	public static boolean Collision(EntityP entP, LinkedList<EntityI> entI) {
		for(int i = 0; i < entI.size();i++){
			
			if(entP.getBounds().intersects(entI.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean Collision(EntityI entI, EntityP entP) {
			
		if(entI.getBounds().intersects(entP.getBounds())){
			return true;
		}

		return false;
	}
}
