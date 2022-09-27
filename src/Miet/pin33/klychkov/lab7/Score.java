package Miet.pin33.klychkov.lab7;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;	
	Player player1;
	Player player2;
	int maxScore;
	int seths;
	
	Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
		player1 = new Player(0, "Blue");
		player2 = new Player(0, "Red");
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.drawLine(0, GAME_HEIGHT, GAME_WIDTH, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1.score/10)+String.valueOf(player1.score%10), (GAME_WIDTH/2)-85, 50);
		g.drawString(String.valueOf(player2.score/10)+String.valueOf(player2.score%10), (GAME_WIDTH/2)+20, 50);
	}
	public void newPlayers(String name1, String name2) { //Player 1 - blue; Player 2 - red;
		player1.name = name1;
		player2.name = name2;		
	}
}
