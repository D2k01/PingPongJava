package Miet.pin33.klychkov.lab7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class InitPlayerFrame extends JFrame {		
	
	GameFrame game;
	GamePanel gp;
	Boolean err = true;
	HelpWindows hw = new HelpWindows();
	
	InitPlayerFrame(GamePanel gp){
		this.gp = gp;
		this.gp.ipF = this;	
		this.gp.score = new Score(gp.GAME_WIDTH, gp.GAME_HEIGHT);
		this.gp.menu = new MenuBar(gp.GAME_WIDTH, gp.GAME_HEIGHT, gp);
		this.gp.menu.menuFrame();
	}
	public void twoPlayers() { //void
		
		gp.score = new Score(gp.GAME_WIDTH, gp.GAME_HEIGHT);
		
		JFrame tpF = new JFrame();
		
		TextField plr1;
		Label lbPlr1;
		
		TextField plr2;
		Label lbPlr2;
		
		TextField scoreNum;
		Label lbScore;
		
		TextField sethsNum;
		Label lbSeths;
		
		Button btnOK;
		Button btnCancel;
		
		plr1 = new TextField("Default1", 15);
		lbPlr1 = new Label("Set Player's nickname");		
		
		plr2 = new TextField("Default2", 15);
		lbPlr2 = new Label("Set Player's nickname");
		
		scoreNum = new TextField("1", 5);
		lbScore = new Label("Set max Score");
		
		sethsNum = new TextField("1", 5);
		lbSeths = new Label("Set seths num");
		
		btnOK = new Button("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
					try {
						gp.score.maxScore = Integer.parseInt(scoreNum.getText());
						gp.score.seths = Integer.parseInt(sethsNum.getText());
						err = false;
					}
					catch(Exception ex) {
						hw.errorOfInit();						
					}				
				
					if(!err) {
						tpF.dispose();						
						changePaddle(plr1.getText(), plr2.getText());
					}								
			}
		});
		btnCancel = new Button("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = 
		  new GridBagConstraints();
		    
		tpF.setLayout(gbl);
		
		c.anchor = GridBagConstraints.NORTHWEST; 
		c.fill   = GridBagConstraints.NONE;  
		c.gridheight = 1;
		c.gridwidth  = 1;
		c.gridx = GridBagConstraints.RELATIVE; 
		c.gridy = GridBagConstraints.RELATIVE; 
		c.insets = new Insets(10, 10, 0, 0);
		    
		gbl.setConstraints(plr1, c);
		tpF.add(plr1);
		
		gbl.setConstraints(lbPlr1, c);
		tpF.add(lbPlr1);
		
		c.gridwidth  = GridBagConstraints.REMAINDER; 
		c.ipadx = 32;
		 
		gbl.setConstraints(btnOK, c);
		tpF.add(btnOK);
		
		c.ipadx = 0;
		c.gridwidth  = 1; 
		 
		gbl.setConstraints(plr2, c);
		tpF.add(plr2);
		    
		gbl.setConstraints(lbPlr2, c);
		tpF.add(lbPlr2);
		
		c.gridwidth  = GridBagConstraints.REMAINDER; 
		c.ipadx = 10;
		c.weightx = 1.0;
		    
		gbl.setConstraints(btnCancel, c);
		tpF.add(btnCancel);
		
		c.ipadx = 0;
		c.gridwidth  = 1; 
		c.weightx = 0.0;
		    
		gbl.setConstraints(scoreNum, c);
		tpF.add(scoreNum);
		
		c.gridwidth  = GridBagConstraints.REMAINDER; 
		 
		gbl.setConstraints(lbScore, c);
		tpF.add(lbScore);
		
		c.gridwidth  = 1; 
	    
		gbl.setConstraints(sethsNum, c);
		tpF.add(sethsNum);
		
		c.gridwidth  = GridBagConstraints.REMAINDER; 
	    
		gbl.setConstraints(lbSeths, c);
		tpF.add(lbSeths);
		
		tpF.setSize(400, 200);
		tpF.setTitle("Initialization");
		tpF.setResizable(false);
		tpF.setLocationRelativeTo(null);		
		tpF.setVisible(true);
	}
	
	public void changePaddle(String name1, String name2) { //Player 1 change paddle, Player 2 receives a paddle by elimination
		JFrame changeF = new JFrame();
		Button blue = new Button();
		blue.setBackground(Color.BLUE);
		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				gp.score.newPlayers(name1, name2);
				changeF.dispose();
				ready(); // Test
				//gp.newPaddles(); // Test
				//gp.newBall(); // Test
				//new GameFrame(gp); // Test
				//System.out.println(scr.player1.name + "\n" + scr.player2.name); Test
			}
		});
		
		Button red = new Button();
		red.setBackground(Color.RED);
		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				gp.score.newPlayers(name2, name1);
				changeF.dispose();
				ready();
				//gp.newPaddles(); // Test
				//gp.newBall(); // Test
				//new GameFrame(gp); // Test
				//System.out.println(scr.player1.name + "\n" + scr.player2.name); Test
			}
		});
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = 
		  new GridBagConstraints();
		    
		changeF.setLayout(gbl);
		
		c.anchor = GridBagConstraints.WEST; 
		c.fill   = GridBagConstraints.HORIZONTAL;  
		c.gridheight = 1;
		c.gridwidth  = 1;
		c.gridx = GridBagConstraints.RELATIVE; 
		c.gridy = GridBagConstraints.RELATIVE; 
		c.ipadx = 75;
		c.insets = new Insets(10, 10, 0, 0);
		
		gbl.setConstraints(blue, c);
		changeF.add(blue);
		
		c.gridwidth  = GridBagConstraints.REMAINDER; 				
		c.anchor = GridBagConstraints.EAST; 
		c.ipadx = 75;
		 
		gbl.setConstraints(red, c);
		changeF.add(red);		
		
		changeF.setSize(400, 200);
		changeF.setTitle("Change paddle");
		changeF.setResizable(false);
		changeF.setLocationRelativeTo(null);		
		changeF.setVisible(true);
		
	}
	
	public void championship() {
		
	}
	public void ready() {
		JFrame readyF = new JFrame();
		
		Label lbReady = new Label("You wanna play?");
		lbReady.setFont(new Font("Serif", Font.PLAIN, 20));
		
		Button start = new Button("Let's play");		
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				readyF.dispose();				
				if(game == null) {
					gp.newPaddles(); // Test
					gp.newBall(); // Test
					gp.newMenu();// Test
					game = new GameFrame(gp); // Test
				}					
				else {
					gp.newPaddles(); // Test
					gp.newBall(); // Test
					gp.newMenu();// Test
				}
					
				//System.out.println(scr.player1.name + "\n" + scr.player2.name); Test
			}
		});	
		
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = 
		  new GridBagConstraints();
		    
		readyF.setLayout(gbl);
		
		c.anchor = GridBagConstraints.CENTER; 
		c.fill   = GridBagConstraints.HORIZONTAL;  
		c.gridheight = 1;
		c.gridwidth  = 1;
		c.gridx = 1; 
		c.gridy = 1; 
		//c.ipadx = 75;
		c.insets = new Insets(10, 10, 0, 0);
		
		gbl.setConstraints(start, c);
		readyF.add(start);			
		 				
		c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(10, 25, 0, 0);
		c.gridx = 1; 
		c.gridy = 0;		
		//c.ipadx = 75;
		
		gbl.setConstraints(lbReady, c);
		readyF.add(lbReady);		
		
		readyF.setSize(400, 200);
		readyF.setTitle("StartGame");
		readyF.setResizable(false);
		readyF.setLocationRelativeTo(null);		
		readyF.setVisible(true);
		
	}
	
	
	
	
	class HelpWindows {
		
		public void Instruction() {
			
			JFrame instF = new JFrame();
			
		}
		public void errorOfInit() {					
			
			JFrame errF = new JFrame();
			
			Label lbErr;
			lbErr = new Label("Check the entered values");
			
			GridBagLayout gbl = new GridBagLayout();
			GridBagConstraints c = 
			  new GridBagConstraints();
			    
			errF.setLayout(gbl);
			
			c.anchor = GridBagConstraints.NORTHWEST; 
			c.fill   = GridBagConstraints.NONE;  
			c.gridheight = 1;
			c.gridwidth  = 1;
			c.gridx = GridBagConstraints.RELATIVE; 
			c.gridy = GridBagConstraints.RELATIVE; 
			c.insets = new Insets(10, 10, 0, 0);
			    
			gbl.setConstraints(lbErr, c);
			errF.add(lbErr);
			
			errF.setSize(300, 200);
			errF.setTitle("Error");
			errF.setResizable(false);
			errF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			errF.setLocationRelativeTo(null);					
			errF.setVisible(true);		
		}
		
	}
	
}
	

