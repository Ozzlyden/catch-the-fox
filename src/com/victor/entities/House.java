package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;

public class House extends Entity {
	
	public BufferedImage HOUSE;

	public House(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		HOUSE = Game.spritesheet.getSprite(32, 0, 16, 16);
		
		
	}
	
	public void tick() {
		
	}
	
	public void render (Graphics g) {
		
		g.drawImage(HOUSE,Game.WIDTH/2 - 20, Game.HEIGHT/2 - 20, null);
	}

}
