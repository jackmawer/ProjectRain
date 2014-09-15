package com.mawersoft.projrain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.mawersoft.projrain.entity.Entity;
import com.mawersoft.projrain.entity.projectile.Projectile;
import com.mawersoft.projrain.entity.projectile.WizardProjectile;
import com.mawersoft.projrain.graphics.Sprite;
import com.mawersoft.projrain.level.Level;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;// 0 N, 1 E, 2 S, 3 W
	protected boolean moving = false;
	
	
	
	public void move(int xa, int ya) {
		
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0)  dir = 1;//East
		if (xa < 0) dir = 3;//West
		if (ya > 0) dir = 2;//South
		if (ya < 0) dir = 0;//North
		
		if (!collision(xa, ya)) {
			y += ya;
			x += xa;
		}
	}
	
	public void update() {
	}
	
	protected void shoot(int x, int y, double dir) {
		//dir *= 182 / Math.PI;
		//System.out.println("Angle: " + dir);
		Projectile p = new WizardProjectile(x, y, dir);
		level.addProjectile(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render() {
	}
	
}
