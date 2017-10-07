package Modele;

import java.lang.Math;

public class Bird {
	private int posX; //Fixe
	private int posY; 
	private float gravity; //Fixe
	private int size;
	private int time;
	private float speed;
	private float v0;
	private float jumpHeight;
	private boolean dead;
	private int  deadSpeed; // speed of obstacles

	//Useless non ? 
/*	public Bird() {
		time = 0;
		posY = 0;
		posX = 10;
		size = 50;
		gravity = 0.7f;
		jumpHeight = Obstacle.INTERVAL / 2;
	}
	*/
	public Bird(int y, int deadSpeed) {
		time = 0;
		size = 90;
		posY = y;
		posX = 10+size/2;
		gravity = 0.5f;
		jumpHeight = Obstacle.INTERVAL *0.5f;
		v0 = (float) Math.sqrt(2*gravity*jumpHeight);
		dead = false;
		this.deadSpeed = deadSpeed;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
		
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	//Meme chose qu'au dessus...
	public void update(boolean saut) {
		if (!dead) {
			if (saut) {time=0;}
			speed = -time++*gravity + v0;
			this.posY -= speed;
		} else this.posX -= deadSpeed;
	}

	public void up() {
		time = 0;
	}
}
