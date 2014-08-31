package com.mawersoft.projrain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mawersoft.projrain.level.tile.Tile;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String path) {
		super(path);
	}

	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			System.out.println("Found this while loading the level, boss:");
			e.printStackTrace();
		}
	}

		protected void generateLevel() {
		}
			
}	
