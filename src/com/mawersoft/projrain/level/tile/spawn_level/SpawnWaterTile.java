package com.mawersoft.projrain.level.tile.spawn_level;

import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.graphics.Sprite;
import com.mawersoft.projrain.level.tile.Tile;

public class SpawnWaterTile extends Tile {

	public SpawnWaterTile(Sprite sprite) {
		super(sprite);
	}
	public void render(int x, int y, Screen screen) {	
		screen.renderTile(x << 4, y << 4, this);
	}
}
