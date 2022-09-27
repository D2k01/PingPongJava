package Miet.pin33.klychkov.lab7;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle implements Runnable {

	Random random;
	GamePanel gp;
	Boolean endBallThread;
	int xVelocity;
	int yVelocity;
	int startX;	
	int speed = 3; //Was 2

	Ball(int x, int y, int width, int height, GamePanel gp) {
		super(x, y, width, height);
		random = new Random();
		this.gp = gp;
		this.startX = x;		
		this.endBallThread = true;
		int randomXDirection = random.nextInt(2);
		int randomYDirection = random.nextInt(2);

		if (randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection * speed);

		if (randomYDirection == 0)
			randomYDirection--;
		setXDirection(randomYDirection * speed);
	}

	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}

	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}

	public void checkCollision() {

		// bounce ball off top & bottom window edges
		if (y <= 0) {
			setYDirection(-yVelocity);
		}
		if (y >= gp.GAME_HEIGHT - gp.BALL_DIAMETER) {
			setYDirection(-yVelocity);
		}
		// bounce ball off paddles
		if (intersects(gp.paddle1)) {
			xVelocity = Math.abs(xVelocity);
			xVelocity++; // optional for more difficulty
			if (yVelocity > 0)
				yVelocity++; // optional for more difficulty
			else
				yVelocity--;
			setXDirection(xVelocity);
			setYDirection(yVelocity);
		}
		if (intersects(gp.paddle2)) {
			xVelocity = Math.abs(xVelocity);
			xVelocity++; // optional for more difficulty
			if (yVelocity > 0)
				yVelocity++; // optional for more difficulty
			else
				yVelocity--;
			setXDirection(-xVelocity);
			setYDirection(yVelocity);
		}

		// give a player 1 point and creates new paddles & ball
		if (x <= 0) {			
			gp.score.player2.score++;	
			gp.twoPlayersGame(); // Test
			gp.paddle1.paddlesToStart();
			gp.paddle2.paddlesToStart();
			ballToStart();
			System.out.println(Thread.activeCount() + "\n");
			// System.out.println("Player 2: "+score.player2);
		}
		if (x >= gp.GAME_WIDTH - gp.BALL_DIAMETER) {			
			gp.score.player1.score++;			
			gp.twoPlayersGame(); // Test
			gp.paddle1.paddlesToStart();
			gp.paddle2.paddlesToStart();
			ballToStart();
			System.out.println(Thread.activeCount() + "\n");
			// System.out.println("Player 1: "+score.player1);
		}
	}

	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, height, width);
	}
	
	public void ballToStart() {
		random = new Random();
		x = startX;
		y = random.nextInt(gp.GAME_HEIGHT - gp.BALL_DIAMETER);
		xVelocity = 0;
		yVelocity = 0;
		
		int randomXDirection = random.nextInt(2);
		int randomYDirection = random.nextInt(2);

		if (randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection * speed);

		if (randomYDirection == 0)
			randomYDirection--;
		setXDirection(randomYDirection * speed);
	}

	@Override
	public void run() {
		try {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			while (endBallThread) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				if (delta >= 1) {
					move();
					checkCollision();
					gp.repaint();
					delta--;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage() + "Îרטבךא ג Ìÿקו");
		}

	}
}
