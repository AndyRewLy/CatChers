package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.game.src.main.Game.STATE;

public class MouseInput implements MouseListener{
	
	@Override
	public void mouseClicked(MouseEvent arGame0) {
		// TODO Auto-Gameenerated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arGame0) {
		// TODO Auto-Gameenerated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arGame0) {
		// TODO Auto-Gameenerated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		//Login Screen
		if (Game.State == Game.STATE.LOGIN) {
			Login.mousePressed(e);
			if (mx >= 330 && mx <= 380) {
				if (my >= 380 && my <= 430) {
					Game.State = Game.STATE.MENU;
				}
			}
		}
		
		//Menu Screen
		else if(Game.State == Game.STATE.MENU) {
			if (my >= 175 && my <= 225) {
				if (mx >= Game.WIDTH / 2 + 60 && mx <= Game.WIDTH / 2 + 140) {
					//Pressed SELECT CAT
					Game.State = Game.STATE.SELECT_CAT;
				}
				else if (mx >= Game.WIDTH / 2 + 220 && mx <= Game.WIDTH / 2 + 300) {
					//Pressed Shop Button
					Game.State = Game.STATE.SHOP;
				}
			}
			else if (mx >= Game.WIDTH / 2 + 130 && mx <= Game.WIDTH / 2 + 230 && my >= 230 && my <= 280) {
				//Pressed Profile Button
				Game.State = Game.STATE.PROFILE;
			}
			else if (mx >= Game.WIDTH / 2 + 130 && mx <= Game.WIDTH / 2 + 240 && my >= 290 && my <= 340) {
				//Pressed Search Button
					Game.State = Game.STATE.SEARCH;
			}
			else if (mx >= Game.WIDTH / 2 + 105 && mx <= Game.WIDTH / 2 + 280 && my >=  350 && my <= 400) {
				//High Score Button
				HighScore.scoresRead = false;
				Game.State = Game.STATE.HIGHSCORE;
			}
			else if (my >= 410 && my <= 460 && mx >= Game.WIDTH / 2 + 150 && mx <= Game.WIDTH / 2 + 230) {
				System.exit(0);
			}
		}
		//Select Cat Screen
		else if (Game.State == Game.STATE.SELECT_CAT) {
			SelectCat.mousePressed(e);
		}
		
		//Lose Screen 
		else if (Game.State == Game.STATE.LOSE && mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
			if (my >= 350 && my <= 400) {
				//Pressed Play Again Button
				Game.State = Game.STATE.SELECT_CAT;

			}
			else if (my >= 250 && my <= 300) {
				//Pressed Menu Button
				Game.State = Game.STATE.MENU;
			}
		}
		
		//Shop Screen
		else if (Game.State == Game.STATE.SHOP) {
			Shop.mousePressed(e);
			
			if (my >= 150 && my <= 200) {
				if (mx >= 0 && mx <= 100) {
					Game.ShopTab = Game.SHOP_TAB.CUSHIONS;
				}
				else if (mx > 100 && mx <= 200) {
					Game.ShopTab = Game.SHOP_TAB.WALLS;
				}
				else if (mx > 200 && mx <= 300) {
					Game.ShopTab = Game.SHOP_TAB.CARPETS;
				}
				else if (mx > 300 && mx <= 400) {
					Game.ShopTab = Game.SHOP_TAB.CLOCKS;
				}
			}
			else if (my >= 20 && my <= 70) {
				if (mx >= Game.WIDTH * 2 - 100 && mx < Game.WIDTH * 2) {
					Game.State = Game.STATE.MENU;
				}
			}
		}
		
		//Profile Screen
		else if (Game.State == Game.STATE.PROFILE) {
			if (mx >= 20 && mx <= 120 && my >= 200 && my <= 250) {
				Game.State = STATE.HOUSE;
			}
			else if (my >= 20 && my <= 70) {
				if (mx >= Game.WIDTH * 2 - 100 && mx < Game.WIDTH * 2) {
					Game.State = Game.STATE.MENU;
				}
			}
		}	
		
		//House Screen 
		else if (Game.State == Game.STATE.HOUSE) {
			House.mousePressed(e);
			if (my >= 250 && my <= 300) {
				if (mx >= 0 && mx <= 100) {
					Game.ShopTab = Game.SHOP_TAB.CUSHIONS;
				}
				else if (mx > 100 && mx <= 200) {
					Game.ShopTab = Game.SHOP_TAB.WALLS;
				}
				else if (mx > 200 && mx <= 300) {
					Game.ShopTab = Game.SHOP_TAB.CARPETS;
				}
				else if (mx > 300 && mx <= 400) {
					Game.ShopTab = Game.SHOP_TAB.CLOCKS;
				}
			}
			else if (my >= 20 && my <= 70) {
				if (mx >= Game.WIDTH * 2 - 100 && mx < Game.WIDTH * 2) {
					Game.State = Game.STATE.MENU;
				}
			}
		}
		
		// Search Screen
		//public Rectangle searchButton = new Rectangle(Game.WIDTH / 2 + 300 , 150, 25, 25);
		else if (Game.State == Game.STATE.SEARCH) {
			if (my >= 150 && my <= 175 && !Search.profileVisible) {
				if (mx >=  Game.WIDTH / 2 + 300 && mx <= Game.WIDTH / 2 + 325) {
					//Search Button
					Search.mousePressed(e);
				}
			}
			else if (my >= 20 && my <= 70) {
				if (mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2) {
					//Menu Button
					Search.profileVisible = false;
					Search.searched = false;
					Search.noUser = false;
					Search.input = "";
					Game.State = Game.STATE.MENU;
				}
			}
		}
		//High Scores
		else if (Game.State == Game.STATE.HIGHSCORE) {
			if (my >= 20 && my <= 70 && mx >= Game.WIDTH * 2 - 100 && mx <= Game.WIDTH * 2) {
				Game.State = Game.STATE.MENU;
				HighScore.scoresRead = false;
			}
		}
	}

	
	@Override
	public void mouseReleased(MouseEvent arGame0) {
		// TODO Auto-Gameenerated method stub
		
	}
	
	

}
