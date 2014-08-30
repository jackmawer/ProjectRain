package com.mawersoft.projrain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mawersoft.projrain.level.tile.Tile;

public class SpawnLevel extends Level {
	
	private Tile[] tiles;
	private int levelPixels;

	public SpawnLevel(String path) {
		super(path);
	}

	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, levelPixels, 0, w);
		} catch (IOException e) {
			System.out.println("Found this while loading the level, boss:");
			e.printStackTrace();
		}
		
		protected void generateLevel() {
			
		}	
}
