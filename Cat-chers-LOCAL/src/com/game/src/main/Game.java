package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityI;
import com.game.src.main.classes.EntityP;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Cat-chers";
	
	private boolean running = false;
	private boolean resetScore = false;
    private Thread thread;
    
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferedImage tintedbg = null;
    private BufferedImage loginscreen = null;
    private BufferedImage menuscreen = null;
    private BufferedImage shopscreen = null;
    
    private int item_count = 5;
    private int item_caught = 0;

	private Player p;
    public static Textures tex;
    private Controller c;
    private Menu menu;
    private Lose lose;
    private Login login;
    private HighScore highScore;
    private Shop shop;
    private Search search;
    private House house;
    private Profile profile;
    private SelectCat selectCat;
    
    public LinkedList<EntityI> eI;
    public LinkedList<EntityP> eP;
    public static int LIVES = 9;
    public static boolean loggedIn = false;
    public static boolean newUser = false;
    public static boolean connected = false;
    public static String currUser;
    public static int currUserId;
    public static BufferedImage shopTab = null;
    
    public static enum STATE{
    	MENU,
    	HIGHSCORE,
    	GAME,
    	LOSE,
    	LOGIN,
    	SHOP,
    	SEARCH,
    	SELECT_CAT,
    	HOUSE,
    	PROFILE
    }
    
    public static enum SHOP_TAB{
    	CUSHIONS,
    	WALLS,
    	CARPETS,
    	CLOCKS,
    	NONE
    }

    public static enum HOUSE_TAB{
    	CUSHIONS,
    	WALLS,
    	CARPETS,
    	CLOCKS,
    	NONE
    }
    public static enum CAT_CHOSEN{
    	BLACKCAT,
    	ORANGECAT,
    	GREENCAT,
    	BLUECAT,
    	REDCAT
    }
    
    public static STATE State = STATE.LOGIN;
    public static SHOP_TAB ShopTab = SHOP_TAB.NONE;
    public static HOUSE_TAB HouseTab = HOUSE_TAB.NONE;
    public static CAT_CHOSEN CatChosen = CAT_CHOSEN.BLACKCAT;
    public static int[] availableCats = new int[5];
    public static int[] allItems = new int[23];
    public static int[] availableItems = new int[23];
    public static int[] itemsInUse = new int[4];
    
    //Connector
    public static Connection conn;
    
    public Game() {
    	
    }
    
    public void init() {
    	requestFocus();
    	BufferedImageLoader loader = new BufferedImageLoader();

    	try{
    		spriteSheet = loader.loadImage("/SpriteSheet.png");
    		background = loader.loadImage("/backGround.png");
    		loginscreen = loader.loadImage("/loginscreen.png");
    		menuscreen = loader.loadImage("/menuscreen.png");
    		shopscreen = loader.loadImage("/shopscreen.png");
    		
    		tintedbg = loader.loadImage("/tintedbg.png");
    		shopTab = loader.loadImage("/shopRect.png");
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	try {
    		conn = DBConnection.getConnection();
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    	tex = new Textures(this);
    	p = new Player(320, 320, tex, this);
    	c = new Controller(tex, this, p);
    	menu = new Menu();
    	lose = new Lose(p, background);
    	login = new Login();
    	shop = new Shop();
    	search = new Search();
    	house = new House(tex);
    	profile = new Profile();
    	selectCat = new SelectCat(tex);
    	highScore = new HighScore();
    	
    	eI = c.getEntityI();
    	eP = c.getEntityP();
    	
    	this.addKeyListener(new KeyInput(this));
    	this.addMouseListener(new MouseInput());
    	
    	c.createItem(item_count);
    }
    
    public int getItem_count() {
		return item_count;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

    public int getItem_caught() {
		return item_caught;
	}

	public void setItem_caught(int item_caught) {
		this.item_caught = item_caught;
	}

	private synchronized void start() {
    	if(running)
    		return;
    	
    	running = true;
    	thread = new Thread(this);
    	thread.start();
    }
    
    private synchronized void stop() {
    	if(!running) 
    		return;
    	
    	running = false;
    	try {
    		thread.join();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	System.exit(1);
    }
    
	public void run() {

		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		if(State == STATE.GAME) {
			if(resetScore) {
				p.setScore(0);
				item_count = 5;
				item_caught = 0;
				c.createItem(item_count);
				resetScore = false;
			}
			p.tick();
			c.tick();
			if(LIVES < 1) {
				State = STATE.LOSE;
				LIVES = 9;
				resetScore = true;
				while (eI.size() > 0) {
					eI.removeFirst();
				}
			}
		}
		
		if(item_caught > 0) {
			item_count += 3;
			c.createItem(3);
			item_caught = 0;
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////// within these lines is drawing space
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		if(State == STATE.GAME) {
			g.drawImage(background, 0, 0, null);
			c.render(g);
			p.render(g);
			g.drawString("Lives: " + LIVES, 20, 20);
		}
		else if(State == STATE.MENU) {
			
			if (!loggedIn) {
				currUser = login.getUser();
				checkInDB();
				currUserId = login.getUserId();
				loggedIn = true;
				getCats();
				getInventory();
			}
			
			g.drawImage(menuscreen, 0, 0, null);
			menu.render(g);
			Lose.newCat = false;
			Lose.statsUpdated = false;
			Lose.highScoreChecked = false;
		}
		else if(State == STATE.LOSE) {
			lose.render(g);	
		}
		else if(State == STATE.LOGIN) {
			g.drawImage(loginscreen, 0, 0, null);
			login.render(g);
		}
		else if(State == STATE.SHOP) {
			g.drawImage(shopscreen, 0, 0, null);
			shop.render(g);
		}
		else if(State == STATE.SEARCH) {
			g.drawImage(background,  0, 0, null);
			search.render(g);
		}
		else if (State == STATE.PROFILE) {
			g.drawImage(background, 0, 0, null);
			profile.render(g);
		}
		else if (State == STATE.HOUSE) {
			getInventory();
			g.drawImage(tintedbg, 0, 0, null);
			house.getCurItems(itemsInUse);
			house.render(g);
		}
		else if (State == STATE.SELECT_CAT) {
			g.drawImage(background, 0, 0, null);
			selectCat.render(g);
		}
		else if (State == STATE.HIGHSCORE) {
			g.drawImage(background,  0,  0,  null);
			highScore.render(g);
		}

		
		if (State != STATE.MENU) {
			newUser = false;
		}
		////////////
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (State == STATE.LOGIN) {
			login.keyPressed(e);
		}
		else if(State == STATE.GAME) {
			if(key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			}
			else if(key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			}
		}
		else if(State == STATE.SEARCH) {
			search.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		}
	}
	
	public static void main(String args[]) throws Exception{
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
		
	}
	
	public BufferedImage getSpriteSheet(){
		return spriteSheet;
	}
	
	//Queries for the User to make sure in DB
	
	public static void checkInDB() {
		String query;
		Statement statement = null;
		ResultSet results = null;
		
		
		query = "SELECT userName" +
		       " FROM UserData " +
			   "WHERE userName = '" + currUser + "';";
		
		try {
			statement = Game.conn.createStatement();
			
			results = statement.executeQuery(query);
			
			if(!results.isBeforeFirst()) {
				newUser = true;
				try {
					setupUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			while (results.next()) {
				String userName = results.getString("userName");
			}		
		}
		catch (SQLException sqlEx) {
			System.out.println("Error doing query: " + sqlEx);
			sqlEx.printStackTrace(System.err);
		}
		finally {
			try {
                if(results != null) {
                   results.close();
                   results = null;
                }

                if(statement != null) {
                   statement.close();
                   statement = null;
                }
             } 
			 catch (Exception ex) {
                 System.err.println("Error closing query: " + ex);
                 ex.printStackTrace(System.err);
             }
        }	      
	}	
	
	public static void setupUser() {
		String insert;
		Statement statement = null;
		
		//Create new User Tuple + House + Inventory
		insert = "INSERT INTO UserData (userName)"
				+ " VALUES ('" + currUser +  "');";
				

		try {
 			statement = conn.createStatement();
 			statement.executeUpdate(insert);
 		
 			if (statement != null) {
 				statement.close();
 			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
 		// Create Inventory Values
		
		insert = " INSERT INTO Inventory (userId, userName, itemId, inUse)"
				+ " VALUES ((SELECT userId FROM UserData WHERE userName = '" + currUser + "'),"
				+ "         '" + currUser + "'," 
				+ "         (SELECT itemId FROM Shop WHERE decorationName = 'Wall Color - White'),"
				+ "			TRUE),"
				+ "		   ((SELECT userId FROM UserData WHERE userName = '" + currUser + "'),"
				+ "         '" + currUser + "'," 
				+ "         (SELECT itemId FROM Shop WHERE decorationName = 'White Carpet'),"
				+ "			TRUE);";
		try {
 			statement = conn.createStatement();
 			statement.executeUpdate(insert);
 		
 			if (statement != null) {
 				statement.close();
 			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
 		//Create new Profile Values
 		
 		insert = "INSERT INTO UserProfile (userId, highestScore, highestRank, totalCatCoins)"
 				+ " VALUES ((SELECT userId FROM UserData WHERE userName = '" + currUser + "'), 0, 0, 0);" ;
 		try {
 			statement = conn.createStatement();
 			statement.executeUpdate(insert);
 			if (statement != null) {
 				statement.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		
 		// Insert into Cats
 		
 		insert = "INSERT INTO Cats (userId, userName, catName)"
 			   + " VALUES ((SELECT userId FROM UserData WHERE userName = '" + currUser + "'),"
 			   + "        '" + currUser + "', 'blackCat');";
 		try {
 			statement = conn.createStatement();
 			statement.executeUpdate(insert);
 		
 			if (statement != null) {
 				statement.close();
 			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void getCats() {
		String query;
		Statement statement = null;
		ResultSet results = null;
		
		query = "SELECT * FROM Cats WHERE userName = '" + currUser + "';";
				
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
					
			while (results.next()) {
				String catName = results.getString("catName");
				
				if (catName.equals("blackCat")) {
					availableCats[0] = 1;
				}
				else if (catName.equals("orangeCat")) {
					availableCats[1] = 1;
				}
				else if (catName.equals("greenCat")) {
					availableCats[2] = 1;
				}
				else if (catName.equals("blueCat")) {
					availableCats[3] = 1;
				}
				else if (catName.equals("redCat")) {
					availableCats[4] = 1;
				}
			}
						
			statement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getInventory() {
		getItemsInUse();
		getAvailableItems();
	}

	public static void getItemsInUse() {
		String query, insert;
		Statement statement = null;
		ResultSet results = null;
		
		query = "SELECT I.itemId FROM Inventory I JOIN Shop S ON S.itemId = I.itemId"
				+ " WHERE userId = " + currUserId + " AND I.inUse = 1 ORDER BY itemId;";
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
					
			while (results.next()) {
				int itemId = results.getInt(1);
				if (itemId >= 1 && itemId <= 6)
					itemsInUse[0] = itemId;
				else if (itemId >= 7 && itemId <= 11)
					itemsInUse[1] = itemId;
				else if (itemId >= 12 && itemId <= 17)
					itemsInUse[2] = itemId;
				else if (itemId >= 18 && itemId <= 22)
					itemsInUse[3] = itemId;
			}
			statement.close();
		}
		catch (Exception e) {
			System.out.println("no items in use");
		}
	}
	
	public static void getAvailableItems() {
		String query;
		Statement statement = null;
		ResultSet results = null;
		
		query = "SELECT S.itemId FROM Inventory I JOIN Shop S ON S.itemId = I.itemId"
				+ " WHERE userId = " + currUserId + " ORDER BY itemId;";
		try {
			statement = conn.createStatement();
			results = statement.executeQuery(query);
					
			while (results.next()) {
				int itemId = results.getInt(1);
				availableItems[itemId] = itemId;
			}
			statement.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
