package Miet.pin33.klychkov.lab7;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.MenuKeyEvent;

public class Paddles extends Rectangle implements Runnable {

	int id;
	int yVelocity;
	int startX;
	int startY;
	int speed = 10;
	Boolean endPaddlesThread;
	GamePanel gp;	

	Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id, GamePanel gp) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
		this.startX = x;
		this.startY = y;
		this.gp = gp;
		this.endPaddlesThread = true;
	}

	public void keyPressed(KeyEvent e) {

		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
			}
			break;
		}

	}

	public void keyReleased(KeyEvent e) {

		switch (id) {
		case 1:
			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}

	}

	public void setYDirection(int YDirection) {
		yVelocity = YDirection;
	}

	public void checkCollision() {
		// paddle's border
		if (y <= 0)
			y = 0;
		if (y >= (gp.GAME_HEIGHT - gp.PADDLE_HEIGHT))
			y = gp.GAME_HEIGHT - gp.PADDLE_HEIGHT;
	}

	public void move() {		
		y = y + yVelocity;
	}

	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
	
	public void paddlesToStart() {
		x = startX;
		y = startY;
	}

	@Override
	public void run() {		
		try {
			long lastTime = System.nanoTime();
			double amountOfTicks =60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			while(endPaddlesThread) {
				long now = System.nanoTime();
				delta += (now -lastTime)/ns;
				lastTime = now;
				if(delta >=1) {
					move();
					checkCollision();
					gp.repaint();
					delta--;
				}
			}			
		}catch(Exception e) { System.err.println(e.getMessage() + "Ошибка в Ракетках"); }
		
	}
}
