package com.mawersoft.projrain.entity;

import java.util.Random;

import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.level.Level;

public abstract class Entity {
	
	public int x, y;
	public boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	public void remove() {
		//TODO Remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
