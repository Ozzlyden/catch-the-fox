package com.victor.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;

public class Tile {
	
	private BufferedImage sprite;
	protected int x, y;
	
	public boolean solid = false;	// veriuficado para ver so tile sera solido ou nao
	
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
