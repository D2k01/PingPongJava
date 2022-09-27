package Miet.pin33.klychkov.lab7;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class GamePanel extends JPanel{

	static final int GAME_WIDTH = 1000; // Ширина окна
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555)); // Высота окна 
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT + 100); // Размер окна; height was +50
	static final int BALL_DIAMETER = 20; // Размер шара
	static final int PADDLE_WIDTH = 25; // Ширина платформы
	static final int PADDLE_HEIGHT = 100; // Высота платформы
	Thread paddle1Thread;
	Thread paddle2Thread;
	Thread ballThread;	
	Image image;
	Graphics graphics;
	Random random;
	Paddles paddle1;
	Paddles paddle2;
	Ball ball;
	Score score;	
	MenuBar menu;
	Leaderboard leaderboard = new Leaderboard();
	InitPlayerFrame ipF;
	Instruction instr;

	GamePanel() {			
		//newScore(); Was		
		//newPaddles(); Was
		//newBall(); Was
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
	}
	
	public void twoPlayersGame() {		
		
		if(score.seths != 0) {
			if(score.player1.score == score.maxScore || score.player2.score == score.maxScore) {
				score.seths--;		
				//leaderboard.addResultToBoard(score);
				if(score.player1.score == score.maxScore) {
					score.player1.winSeths++;
					score.player2.losingSeths++;
					score.player1.winPoints += score.player1.score;					
				}
				if(score.player2.score == score.maxScore) {
					score.player2.winSeths++;
					score.player1.losingSeths++;
					score.player2.winPoints += score.player2.score;					
				}					
				score.player1.losingPoints += score.player2.score;
				score.player2.losingPoints += score.player1.score;
				ball.ballToStart();				
				menu.drawScoreOfSets(graphics, true);
				score.player1.score = 0;
				score.player2.score = 0;
				repaint();					
				if (score.seths == 0) {
					score.player1.winGame = (score.player1.winSeths > score.player2.winSeths) ? 1 : 0;
					score.player2.winGame = (score.player1.winSeths < score.player2.winSeths) ? 1 : 0;
					score.player1.Games++;
					score.player2.Games++;					
					System.out.println("  N  |\tName\t|  WinningGames  |  WinningSeths  |  LosingSeths  |  WinningPoints  |  LosingPoints  |  Games  \n");
					System.out.println("  0  |  " +
							score.player1.name + "  |  " +
							score.player1.winGame + "  |  " +
							score.player1.winSeths + "  |  " +
							score.player1.losingSeths + "  |  " +
							score.player1.winPoints + "  |  " +
							score.player1.losingPoints + "  | " +
							score.player1.Games + "\n");
					System.out.println("  0  |  " +
							score.player2.name + "  |  " +
							score.player2.winGame + "  |  " +
							score.player2.winSeths + "  |  " +
							score.player2.losingSeths + "  |  " +
							score.player2.winPoints + "  |  " +
							score.player2.losingPoints + "  | " +
							score.player2.Games + "\n");					
					leaderboard.updateBoard(score);		
					stopThreads();
				}					
			}			
		}
	}
	
	public void restartGame(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			if(stopThreads()) {
				newPaddles();
				newBall();
				newScore();
			}			
		}			
		
	}	
	public boolean stopThreads() {
		paddle1.endPaddlesThread = false;
		paddle2.endPaddlesThread = false;
		ball.endBallThread = false;
		
		/*
		while(paddle1Thread.isAlive() || paddle2Thread.isAlive() || ballThread.isAlive())
		{
			System.out.println("Потоки не закрыты");
		}
		*/
		if (!paddle1Thread.isAlive() & !paddle2Thread.isAlive() & !ballThread.isAlive())
			return true;
		else 
			return false;

	}

	public void newBall() {		
		random = new Random();
		ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER, this);		
		
		ballThread = new Thread(ball);
		ballThread.setName("ballThread");
		
		ballThread.start();
		
		System.out.println("Ball created\n");
	}

	public void newPaddles() {
		paddle1 = new Paddles(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1, this);
		paddle2 = new Paddles((GAME_WIDTH - PADDLE_WIDTH), (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2, this);
		
		paddle1Thread = new Thread(paddle1);
		paddle1Thread.setName("Paddle1Thread");
		
		paddle2Thread = new Thread(paddle2);
		paddle2Thread.setName("Paddle2Thread");
		
		paddle1Thread.start();
		paddle2Thread.start();
		
		System.out.println("Paddles created\n");
	}
	
	public void newScore() {
		score = new Score(GAME_WIDTH, GAME_HEIGHT);				
	}
	
	public void newMenu() {
		menu = new MenuBar(GAME_WIDTH, GAME_HEIGHT, this);
		menu.draw();
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {		
		ball.draw(g);
		paddle1.draw(g);
		paddle2.draw(g);
		score.draw(g);		
		menu.drawScoreOfMatch(g);
	}		

	public class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			restartGame(e);
			
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}

}
