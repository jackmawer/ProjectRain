package com.mawersoft.projrain.level;

import java.util.ArrayList;
import java.util.List;

import com.mawersoft.projrain.entity.Entity;
import com.mawersoft.projrain.entity.projectile.Projectile;
import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/levels/spawn.png");
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
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
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}
	
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	private void time() {
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + c % 2 * size / 2 - 5) / 16;
			int yt = (((int)y + (int)ya) + c / 2 * size / 2 + 5) / 16;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
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
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void addProjectile(Projectile p) {
		p.init(this);
		projectiles.add(p);
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
