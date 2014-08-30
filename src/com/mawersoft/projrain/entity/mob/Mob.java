package com.mawersoft.projrain.entity.mob;

import com.mawersoft.projrain.entity.Entity;
import com.mawersoft.projrain.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;// 0 N, 1 E, 2 S, 3 W
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		
		if (xa > 0)  dir = 1;//East
		if (xa < 0) dir = 3;//West
		if (ya > 0) dir = 2;//South
		if (ya < 0) dir = 0;//North
		
		if (!collision()) {
			x += xa;
			y += ya;
		}
	}
	
	public void update() {
	}
	
	private boolean collision() {
		return false;
	}
	
	public void render() {
	}
	
}
