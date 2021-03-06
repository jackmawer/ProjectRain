package com.mawersoft.projrain.entity.mob;

import com.mawersoft.projrain.Game;
import com.mawersoft.projrain.entity.projectile.Projectile;
import com.mawersoft.projrain.entity.projectile.WizardProjectile;
import com.mawersoft.projrain.graphics.Screen;
import com.mawersoft.projrain.graphics.Sprite;
import com.mawersoft.projrain.input.Keyboard;
import com.mawersoft.projrain.input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	
	private int fireRate = 0;

	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	public void update() {
		if (fireRate > 0) fireRate--;
		int xa = 0, ya = 0;
		if (anim < 7500) anim++; 
		else anim = 0;
		//TODO Add sprinting mechanics eg. a toggle key in the key listener and an if statement here
		boolean sprinting = true;
		if (sprinting) {
			// Do the walk thing twice, therefore doubling the speed
			if (input.up) ya--;
			if (input.down) ya++;
			if (input.left) xa--;
			if (input.right) xa++;
		}
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;
		if (xa != 0 || ya != 0) {
			move (xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
	}
	
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate <=0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 3;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}
	
	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_forward_1;
				} else {
					sprite = Sprite.player_forward_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_right_1;
				} else {
					sprite = Sprite.player_right_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_back_1;
				} else {
					sprite = Sprite.player_back_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_left_1;
				} else {
					sprite = Sprite.player_left_2;
				}
			}
		}
		//int xx = x - 16;
		//int yy = y - 16;
		screen.renderPlayer(x - 16, y - 16, sprite);
		//screen.renderPlayer(xx, yy, Sprite.player0);
		//screen.renderPlayer(xx + 16, yy, Sprite.player1);
		//screen.renderPlayer(xx, yy + 16, Sprite.player2);
		//screen.renderPlayer(xx + 16, yy + 16, Sprite.player3);
		
	}
	
}
