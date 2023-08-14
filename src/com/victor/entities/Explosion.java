package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;

public class Explosion extends Entity{
	
	private int frames = 0, maxFrames = 4, index = 0, maxIndex = 2;
	
	public BufferedImage[] EXPLOSION;

	
	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
		
		EXPLOSION = new BufferedImage[4];
		
		EXPLOSION[0] = Game.spritesheet.getSprite(0, 128, 16, 16);
		EXPLOSION[1] = Game.spritesheet.getSprite(16, 128, 16, 16);
		EXPLOSION[2] = Game.spritesheet.getSprite(32, 128, 16, 16);

	}
	
	public void tick() {
		//LOGICA ANIMACAO
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
				Game.entities.remove(this);
				return;
			}			
		}
	}

	public void render(Graphics g) {
		g.drawImage(EXPLOSION[index], this.getX(), this.getY(), null);
	}
}
