package com.mawersoft.projrain.level.tile;

import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.graphics.Sprite;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnBrickTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnDebugTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnFlowerTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnGrassTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnRockTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnStoneTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnWaterTile;
import com.mawersoft.projrain.level.tile.spawn_level.SpawnWoodTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_debug = new SpawnDebugTile(Sprite.spawn_debug);
	public static Tile spawn_flower = new SpawnFlowerTile(Sprite.spawn_flower);
	public static Tile spawn_stone = new SpawnStoneTile(Sprite.spawn_stone);
	public static Tile spawn_brick = new SpawnBrickTile(Sprite.spawn_brick);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_rock = new SpawnRockTile(Sprite.spawn_rock);
	public static Tile spawn_wood = new SpawnWoodTile(Sprite.spawn_wood);

	
	public static final int col_spawn_grass = 0xff00ff00;
	public static final int col_spawn_debug = 0;//unused
	public static final int col_spawn_flower = 0;//unused
	public static final int col_spawn_stone = 0xffc9c9c9;
	public static final int col_spawn_brick = 0xffffbc00;
	public static final int col_spawn_water = 0;//unused
	public static final int col_spawn_rock = 0;//unused
	public static final int col_spawn_wood = 0xffea9a59;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {	
	}
	
	public boolean solid() {
		return false;// By default, pass through tiles
	}
	
}
