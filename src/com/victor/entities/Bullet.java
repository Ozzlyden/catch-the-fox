package com.victor.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;

public class Bullet extends Entity{

	public Bullet(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		y-=speed;
		
		// OTIMIZACAO
		if(y < 0) {
			Game.entities.remove(this);
			return;
		}
	}

	
	public void render(Graphics g) {
		
		if(Player.isPressed == true) {
			g.setColor(Color.red);
			g.fillRect(this.getX(), this.getY(), width, height);
		}
		
	}
}
