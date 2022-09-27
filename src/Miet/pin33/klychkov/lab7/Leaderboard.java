package Miet.pin33.klychkov.lab7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Leaderboard {
	
	final static String FILE_PATH = "D:\\Java projects\\Lab_7";
	ArrayList<Player> players = new ArrayList<Player>();
	File lb;
	GamePanel gp;
	
	Leaderboard(){
		lb = new File(FILE_PATH, "Leaderboard.txt");
		if(lb.exists()) {
			//readBoard();
			System.out.println("File exists");
		}			
		else {
			try {
				lb.createNewFile();				
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
	}
	
	public void readBoard() {
		
		try {
			File board = new File(FILE_PATH, "Leaderboard.txt");
			FileReader fr = new FileReader(board);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			
			while(line != null) {				
				
				String[] tokens = line.split("-");
				
				Player p = new Player(tokens[1],
						Integer.parseInt(tokens[2]),
						Integer.parseInt(tokens[3]),
						Integer.parseInt(tokens[4]),
						Integer.parseInt(tokens[5]),
						Integer.parseInt(tokens[6]),
						Integer.parseInt(tokens[7])
						);
				players.add(p);		
				
				line = br.readLine();
			}
			
		}
		catch(Exception ex){
			
		}
		
	}	
	
	public void sortBoard() {
		
		Boolean change = true;
		Player buf;
		
		while(change) {
			change = false;
			for (int i = 0; i < players.size() - 1; i++) {
				if (players.get(i).winGame < players.get(i + 1).winGame) {
					buf = players.get(i);
					players.set(i, players.get(i + 1));
					players.set(i + 1, buf);
					change = true;
				}
			}
		}
		
	}
	
	public void updateBoard(Score scr) {		
		
		Boolean exists1 = false;
		Boolean exists2 = false;
		
		players.clear();
		
		readBoard();
		
		if(players.size() == 0) {
			players.add(scr.player1);
			players.add(scr.player2);
		}
		else {
			for (int i = 0; i < players.size(); i++) {
				
				if(players.get(i).name.equals(scr.player1.name)) {
					players.get(i).winSeths += scr.player1.winSeths;
					players.get(i).losingSeths += scr.player1.losingSeths;
					players.get(i).winPoints += scr.player1.winPoints;
					players.get(i).losingPoints += scr.player1.losingPoints;
					players.get(i).winGame += scr.player1.winGame;
					players.get(i).Games += scr.player1.Games;
					exists1 = true;
				}
				if(players.get(i).name.equals(scr.player2.name)){
					players.get(i).winSeths += scr.player2.winSeths;
					players.get(i).losingSeths += scr.player2.losingSeths;
					players.get(i).winPoints += scr.player2.winPoints;
					players.get(i).losingPoints += scr.player2.losingPoints;
					players.get(i).winGame += scr.player2.winGame;
					players.get(i).Games += scr.player2.Games;
					exists2 = true;
				}				
				
			}
		}
		
		if (!exists1)
			players.add(scr.player1);
		if(!exists2)
			players.add(scr.player2);		
		
		sortBoard();
		
		try(FileWriter writer = new FileWriter(lb, false)){
				for (int i = 0; i < players.size(); i++) {
					writer.write((i + 1) + "-"
							+ players.get(i).name + "-"
							+ players.get(i).winSeths + "-"
							+ players.get(i).losingSeths + "-"
							+ players.get(i).winPoints + "-"
							+ players.get(i).losingPoints + "-"
							+ players.get(i).winGame + "-"
							+ players.get(i).Games + "-" + "\n");	
				}									
					
				writer.flush();			
		}
		catch(IOException ex) {
					System.out.println(ex.getMessage());
		}
		
	}
	

}
