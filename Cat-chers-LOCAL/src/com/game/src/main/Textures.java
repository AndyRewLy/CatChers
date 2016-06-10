package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage blueYarn, maroonYarn, greenYarn;
	public BufferedImage catGrass, catnip, blueFish, silverFish, goldFish;
	public BufferedImage deadGrass, water, oldFish, tick;
	public BufferedImage blackCat, orangeCat, greenCat, blueCat, redCat, mysteryCat;
	public BufferedImage whiteCushion, blueCushion, redCushion, treeCushion, catCushion;
	public BufferedImage whiteWall, blueWall, orangeWall, floralWall, stripedWall, catWall;
	public BufferedImage whiteCarpet, blueCarpet, maroonCarpet, linoleum, wood, catTiles;
	public BufferedImage whiteClock, blueClock, redClock, blackClock, catClock;
	public static BufferedImage[] allItemsTex = new BufferedImage[23]; 
	private SpriteSheet ss;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures(){
		allItemsTex[1] = whiteWall = ss.grabImage(6, 5, 32, 32);
		allItemsTex[2] = blueWall = ss.grabImage(3, 4, 32, 32);
		allItemsTex[3] = orangeWall = ss.grabImage(3, 5, 32, 32);
		allItemsTex[4] = floralWall = ss.grabImage(3, 6, 32, 32);
		allItemsTex[5] = stripedWall = ss.grabImage(4, 3, 32, 32);
		allItemsTex[6] = catWall = ss.grabImage(4, 4, 32, 32);
		allItemsTex[7] = whiteCushion = ss.grabImage(4, 5, 32, 32);
		allItemsTex[8] = blueCushion = ss.grabImage(4, 6, 32, 32);
		allItemsTex[9] = redCushion = ss.grabImage(5, 6, 32, 32);
		allItemsTex[10] = treeCushion = ss.grabImage(5, 5, 32, 32);
		allItemsTex[11] = catCushion = ss.grabImage(5, 4, 32, 32);
		allItemsTex[12] = whiteCarpet = ss.grabImage(6, 6, 32, 32);
		allItemsTex[13] = blueCarpet = ss.grabImage(5, 3, 32, 32);
		allItemsTex[14] = maroonCarpet = ss.grabImage(5, 7, 32, 32);
		allItemsTex[15] = linoleum = ss.grabImage(5, 8, 32, 32);
		allItemsTex[16] = wood = ss.grabImage(6, 8, 32, 32);
		allItemsTex[17] = catTiles = ss.grabImage(6, 7, 32, 32);
		allItemsTex[18] = blueClock = ss.grabImage(7, 5, 32, 32);
		allItemsTex[19] = redClock = ss.grabImage(7, 6, 32, 32);
		allItemsTex[20] = blackClock = ss.grabImage(7, 7, 32, 32);
		allItemsTex[21] = whiteClock = ss.grabImage(7, 8, 32, 32);
		allItemsTex[22] = catClock = ss.grabImage(8, 8, 32, 32);

		blueYarn = ss.grabImage(3, 1, 32, 32);
		maroonYarn = ss.grabImage(3, 2, 32, 32);
		greenYarn = ss.grabImage(3, 3, 32, 32);
		catGrass = ss.grabImage(4, 1, 32, 32);
		catnip = ss.grabImage(4, 2, 32, 32);
		blueFish = ss.grabImage(5, 1, 32, 32);
		silverFish = ss.grabImage(5, 2, 32, 32);
		deadGrass = ss.grabImage(6, 1, 32, 32);
		water = ss.grabImage(6, 2, 32, 32);
		oldFish = ss.grabImage(6, 3, 32, 32);
		tick = ss.grabImage(6, 4, 32, 32);
		goldFish = ss.grabImage(7, 1, 64, 64);
		blackCat = ss.grabImage(1, 1, 64, 64);
		orangeCat = ss.grabImage(1, 3, 64, 64);
		greenCat = ss.grabImage(1, 5, 64, 64);
		blueCat = ss.grabImage(1, 7, 64, 64);
		redCat = ss.grabImage(3, 7, 64, 64);
		mysteryCat = ss.grabImage(7, 3, 64, 64);
	
	}
}