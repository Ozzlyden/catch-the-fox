package com.victor.main;

import com.victor.entities.Enemy1;
import com.victor.entities.Entity;
import com.victor.world.World;

public class EnemySpawn {
	
	public int targetTimeEnemy1 = 60 * 3;
	public int targetTimeEnemy2 = 60 * 2;
	public int curTimeEn1 = 0;
	
	public void tick() {
		curTimeEn1++;
		
		// SPAWN ENEMY1
		if(curTimeEn1 == targetTimeEnemy1) {
			curTimeEn1 = 0;

			int xWidth = Entity.rand.nextInt(Game.WIDTH - 60);	// -40 para nao sair da tela parte de cima
			int yHeight = -60;
			
			int xxWidth = Entity.rand.nextInt(Game.WIDTH - 60);	// -40 para nao sair da tela para de baixo
			int yyHeight = Entity.rand.nextInt(Game.HEIGHT - 60);
			
			if(Entity.rand.nextInt(100) < 50) {
				Enemy1 enemy1 = new Enemy1(xWidth, yHeight, 16, 16, 0.5, Entity.ENEMY1);
				Game.entities.add(enemy1);
			}else {
				Enemy1 enemy1 = new Enemy1(xxWidth, yyHeight, 16, 16, 0.5, Entity.ENEMY1);
				Game.entities.add(enemy1);
			}
	
		}
	}

}
