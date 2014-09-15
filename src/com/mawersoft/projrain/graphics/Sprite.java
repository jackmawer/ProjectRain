package com.mawersoft.projrain.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);//This is how you create a sprite! (size of sprite, x pos of sprite on sheet, y pos on sheet, sheet)
	public static Sprite brickwall = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0xA30000); //Void tile, might replace with a texture
	
	//public static Sprite player0 = new Sprite(16, 0, 10, SpriteSheet.tiles);
	//public static Sprite player1 = new Sprite(16, 1, 10, SpriteSheet.tiles);
	//public static Sprite player2 = new Sprite(16, 0, 11, SpriteSheet.tiles);
	//public static Sprite player3 = new Sprite(16, 1, 11, SpriteSheet.tiles);
	
	//Spawn level sprites
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_debug = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_flower = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_stone = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_brick = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_rock = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_wood = new Sprite(16, 1, 2, SpriteSheet.spawn_level);
	
	//Player sprites
	
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles); //This is the 5th sprite down when dealing with 32
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles); //This is the 5th sprite down when dealing with 32
	public static Sprite player_left = new Sprite(32, 3, 5, SpriteSheet.tiles); //This is the 5th sprite down when dealing with 32
	public static Sprite player_right = new Sprite(32, 1, 5, SpriteSheet.tiles); //This is the 5th sprite down when dealing with 32
	
	public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	
	public static Sprite player_left_1 = new Sprite(32, 3, 6, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 3, 7, SpriteSheet.tiles);
	
	public static Sprite player_right_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	
	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	
	
	//Projectile sprites
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int colour) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		
		for (int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = colour;
		}
		
	}

	private void load() {
		for (int y = 0; y < SIZE; y++)
		{
			for (int x = 0; x < SIZE; x++) {
				pixels[x+y*SIZE] = sheet.pixels[ (x + this.x) + (y + this.y) * sheet.SIZE ];
			}
		}
	}
	
}
