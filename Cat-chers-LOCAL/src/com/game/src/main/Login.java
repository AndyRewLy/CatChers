package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	
	public static enum STATE {
		USER,
		FIRST,
		LAST
	}
	
	public Rectangle userName= new Rectangle(5, 390, 100, 50);
	public Rectangle userInput = new Rectangle(80, 390, 270, 40);
	public Rectangle userGo = new Rectangle(330, 380, 50, 50);
	
	public String user = "";
	public String first = "";
	public String last = "";
	public String query;
	int userId;
	
	public static STATE State = STATE.USER;
	public Statement statement = null;
	public ResultSet results = null;
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt1 = new Font("arial", Font.BOLD, 12);
		g.setFont(fnt1);
		g.drawString(user, userInput.x + 10, userInput.y + 25);
		
	}
	
	public void keyPressed(KeyEvent e){
		char key = e.getKeyChar();
		int keyInt = e.getKeyCode();
		
		if (State == STATE.USER) {
			if (keyInt == KeyEvent.VK_BACK_SPACE) {
				if(user.length() > 0) {
					user = user.substring(0, user.length()-1);
				}
			}
			else if (keyInt == KeyEvent.VK_ENTER) {
				Game.State = Game.STATE.MENU;
			}
			else {
				if (user.length() < 32 && keyInt >= 65 && keyInt <= 90) {
					user = user + key;
				}
			}
		}
	}
	
	public static void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (mx >= Game.WIDTH / 2 + 70 && mx <= Game.WIDTH / 2 + 320) {
			if (my >= 150 && my <= 200) {
				State = STATE.USER;
			}
		}
		
	}

	public String getUser() {
		return user;
	}
	
	public int getUserId() {
		query = "SELECT userId FROM UserData WHERE userName = '" + Game.currUser + "';";
		try {
			statement = Game.conn.createStatement();
			results = statement.executeQuery(query);
			
			if (results.next() ) {
				userId = results.getInt(1);	
			}
			
			if (statement != null) {
				statement.close();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return userId;
	}
	
}
