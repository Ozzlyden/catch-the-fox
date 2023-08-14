package com.victor.entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.FloorTile;
import com.victor.world.Tile;
import com.victor.world.WallTile;
import com.victor.world.World;

public class Enemy2 extends Entity{
	
	public boolean isDamaged;
	public int damageFrames = 8, damageCurrent = 0;
	private int frames = 0, maxFrames = 15, index = 0, maxIndex = 3;
	
	public int life = 5;
	
	public BufferedImage[] ENEMY2;
	public BufferedImage[] ENEMY2_FEEDBACK;

	public Enemy2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, null);
		// TODO Auto-generated constructor stub
		
		ENEMY2 = new BufferedImage[4];
		ENEMY2_FEEDBACK = new BufferedImage[2];
			
		ENEMY2[0] = Game.spritesheet.getSprite(0, 112, 16, 16);
		ENEMY2[1] = Game.spritesheet.getSprite(16, 112, 16, 16);
		ENEMY2[2] = Game.spritesheet.getSprite(32, 112, 16, 16);
		ENEMY2[3] = Game.spritesheet.getSprite(48, 112, 16, 16);

	}

	public void tick() {
		// CAMADA DE RENDER
		depth = 2;
		
		// LOGICA DE MOVIMENTACAO
		y+=speed;
		
		// OTIMISACAO
		if(y >= Game.HEIGHT) {
			Game.entities.remove(this);
			Player.lifePlayer--;
			return;
		}
		
		// SISTEMA DE DANO
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Bullet) {
				if(Entity.isColliding(this, e)) {
					life--;
					Game.entities.remove(e);
					isDamaged = true;
					if(life == 0) {
						Explosion explosion = new Explosion(x, y, 16, 16, 0, null);
						Game.entities.add(explosion);
						Game.score++;
						Game.entities.remove(this);
						return;
					}
				}
			}
		}
		
	
		//LOGICA ANIMACAO
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
		//FEEDBACK DANO
		if(isDamaged) {
			this.damageCurrent++;
			if(this.damageCurrent == this.damageFrames) {
				this.damageCurrent = 0;
				this.isDamaged = false;
			}
		}
		
	}
	
	
	public void render(Graphics g) {
		
		g.drawImage(ENEMY2[index],this.getX() - Camera.x, this.getY() - Camera.y, null);
		
	}
}
