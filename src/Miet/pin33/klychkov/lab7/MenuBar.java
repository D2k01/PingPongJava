package Miet.pin33.klychkov.lab7;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MenuBar {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	static int seths;
	//Boolean start = false;
	ArrayList<Integer> player1scores = new ArrayList();
	ArrayList<Integer> player2scores = new ArrayList();	
	GamePanel gp;
	
	MenuBar(int width, int height, GamePanel gp){
		
		this.GAME_WIDTH = width;
		this.GAME_HEIGHT = height;
		this.gp = gp;
		this.seths = gp.score.seths;		
		//Test
		for (int i = 0; i < seths; i++) {
			this.player1scores.add(i, 0);
			this.player2scores.add(i, 0);
		}
	}
	MenuBar(){
		//this.start = start;
		menuFrame();
	}
	public void draw() {
		
		Button menu = new Button("MENU");	
		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {					
				
				menuFrame();
				menu.setFocusable(false);
				
			}
		});
		gp.setLayout(null);
		menu.setBounds(0, GAME_HEIGHT + 25, 100, 50); //height was 50
		gp.add(menu);	
		
	}
	public void menuFrame() {// Выровнить кнопки
		
		int x = 45;
		
		JFrame menuF = new JFrame();
		
		
		JPanel menuP = new JPanel();
		menuP.setLayout(null);
		
		Button twoPlayers = new Button("Two Players");
		twoPlayers.setBounds(50, x, 200, 50);	
		twoPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				gp.ipF.twoPlayers();// Test
				menuF.dispose();				
			}
		});
		
		Button championship = new Button("Championship");
		championship.setBounds(50, x*2, 200, 50);
		
		Button statistic = new Button("Statistic");
		statistic.setBounds(50, x*3, 200, 50);
		
		Button instruction = new Button("Instruction");
		instruction.setBounds(50, x*4, 200, 50);
		instruction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				gp.instr = new Instruction(gp);// Test
				menuF.dispose();				
			}
		});
		
		Button back = new Button("Back");
		back.setBounds(50, x*5, 200, 50);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				menuF.dispose();
				gp.newBall();
				gp.newPaddles();				
			}
		});
		
		Button exit = new Button("Exit");
		exit.setBounds(50, x*6, 200, 50); // height was 50
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				gp.leaderboard.updateBoard(gp.score);
				gp.ipF.game.dispose();
				menuF.dispose();							
			}
		});
		
		menuP.add(twoPlayers);
		menuP.add(championship);
		menuP.add(statistic);
		menuP.add(instruction);
		menuP.add(back);
		menuP.add(exit);		
		
		if(gp.ipF.game != null)
			gp.stopThreads();// Test
		
		menuF.add(menuP);
		menuF.setSize(300, 365);
		menuF.setTitle("Menu");
		menuF.setResizable(false);
		menuF.setLocationRelativeTo(null);		
		menuF.setVisible(true);
		
	}
	public void drawScoreOfMatch(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 30));	
		
		
		g.drawString(String.valueOf(gp.score.seths/10)+String.valueOf(gp.score.seths%10), 150, GAME_HEIGHT + 60);
		
		g.setFont(new Font("Consolas", Font.PLAIN, 15));
		
		
		for (int i = 0; i < seths; i++) {
			g.drawString(String.valueOf(i + 1), 260 + (i * 40), GAME_HEIGHT + 20);			
		}
		
		drawScoreOfSets(g, false);
		
	}
	public void drawScoreOfSets(Graphics g, Boolean endSet) {
		
		g.setFont(new Font("Consolas", Font.PLAIN, 20));	
		
		if (endSet) {
			player1scores.set(seths - gp.score.seths - 1, gp.score.player1.score);
			player2scores.set(seths - gp.score.seths - 1, gp.score.player2.score);
		}
		
		for (int i = 0; i < seths; i++) {			
			g.drawString(String.valueOf(player1scores.get(i)/10)+String.valueOf(player1scores.get(i)%10), 250 + ((i) * 40), GAME_HEIGHT + 40);
			g.drawString(String.valueOf(player2scores.get(i)/10)+String.valueOf(player2scores.get(i)%10), 250 + ((i) * 40), GAME_HEIGHT + 70);
		}
		
		endSet = false;
		
	}
}
