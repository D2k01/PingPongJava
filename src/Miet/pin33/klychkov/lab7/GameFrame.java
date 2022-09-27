package Miet.pin33.klychkov.lab7;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GamePanel panel;
	
	GameFrame(GamePanel gp) { //gp Test
		
		//panel = new GamePanel();
		panel = gp;
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);		
	}	
}
