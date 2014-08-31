package com.mawersoft.projrain.level;

import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/levels/spawn.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
		
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
	}
	
	protected void loadLevel(String path) {
	}
	
	public void update() {
	}
	
	private void time() {
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++){
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	
	// Grass = 0xFF00FF00
    // Flower = 0xFFFFFF00
	// Rock = 0xFFDFDF00
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if (tiles[x+y*width] == Tile.col_spawn_wood) return Tile.spawn_wood;
		if (tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x+y*width] == Tile.col_spawn_debug) return Tile.spawn_debug;
		if (tiles[x+y*width] == Tile.col_spawn_brick) return Tile.spawn_brick;
		if (tiles[x+y*width] == Tile.col_spawn_flower) return Tile.spawn_flower;
		if (tiles[x+y*width] == Tile.col_spawn_rock) return Tile.spawn_rock;
		if (tiles[x+y*width] == Tile.col_spawn_stone) return Tile.spawn_stone;
		if (tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;
		return Tile.spawn_debug;
	}
	
}
