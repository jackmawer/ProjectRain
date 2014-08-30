package com.mawersoft.projrain.level.tile;

import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {	
		screen.renderTile(x << 4, y << 4, this);
	}
	
	
	
}
