package com.victor.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.Node;
import com.victor.world.Vector2i;
import com.victor.world.World;

public class Entity {
	
	public static BufferedImage[] PLAYER_SPRITE = {Game.spritesheet.getSprite(0, 0, 16, 16), Game.spritesheet.getSprite(0, 0, 16, 16)};
	
	public static BufferedImage ENEMY1 = Game.spritesheet.getSprite(0,16, 16, 16);
	public static BufferedImage HOUSE = Game.spritesheet.getSprite(32,0, 16, 16);

	public double x;
	public double y;
	public double z;
	public double speed;
	protected int width;
	protected int height;
	
	public int depth;	//Camadas de renderizacoa
	
	protected List <Node> path;
	
	protected BufferedImage sprite;
	
	public static Random rand = new Random();
	
	public Entity(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.sprite = sprite;
	}
	
	//COMPARACAO CAMADAS RENDER
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
			//O primeiro da lista eh renderizado primeiro e o ultimo eho ultimo
			// ex: depth = 10, vai renderizar primeiro que um depth = 1
			
			@Override
			public int compare(Entity n0, Entity n1){
				if(n1.depth < n0.depth) 
					return +1;
				
				if(n1.depth > n0.depth)
					return -1;
				return 0;
					
			}
		};
		
		
	//LOGICA PARA A CAMERA SEGUIR e NAO MOSTRAR AS AREAS FORA DO MAPA
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT * 16 - Game.HEIGHT);
	}	
	
	
	//GET E SETTERS -> Sao metodos de acesso para as var privadas
	//serve para proteger o as variaveis de alteracoes
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	//METODO PARA CALCULAR DISTANCIA ENTRE ENTITIES
	public double calculateDistance(int x1, int y1, int x2, int y2) {
		
		//retorna a distancia usando angulos para um direcao
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));	//retorna angulos	
	}	
	
	//SEGUIR O CAMINHO DO ALGORITMO AStar
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				//target eh caminho
				Vector2i target = path.get(path.size() - 1).tile;	//pega o ultimo item da list e depois o tile
				//xprev = x;
				//yprev = y;
				if(x < target.x * 16) {	//16 pq as sprites do tile ta em 16X16
					x++;
				}else if(x > target.x * 16) {
					x--;
				}
				
				if(y < target.y * 16) {
					y++;
				}else if( y > target.y * 16) {
					y--;
				}
				
				if(x == target.x * 16 && y == target.y * 16) {	// se os dois forem iguais, significa que achou o caminho
					path.remove(path.size() - 1);	//remove da lista e comeca outra ansalie de caminho
				}
			}
		}
	}
	
	public static boolean isColliding(Entity e1,Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());
		Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());
		
		if(e1Mask.intersects(e2Mask) && e1.z == e2.z) {
			return true;
		}
		return false;
	}
	
	public void render (Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);
		
		//Testar mascaras
		//g.setColor(Color.red);
		//g.fillRect(this.getX() + maskx - Camera.x,this.getY() + masky - Camera.y, mwidth, mheight);
	}
}
